package net.renfei.services.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import net.renfei.config.SystemConfig;
import net.renfei.services.BaseService;

/**
 * 阿里云服务
 *
 * @author renfei
 */
public abstract class AbstractAliyunService extends BaseService {
    protected IAcsClient client;

    protected AbstractAliyunService(SystemConfig systemConfig) {
        client = new DefaultAcsClient(DefaultProfile.getProfile(
                systemConfig.getAliyun().getGreen().getRegionId(),
                systemConfig.getAliyun().getAccessKeyId(),
                systemConfig.getAliyun().getAccessKeySecret()));
    }

    protected AbstractAliyunService(IClientProfile profile) {
        client = new DefaultAcsClient(profile);
    }
}
