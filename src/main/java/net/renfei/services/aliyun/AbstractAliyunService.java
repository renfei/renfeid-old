package net.renfei.services.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import net.renfei.services.BaseService;

/**
 * 阿里云服务
 *
 * @author renfei
 */
public abstract class AbstractAliyunService extends BaseService {
    protected IAcsClient client;

    protected AbstractAliyunService() {
        client = new DefaultAcsClient(DefaultProfile.getProfile(
                SYSTEM_CONFIG.getAliyun().getGreen().getRegionId(),
                SYSTEM_CONFIG.getAliyun().getAccessKeyId(),
                SYSTEM_CONFIG.getAliyun().getAccessKeySecret()));
    }

    protected AbstractAliyunService(IClientProfile profile) {
        client = new DefaultAcsClient(profile);
    }
}
