package net.renfei.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author renfei
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OGProtocol implements Serializable {
    private String title;
    private String description;
    private String type;
    private String image;
    private String url;
    private Date releaseDate;
    private String author;
    private String locale;
    private String siteName;

    public String getImage() {
        return image == null ? "https://cdn.renfei.net/Logo/ogimage.png" : image;
    }
}
