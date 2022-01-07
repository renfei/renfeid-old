package net.renfei.model;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: ReportPublicKeyVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
public class ReportPublicKeyVO implements Serializable {
    private String secretKeyId;
    private String publicKey;
}
