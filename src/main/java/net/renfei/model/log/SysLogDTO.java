package net.renfei.model.log;

import lombok.Data;

import java.util.Date;

/**
 * @author renfei
 */
@Data
public class SysLogDTO {
    private Long id;

    private String logLevel;

    private String logModule;

    private String logType;

    private String userUuid;

    private String userName;

    private String requMethod;

    private String requUri;

    private String requIp;

    private Date logTime;

    private String logDesc;

    private String requParam;

    private String respParam;

    private String requAgent;
}
