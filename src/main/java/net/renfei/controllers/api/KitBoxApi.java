package net.renfei.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.nashorn.internal.ir.annotations.Ignore;
import net.renfei.model.APIResult;
import net.renfei.model.DnsTypeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Ignore
    @GetMapping("server/dateTime")
    APIResult<String> getServerDateTime();

    @PostMapping("freemarker/test")
    @Operation(summary = "FreeMarker(FTL)在线测试工具", tags = "开放接口")
    APIResult<String> getContentByFreeMarkerAndBean(String ftl, String beanJson);

    @GetMapping("dns/dig/{domain}")
    @Operation(summary = "域名 dig+trace", description = "使用该接口可以查询域名的解析过程", tags = "开放接口")
    APIResult<String> getDomainDigTrace(@PathVariable(value = "domain") String domain);

    @GetMapping("dns/dig/{domain}/{dnsTypeEnum}")
    @Operation(summary = "域名 dig+trace", description = "使用该接口可以查询域名的解析过程", tags = "开放接口")
    APIResult<String> getDomainDigTrace(@PathVariable(value = "domain") String domain,
                                        @PathVariable(value = "dnsTypeEnum", required = false) DnsTypeEnum dnsTypeEnum);
}
