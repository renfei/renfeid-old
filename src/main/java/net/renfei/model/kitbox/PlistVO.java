package net.renfei.model.kitbox;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>Title: PlistVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlistVO implements Serializable {
    private String appname;
    private String version;
    private String bundleid;
    private String ipa;
    private String icon;
}
