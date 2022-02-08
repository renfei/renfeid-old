package net.renfei.controllers.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import net.renfei.exception.IP2LocationException;
import net.renfei.ip2location.IPResult;
import net.renfei.model.kitbox.IcpQueryVo;
import net.renfei.model.kitbox.IkAnalyzeVO;
import net.renfei.model.system.RegionVO;
import net.renfei.services.IP2LocationService;
import net.renfei.services.KitBoxService;
import net.renfei.services.SearchService;
import net.renfei.services.SysService;
import net.renfei.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * GraphQL查询入口
 *
 * @author renfei
 */
@Component
public class Query implements GraphQLQueryResolver {
    private final SysService sysService;
    private final KitBoxService kitBoxService;
    private final SearchService searchService;
    private final IP2LocationService ip2LocationService;
    @Autowired
    protected HttpServletRequest request;

    public Query(SysService sysService,
                 KitBoxService kitBoxService,
                 SearchService searchService,
                 IP2LocationService ip2LocationService) {
        this.sysService = sysService;
        this.kitBoxService = kitBoxService;
        this.searchService = searchService;
        this.ip2LocationService = ip2LocationService;
    }

    public List<RegionVO> rootRegion() {
        return sysService.getRegion("");
    }

    public List<RegionVO> region(String regionCode) {
        return sysService.getRegion(regionCode);
    }

    public IPResult ipInfo(String ip) throws IP2LocationException {
        if (ip == null) {
            return ip2LocationService.ipQuery(IpUtils.getIpAddress(request));
        }
        return ip2LocationService.ipQuery(ip);
    }

    public List<IkAnalyzeVO> wordIkAnalyze(String word) throws IOException {
        if (word == null) {
            return new ArrayList<>();
        }
        return searchService.getIkAnalyzeTerms(word);
    }

    public IcpQueryVo.IcpInfo icpInfo(String domain) {
        if (domain == null) {
            return null;
        }
        return kitBoxService.queryIcpInfo(domain, false);
    }
}
