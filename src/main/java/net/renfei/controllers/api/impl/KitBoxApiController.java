package net.renfei.controllers.api.impl;

import net.renfei.controllers.BaseController;
import net.renfei.controllers.api.KitBoxApi;
import net.renfei.model.APIResult;
import net.renfei.model.DnsTypeEnum;
import net.renfei.model.StateCodeEnum;
import net.renfei.services.KitBoxService;
import net.renfei.utils.JacksonUtil;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;
import java.util.Date;

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
}
