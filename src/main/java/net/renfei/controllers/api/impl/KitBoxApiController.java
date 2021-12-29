package net.renfei.controllers.api.impl;

import net.renfei.controllers.BaseController;
import net.renfei.controllers.api.KitBoxApi;
import net.renfei.model.APIResult;
import net.renfei.model.DnsTypeEnum;
import net.renfei.model.StateCodeEnum;
import net.renfei.model.kitbox.IkAnalyzeVO;
import net.renfei.services.KitBoxService;
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

    public KitBoxApiController(KitBoxService kitBoxService) {
        this.kitBoxService = kitBoxService;
    }

    @Override
    public APIResult<String> getServerDateTime() {
        return new APIResult<>(new Date().toString());
    }

    @Override
    public APIResult<String> getContentByFreeMarkerAndBean(String ftl, String beanJson) {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.getVersion());
        freemarker.cache.StringTemplateLoader templateLoader = new freemarker.cache.StringTemplateLoader();
        configuration.setTemplateLoader(templateLoader);
        configuration.setDefaultEncoding("UTF-8");
        APIResult<String> apiResult;
        try {
            freemarker.template.Template template = new freemarker.template.Template("freemarkerTest", ftl, configuration);
            StringWriter stringWriter = new StringWriter();
            Object object = JacksonUtil.string2Obj(beanJson, Object.class);
            template.process(object, stringWriter);
            apiResult = APIResult.builder()
                    .code(StateCodeEnum.OK)
                    .message("")
                    .data(stringWriter.toString())
                    .build();
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
    public APIResult<String> getDomainDigTrace(String domain) {
        return kitBoxService.execDigTrace(domain, null);
    }

    @Override
    public APIResult<String> getDomainDigTrace(String domain, DnsTypeEnum dnsTypeEnum) {
        return kitBoxService.execDigTrace(domain, dnsTypeEnum);
    }

    @Override
    public APIResult<String> getDomainWhois(String domain) {
        return kitBoxService.execWhois(domain);
    }

    @Override
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
    public APIResult<List<IkAnalyzeVO>> getWordIkAnalyze(String word) {
        if (ObjectUtils.isEmpty(word)) {
            return APIResult.builder()
                    .code(StateCodeEnum.Failure)
                    .message("待分词内容为空，请检查入参。")
                    .build();
        }
        try {
            // TODO ES 搜索引擎集成以后才能集成
//            return new APIResult<>(searchService.getIkAnalyzeTerms(word));
            return new APIResult<>(new ArrayList<>());
        } catch (Exception exception) {
            return APIResult.builder()
                    .code(StateCodeEnum.Error)
                    .message("中文分词服务暂时不可用，请稍后再试。")
                    .build();
        }
    }
}
