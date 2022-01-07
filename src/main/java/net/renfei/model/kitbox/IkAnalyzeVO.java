package net.renfei.model.kitbox;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>Title: IkAnalyzeVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2020-11-09 00:01
 */
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IkAnalyzeVO implements Serializable {
    private String word;
    private String type;
}
