package net.renfei.domain.user;

import lombok.Data;
import net.renfei.model.SecretLevelEnum;

import java.io.Serializable;

/**
 * 用户域实体类
 *
 * @author renfei
 */
@Data
public final class User implements Serializable {
    private Long id;
    private String userName;
    private String uuid;
    private String email;
    private String webSite;
    private String ucScript;
    private SecretLevelEnum secretLevelEnum;

    public User() {
    }

    public User(Long id) {
    }
}
