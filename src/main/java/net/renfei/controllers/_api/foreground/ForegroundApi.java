package net.renfei.controllers._api.foreground;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.renfei.domain.blog.Post;
import net.renfei.model.APIResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前台接口
 *
 * @author renfei
 */
@RequestMapping("/_api/foreground")
@Tag(name = "前台接口", description = "前台接口")
public interface ForegroundApi {
    /**
     * 使用密码获取文章详情
     *
     * @param id       文章ID
     * @param password 文章密码
     * @return
     */
    @PostMapping("blog/{id}/byPassword")
    APIResult<Post> getPostInfoByPassword(@PathVariable("id") Long id, @RequestBody String password);
}
