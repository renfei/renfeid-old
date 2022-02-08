package net.renfei.controllers.api.impl;

import net.renfei.annotation.OperationLog;
import net.renfei.controllers.BaseController;
import net.renfei.controllers.api.KitBoxApi;
import net.renfei.model.APIResult;
import net.renfei.model.DnsTypeEnum;
import net.renfei.model.StateCodeEnum;
import net.renfei.model.kitbox.FreeMarkerAndBeanVO;
import net.renfei.model.kitbox.IcpQueryVo;
import net.renfei.model.kitbox.IkAnalyzeVO;
import net.renfei.model.log.OperationTypeEnum;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.services.KitBoxService;
import net.renfei.services.SearchService;
import net.renfei.utils.JacksonUtil;
import net.renfei.utils.NumberUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 工具箱API接口
 *
 * @author renfei
 */
@RestController
public class KitBoxApiController extends BaseController implements KitBoxApi {
    private final KitBoxService kitBoxService;
    private final SearchService searchService;

    public KitBoxApiController(KitBoxService kitBoxService,
                               SearchService searchService) {
        this.kitBoxService = kitBoxService;
        this.searchService = searchService;
    }

    @Override
    @OperationLog(module = SystemTypeEnum.API, desc = "获取系统时间接口")
    public APIResult<String> getServerDateTime() {
        return new APIResult<>(new Date().toString());
    }

    @Override
    @OperationLog(module = SystemTypeEnum.API, desc = "FreeMarker(FTL)在线测试工具", operation = OperationTypeEnum.CREATE)
    public APIResult<String> getContentByFreeMarkerAndBean(FreeMarkerAndBeanVO freeMarkerAndBean) {
        if (freeMarkerAndBean == null) {
            return new APIResult<>("");
        }
        if (freeMarkerAndBean.getFtl() == null || freeMarkerAndBean.getFtl().isEmpty()) {
            return new APIResult<>("");
        }
        if (freeMarkerAndBean.getBeanJson() == null || freeMarkerAndBean.getBeanJson().isEmpty()) {
            return new APIResult<>("");
        }
        if (freeMarkerAndBean.getFtl().contains("<#include")
                || freeMarkerAndBean.getFtl().contains("?api.")
                || freeMarkerAndBean.getFtl().contains("get_optional_template")
                || freeMarkerAndBean.getFtl().contains("new()")) {
            return APIResult.builder()
                    .code(StateCodeEnum.NoContent)
                    .message("我们理解您的请求，出于安全考虑被被拒绝执行，请更换变量名称重试。")
                    .data("")
                    .build();
        }
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_31);
        freemarker.cache.StringTemplateLoader templateLoader = new freemarker.cache.StringTemplateLoader();
        configuration.setTemplateLoader(templateLoader);
        configuration.setLocalizedLookup(false);
        configuration.setNewBuiltinClassResolver(freemarker.core.TemplateClassResolver.SAFER_RESOLVER);
        configuration.setDefaultEncoding("UTF-8");
        APIResult<String> apiResult;
        try {
            freemarker.template.Template template = new freemarker.template.Template("freemarkerTest", freeMarkerAndBean.getFtl(), configuration);
            StringWriter stringWriter = new StringWriter();
            Object object = JacksonUtil.string2Obj(freeMarkerAndBean.getBeanJson(), Object.class);
            template.process(object, stringWriter);
            apiResult = new APIResult<>(stringWriter.toString());
        } catch (Exception ex) {
            apiResult = APIResult.builder()
                    .code(StateCodeEnum.Error)
                    .message(ex.getMessage())
                    .data(ex.getMessage())
                    .build();
        }
        return apiResult;
    }

    @Override
    @OperationLog(module = SystemTypeEnum.API, desc = "域名 dig+trace")
    public APIResult<String> getDomainDigTrace(String domain) {
        return kitBoxService.execDigTrace(domain, null);
    }

    @Override
    @OperationLog(module = SystemTypeEnum.API, desc = "域名 dig+trace")
    public APIResult<String> getDomainDigTrace(String domain, DnsTypeEnum dnsTypeEnum) {
        return kitBoxService.execDigTrace(domain, dnsTypeEnum);
    }

    @Override
    @OperationLog(module = SystemTypeEnum.API, desc = "域名Whois信息查询工具")
    public APIResult<String> getDomainWhois(String domain) {
        return kitBoxService.execWhois(domain);
    }

    @Override
    @OperationLog(module = SystemTypeEnum.API, desc = "UUID/GUID 生成接口")
    public APIResult<List<String>> getUuid(String quantity, Boolean upperCase, Boolean hyphen) {
        int lQuantity = NumberUtils.parseInt(quantity, 1);
        if (lQuantity <= 0) {
            lQuantity = 1;
        }
        if (lQuantity > 1000) {
            lQuantity = 1000;
        }
        if (upperCase == null) {
            upperCase = true;
        }
        if (hyphen == null) {
            hyphen = true;
        }
        List<String> stringUuid = new ArrayList<>();
        for (; lQuantity > 0; lQuantity--) {
            String uuid = UUID.randomUUID().toString();
            if (upperCase) {
                uuid = uuid.toUpperCase();
            } else {
                uuid = uuid.toLowerCase();
            }
            if (!hyphen) {
                uuid = uuid.replace("-", "");
            }
            stringUuid.add(uuid);
        }
        return new APIResult<>(stringUuid);
    }

    @Override
    @OperationLog(module = SystemTypeEnum.API, desc = "中文分词工具API", operation = OperationTypeEnum.CREATE)
    public APIResult<List<IkAnalyzeVO>> getWordIkAnalyze(String word) {
        if (ObjectUtils.isEmpty(word)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("待分词内容为空，请检查入参。")
                    .build();
        }
        try {
            return new APIResult<>(searchService.getIkAnalyzeTerms(word));
        } catch (Exception exception) {
            return APIResult.builder()
                    .code(StateCodeEnum.Error)
                    .message("中文分词服务暂时不可用，请稍后再试。")
                    .build();
        }
    }

    @Override
    @OperationLog(module = SystemTypeEnum.API, desc = "ICP备案查询接口", operation = OperationTypeEnum.RETRIEVE)
    public APIResult<IcpQueryVo.IcpInfo> getDomainIcpInfo(String domain, Boolean refresh) {
        return new APIResult<>(kitBoxService.queryIcpInfo(domain, refresh));
    }
}
