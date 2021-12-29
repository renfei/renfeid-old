package net.renfei.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.nashorn.internal.ir.annotations.Ignore;
import net.renfei.model.APIResult;
import net.renfei.model.DnsTypeEnum;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("dns/whois/{domain}")
    @Operation(summary = "域名Whois信息查询工具",
            description = "域名Whois信息查询工具，查询域名是否已经被注册，以及注册域名的详细信息的数据库（如域名所有人、域名注册商、域名注册日期和过期日期等）。通过域名Whois服务器查询，可以查询域名归属者联系方式，以及注册和到期时间。",
            tags = "开放接口")
    APIResult<String> getDomainWhois(@PathVariable(value = "domain") String domain);

    @GetMapping("uuid")
    @Operation(summary = "UUID/GUID 生成接口", description = "批量生成 UUID/GUID", tags = "开放接口")
    APIResult<List<String>> getUuid(@RequestParam(value = "quantity", required = false) String quantity,
                                    @RequestParam(value = "upperCase", required = false) Boolean upperCase,
                                    @RequestParam(value = "hyphen", required = false) Boolean hyphen);
}
