package net.renfei.domain.blog;

import lombok.Builder;
import lombok.Data;
import net.renfei.model.SecretLevel;

/**
 * @author renfei
 */
@Data
@Builder
public class Category {
    private Long id;
    private String enName;
    private String zhName;
    private SecretLevel secretLevel;
}
