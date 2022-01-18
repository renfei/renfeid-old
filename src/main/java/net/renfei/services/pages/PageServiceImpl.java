package net.renfei.services.pages;

import net.renfei.repositories.SysPagesMapper;
import net.renfei.repositories.model.SysPagesExample;
import net.renfei.repositories.model.SysPagesWithBLOBs;
import net.renfei.services.BaseService;
import net.renfei.services.PageService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 页面服务
 *
 * @author renfei
 */
@Service
public class PageServiceImpl extends BaseService implements PageService {
    private final SysPagesMapper pagesMapper;

    public PageServiceImpl(SysPagesMapper pagesMapper) {
        this.pagesMapper = pagesMapper;
    }

    @Override
    public List<SysPagesWithBLOBs> getAllPageNotCache() {
        SysPagesExample example = new SysPagesExample();
        example.setOrderByClause("page_date DESC");
        example.createCriteria()
                .andPageDateLessThanOrEqualTo(new Date());
        return pagesMapper.selectByExampleWithBLOBs(example);
    }
}
