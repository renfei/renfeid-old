package net.renfei.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.model.APIResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 工具箱API接口
 *
 * @author renfei
 */
@RequestMapping("/api")
@Tag(name = "开放接口", description = "开放接口")
public interface KitBoxApi {
    @PostMapping("freemarker/test")
    @Operation(summary = "FreeMarker(FTL)在线测试工具", tags = "开放接口")
    APIResult<String> getContentByFreeMarkerAndBean(String ftl, String beanJson);
}
