package net.renfei.controllers._api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 超管系统接口（后台接口）
 *
 * @author renfei
 */
@RequestMapping("/_/api")
@Tag(name = "后台接口", description = "后台接口")
public interface BackgroundApi {
}
