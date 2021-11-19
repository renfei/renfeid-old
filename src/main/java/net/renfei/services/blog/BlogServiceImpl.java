package net.renfei.services.blog;

import lombok.extern.slf4j.Slf4j;
import net.renfei.services.BaseService;
import net.renfei.services.BlogService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 博客服务的实现
 *
 * @author renfei
 */
@Lazy
@Slf4j
@Service
public class BlogServiceImpl extends BaseService implements BlogService {
}
