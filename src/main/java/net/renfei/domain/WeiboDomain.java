package net.renfei.domain;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.domain.comment.Comment;
import net.renfei.model.system.SystemTypeEnum;
import net.renfei.domain.weibo.Weibo;
import net.renfei.exception.NotExistException;
import net.renfei.model.ListData;
import net.renfei.repositories.WeiboPostmetaMapper;
import net.renfei.repositories.WeiboPostsMapper;
import net.renfei.repositories.model.WeiboPostmeta;
import net.renfei.repositories.model.WeiboPostmetaExample;
import net.renfei.repositories.model.WeiboPosts;
import net.renfei.repositories.model.WeiboPostsExample;
import net.renfei.application.ApplicationContextUtil;
import net.renfei.utils.ListUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 微博领域对象
 *
 * @author renfei
 */
public final class WeiboDomain {
    private final Weibo weibo;
    private final List<Comment> commentList;
    private final WeiboPostsMapper weiboPostsMapper;
    private final WeiboPostmetaMapper weiboPostmetaMapper;

    {
        weiboPostsMapper = (WeiboPostsMapper) ApplicationContextUtil.getBean("weiboPostsMapper");
        weiboPostmetaMapper = (WeiboPostmetaMapper) ApplicationContextUtil.getBean("weiboPostmetaMapper");
    }

    private WeiboDomain() {
        weibo = null;
        commentList = null;
    }

    public WeiboDomain(Long id) throws NotExistException {
        if (id == null) {
            throw new NotExistException("微博文章不存在");
        }
        WeiboPostsExample weiboPostsExample = new WeiboPostsExample();
        weiboPostsExample.createCriteria()
                .andIdEqualTo(id)
                .andReleaseTimeLessThanOrEqualTo(new Date());
        assert weiboPostsMapper != null;
        WeiboPosts weiboPosts = ListUtils.getOne(weiboPostsMapper.selectByExample(weiboPostsExample));
        if (weiboPosts == null) {
            throw new NotExistException("微博文章不存在");
        }
        Weibo weibo = convert(weiboPosts);
        List<WeiboPostmeta> weiboMetas = getWeiboMetaListByWeiboId(weiboPosts.getId());
        if (weiboMetas != null && weiboMetas.size() > 0) {
            // 目前微博默认只支持一张图片，后续可支持多张图片或者视频等扩展
            WeiboPostmeta weiboMeta = ListUtils.getOne(weiboMetas);
            weibo.setImage(AlbumDomain.imageUrlByAlbumId(Long.parseLong(weiboMeta.getMetaValue())));
        }
        this.weibo = weibo;
        commentList = new CommentDomain(SystemTypeEnum.WEIBO, id).getCommentList();
    }

    public void view() {
        WeiboPostsExample weiboPostsExample = new WeiboPostsExample();
        weiboPostsExample.createCriteria()
                .andIdEqualTo(weibo.getId());
        WeiboPosts weiboPosts = ListUtils.getOne(weiboPostsMapper.selectByExample(weiboPostsExample));
        weiboPosts.setViews(weiboPosts.getViews() + 1);
        weiboPostsMapper.updateByExampleSelective(weiboPosts, weiboPostsExample);
    }

    public void thumbsUp() {
        WeiboPostsExample weiboPostsExample = new WeiboPostsExample();
        weiboPostsExample.createCriteria()
                .andIdEqualTo(weibo.getId());
        WeiboPosts weiboPosts = ListUtils.getOne(weiboPostsMapper.selectByExample(weiboPostsExample));
        weiboPosts.setThumbsUp(weiboPosts.getThumbsUp() + 1);
        weiboPostsMapper.updateByExampleSelective(weiboPosts, weiboPostsExample);
    }

    public void thumbsDown() {
        WeiboPostsExample weiboPostsExample = new WeiboPostsExample();
        weiboPostsExample.createCriteria()
                .andIdEqualTo(weibo.getId());
        WeiboPosts weiboPosts = ListUtils.getOne(weiboPostsMapper.selectByExample(weiboPostsExample));
        weiboPosts.setThumbsDown(weiboPosts.getThumbsDown() + 1);
        weiboPostsMapper.updateByExampleSelective(weiboPosts, weiboPostsExample);
    }

    public static ListData<Weibo> allWeiboList(int pages, int rows) {
        WeiboDomain weiboDomain = new WeiboDomain();
        return weiboDomain.getAllWeiboList(pages, rows);
    }

    private ListData<Weibo> getAllWeiboList(int pages, int rows) {
        WeiboPostsExample weiboPostsExample = new WeiboPostsExample();
        weiboPostsExample.setOrderByClause("release_time DESC");
        weiboPostsExample.createCriteria()
                .andReleaseTimeLessThanOrEqualTo(new Date());
        Page<WeiboPosts> page = PageHelper.startPage(pages, rows);
        weiboPostsMapper.selectByExample(weiboPostsExample);
        List<Weibo> weiboList = new CopyOnWriteArrayList<>();
        for (WeiboPosts weiboPosts : page.getResult()
        ) {
            weiboList.add(convert(weiboPosts));
        }
        ListData<Weibo> weiboListData = new ListData<>(page);
        weiboListData.setData(weiboList);
        return weiboListData;
    }

    private List<WeiboPostmeta> getWeiboMetaListByWeiboId(Long id) {
        if (id == null) {
            return null;
        }
        WeiboPostmetaExample example = new WeiboPostmetaExample();
        example.createCriteria()
                .andPostIdEqualTo(id);
        return weiboPostmetaMapper.selectByExampleWithBLOBs(example);
    }

    private Weibo convert(WeiboPosts weiboPosts) {
        Weibo weibo = new Weibo();
        weibo.setId(weiboPosts.getId());
        weibo.setContent(weiboPosts.getContent());
        weibo.setReleaseTime(weiboPosts.getReleaseTime());
        weibo.setViews(weiboPosts.getViews());
        weibo.setThumbsUp(weiboPosts.getThumbsUp());
        weibo.setThumbsDown(weiboPosts.getThumbsDown());
        return weibo;
    }

    public Weibo getWeibo() {
        return weibo;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }
}
