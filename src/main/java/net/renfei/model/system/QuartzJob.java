package net.renfei.model.system;

import lombok.Data;

import java.util.Map;

/**
 * @author renfei
 */
@Data
public class QuartzJob {
    private String jobName;
    private String reference;
    private String jobGroup;
    private String cron;
    private Map<String, Object> param;
}
