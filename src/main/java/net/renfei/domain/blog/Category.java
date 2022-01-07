package net.renfei.domain.blog;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import net.renfei.model.SecretLevelEnum;

/**
 * @author renfei
 */
@Data
@Builder
public class Category {
    private Long id;
    private String enName;
    private String zhName;
    private SecretLevelEnum secretLevelEnum;

    @Tolerate
    Category() {
    }
}
