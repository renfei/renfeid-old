package net.renfei.domain.user;

import lombok.Data;

/**
 * 用户域实体类
 *
 * @author renfei
 */
@Data
public final class User {
    private Long id;
    private String userName;
    private String email;
    private String webSite;

    private User() {
    }

    public User(Long id) {
    }
}
