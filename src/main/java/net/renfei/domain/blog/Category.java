package net.renfei.domain.blog;

import lombok.*;
import lombok.experimental.Tolerate;
import net.renfei.model.SecretLevelEnum;

import java.io.Serializable;

/**
 * @author renfei
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category implements Serializable {
    private Long id;
    private String enName;
    private String zhName;
    private SecretLevelEnum secretLevelEnum;
}
