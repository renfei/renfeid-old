package net.renfei.services;

import net.renfei.model.PaginationVO;
import net.renfei.utils.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 页码服务
 */
@Service("paginationService")
public class PaginationService extends BaseService {
    /**
     * 页码生成
     *
     * @param current    当前页码
     * @param totalPages 总页码
     * @param link       翻页链接
     * @return
     */
    public List<PaginationVO> getPagination(String current, int totalPages, String link) {
        return getPagination(NumberUtils.parseInt(current, 1), totalPages, link);
    }

    /**
     * 页码生成
     *
     * @param current    当前页码
     * @param totalPages 总页码
     * @param link       翻页链接
     * @return
     */
    public List<PaginationVO> getPagination(int current, int totalPages, String link) {
        List<PaginationVO> paginationVOS = new ArrayList<>();
        //添加翻页
        PaginationVO paginationVO1 = new PaginationVO();
        paginationVO1.setActive(false);
        paginationVO1.setLink((current - 1) <= 0 ? "javascript:void(0)" : link + (current - 1));
        paginationVO1.setPage("&lt;&lt;");
        paginationVOS.add(paginationVO1);
        //先往前取4个
        for (int i = 4; i > 0; i--) {
            int page = current - i;
            if (page <= 0) {
                continue;
            } else {
                PaginationVO paginationVOLeft = new PaginationVO();
                paginationVOLeft.setLink(link + page);
                paginationVOLeft.setActive(false);
                paginationVOLeft.setPage(page + "");
                paginationVOS.add(paginationVOLeft);
            }
        }
        //添加当前页
        PaginationVO paginationVOCurrent = new PaginationVO();
        paginationVOCurrent.setPage(current + "");
        paginationVOCurrent.setActive(true);
        paginationVOCurrent.setLink("javascript:void(0)");
        paginationVOS.add(paginationVOCurrent);
        int page = current;
        //后面补齐
        for (int i = paginationVOS.size(); i < 9; i++) {
            page++;
            if (page > totalPages) {
                break;
            }
            PaginationVO paginationVORight = new PaginationVO();
            paginationVORight.setLink(link + page);
            paginationVORight.setActive(false);
            paginationVORight.setPage(page + "");
            paginationVOS.add(paginationVORight);
        }
        //前面取4个是理想状态，有可能后面不足4个，这就需要继续从前面找替补
        for (int i = 0; i < 9 - paginationVOS.size(); i++) {
            PaginationVO paginationVO = paginationVOS.get(1);
            int p = Integer.parseInt(paginationVO.getPage());
            if (p - (i + 1) <= 0) {
                break;
            } else {
                paginationVO.setLink(link + p);
                paginationVO.setActive(false);
                paginationVO.setPage(p + "");
                paginationVOS.add(1, paginationVO);
            }
        }
        //添加翻页
        PaginationVO paginationVO2 = new PaginationVO();
        paginationVO2.setActive(false);
        paginationVO2.setLink(current + 1 > totalPages ? "javascript:void(0)" : link + (current + 1));
        paginationVO2.setPage("&gt;&gt;");
        paginationVOS.add(paginationVO2);
        return paginationVOS;
    }
}
