package net.renfei.services.leaf;

import lombok.extern.slf4j.Slf4j;
import net.renfei.config.SystemConfig;
import net.renfei.repositories.LeafAllocMapper;
import net.renfei.services.BaseService;
import net.renfei.services.LeafService;
import net.renfei.services.leaf.common.Result;
import net.renfei.services.leaf.common.ZeroIDGen;
import net.renfei.services.leaf.segment.SegmentIDGenImpl;
import net.renfei.services.leaf.snowflake.SnowflakeIDGenImpl;
import org.springframework.stereotype.Service;

/**
 * 美团开源的 Leaf 全局发号服务
 *
 * @author renfei
 */
@Slf4j
@Service
public class LeafServiceImpl extends BaseService implements LeafService {
    private final IDGen idGen;

    public LeafServiceImpl(SystemConfig systemConfig,
                           LeafAllocMapper mapper) {
        boolean segmentFlag = systemConfig.getLeaf().getSegment().getEnable();
        boolean snowflakeFlag = systemConfig.getLeaf().getSnowflake().getEnable();
        if (segmentFlag) {
            idGen = new SegmentIDGenImpl();
            ((SegmentIDGenImpl) idGen).setMapper(mapper);
            if (idGen.init()) {
                log.info("Segment Service Init Successfully");
            } else {
                throw new RuntimeException("Segment Service Init Fail");
            }
        } else if (snowflakeFlag) {
            String zkAddress = systemConfig.getLeaf().getSnowflake().getZk().getAddress();
            int port = systemConfig.getLeaf().getSnowflake().getPort();
            idGen = new SnowflakeIDGenImpl(zkAddress, port);
            if (idGen.init()) {
                log.info("Snowflake Service Init Successfully");
            } else {
                throw new RuntimeException("Snowflake Service Init Fail");
            }
        } else {
            idGen = new ZeroIDGen();
            log.info("Zero ID Gen Service Init Successfully");
        }
    }

    /**
     * 发号服务
     *
     * @return
     */
    @Override
    public Result getId() {
        return idGen.get(SYSTEM_CONFIG.getLeafKey());
    }

    /**
     * 发号服务
     *
     * @param key
     * @return
     */
    @Override
    public Result getId(String key) {
        return idGen.get(key);
    }
}
