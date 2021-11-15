package net.renfei.services.system;

import net.renfei.model.system.RegionVO;
import net.renfei.repositories.SysRegionMapper;
import net.renfei.repositories.model.SysRegion;
import net.renfei.repositories.model.SysRegionExample;
import net.renfei.services.BaseService;
import net.renfei.services.SysService;
import net.renfei.utils.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 系统基础服务
 *
 * @author renfei
 */
@Service
public class SysServiceImpl extends BaseService implements SysService {
    private final SysRegionMapper regionMapper;

    public SysServiceImpl(SysRegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    /**
     * 获取行政代码
     *
     * @param regionCode 行政代码
     * @return 下一级行政代码列表
     */
    @Override
    public List<RegionVO> getRegion(String regionCode) {
        if (regionCode.length() != 6) {
            regionCode = null;
        }
        SysRegionExample example = new SysRegionExample();
        example.setOrderByClause("region_code");
        if (ObjectUtils.isEmpty(regionCode)) {
            example.createCriteria().andRegionCodeLike("__0000");
        } else if (regionCode.endsWith("0000")) {
            // 直辖市单独处理：北京、天津、上海、重庆、香港、澳门、
            if (regionCode.startsWith("110") || regionCode.startsWith("120")
                    || regionCode.startsWith("310") || regionCode.startsWith("500")
                    || regionCode.startsWith("810") || regionCode.startsWith("820")) {
                example.createCriteria().andRegionCodeLike(regionCode.substring(0, 2) + "____");
            } else {
                example.createCriteria().andRegionCodeLike(regionCode.substring(0, 2) + "__00");
            }
        } else if (regionCode.endsWith("00")) {
            example.createCriteria().andRegionCodeLike(regionCode.substring(0, 4) + "__");
        } else {
            example.createCriteria().andRegionCodeEqualTo(regionCode);
        }
        List<SysRegion> regionList = regionMapper.selectByExample(example);
        if (ObjectUtils.isEmpty(regionList)) {
            return new CopyOnWriteArrayList<>();
        }
        List<RegionVO> regionVoList = new CopyOnWriteArrayList<>();
        for (SysRegion region : regionList
        ) {
            if (region.getRegionCode().equals(regionCode)) {
                if (!"460400".equals(regionCode)
                        && !"441900".equals(regionCode)
                        && !"442000".equals(regionCode)) {
                    continue;
                }
            }
            RegionVO regionVO = new RegionVO();
            BeanUtils.copyProperties(region, regionVO);
            regionVoList.add(regionVO);
        }
        if ("410000".equals(regionCode)) {
            // 单独处理济源市（县级市）的情况
            example = new SysRegionExample();
            example.createCriteria().andRegionCodeEqualTo("419001");
            SysRegion region = CommonUtil.getOne(regionMapper.selectByExample(example));
            RegionVO regionVO = new RegionVO();
            BeanUtils.copyProperties(region, regionVO);
            regionVoList.add(regionVO);
        } else if ("420000".equals(regionCode)) {
            // 单独处理湖北省的情况
            example = new SysRegionExample();
            example.createCriteria().andRegionCodeLike("4290__");
            List<SysRegion> regionXinJiangList = regionMapper.selectByExample(example);
            for (SysRegion region : regionXinJiangList
            ) {
                RegionVO regionVO = new RegionVO();
                BeanUtils.copyProperties(region, regionVO);
                regionVoList.add(regionVO);
            }
        } else if ("650000".equals(regionCode)) {
            // 单独处理新疆维吾尔自治区直辖县级市的情况
            example = new SysRegionExample();
            example.createCriteria().andRegionCodeLike("65900_");
            List<SysRegion> regionXinJiangList = regionMapper.selectByExample(example);
            for (SysRegion region : regionXinJiangList
            ) {
                RegionVO regionVO = new RegionVO();
                BeanUtils.copyProperties(region, regionVO);
                regionVoList.add(regionVO);
            }
        } else if ("460000".equals(regionCode)) {
            // 单独处理海南省直辖县级市的情况
            example = new SysRegionExample();
            example.createCriteria().andRegionCodeLike("4690__");
            List<SysRegion> regionXinJiangList = regionMapper.selectByExample(example);
            for (SysRegion region : regionXinJiangList
            ) {
                RegionVO regionVO = new RegionVO();
                BeanUtils.copyProperties(region, regionVO);
                regionVoList.add(regionVO);
            }
        } else if ("830000".equals(regionCode)) {
            // 单独处理台湾省直辖县级市的情况
            example = new SysRegionExample();
            example.createCriteria().andRegionCodeLike("8390__");
            List<SysRegion> regionXinJiangList = regionMapper.selectByExample(example);
            for (SysRegion region : regionXinJiangList
            ) {
                RegionVO regionVO = new RegionVO();
                BeanUtils.copyProperties(region, regionVO);
                regionVoList.add(regionVO);
            }
        } else if ("429004".equals(regionCode) || "429021".equals(regionCode)
                || "429006".equals(regionCode) || "429005".equals(regionCode)
                || "659001".equals(regionCode) || "659009".equals(regionCode)
                || "659008".equals(regionCode) || "659007".equals(regionCode)
                || "659006".equals(regionCode) || "659005".equals(regionCode)
                || "659004".equals(regionCode) || "659003".equals(regionCode)
                || "659002".equals(regionCode) || "469001".equals(regionCode)
                || "469029".equals(regionCode) || "469028".equals(regionCode)
                || "469027".equals(regionCode) || "469026".equals(regionCode)
                || "469025".equals(regionCode) || "469024".equals(regionCode)
                || "469023".equals(regionCode) || "469022".equals(regionCode)
                || "469021".equals(regionCode) || "469007".equals(regionCode)
                || "469006".equals(regionCode) || "469005".equals(regionCode)
                || "469002".equals(regionCode) || "839001".equals(regionCode)
                || "839013".equals(regionCode) || "839012".equals(regionCode)
                || "839011".equals(regionCode) || "839009".equals(regionCode)
                || "839008".equals(regionCode) || "839007".equals(regionCode)
                || "839006".equals(regionCode) || "839005".equals(regionCode)
                || "839004".equals(regionCode) || "839003".equals(regionCode)
                || "429024".equals(regionCode) || "429025".equals(regionCode)
                || "429026".equals(regionCode) || "839002".equals(regionCode)
                || "419001".equals(regionCode)) {
            // 单独处理直辖县级市的情况
            example = new SysRegionExample();
            example.createCriteria().andRegionCodeEqualTo(regionCode);
            SysRegion region = CommonUtil.getOne(regionMapper.selectByExample(example));
            RegionVO regionVO = new RegionVO();
            BeanUtils.copyProperties(region, regionVO);
            regionVoList.add(regionVO);
        }
        return regionVoList;
    }
}
