package net.renfei.services.leaf;

import net.renfei.config.SystemConfig;
import net.renfei.exception.BusinessException;
import net.renfei.repositories.LeafAllocMapper;
import net.renfei.services.BaseService;
import net.renfei.services.LeafService;
import net.renfei.services.leaf.common.Result;
import net.renfei.services.leaf.common.ZeroIDGen;
import net.renfei.services.leaf.segment.SegmentIDGenImpl;
import net.renfei.services.leaf.snowflake.SnowflakeIDGenImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 美团开源的 Leaf 全局发号服务
 *
 * @author renfei
 */
@Service
public class LeafServiceImpl extends BaseService implements LeafService {
    private static final Logger logger = LoggerFactory.getLogger(LeafServiceImpl.class);
    private final IDGen idGen;

    public LeafServiceImpl(SystemConfig systemConfig,
                           LeafAllocMapper mapper) {
        boolean segmentFlag = systemConfig.getLeaf().getSegment().getEnable();
        boolean snowflakeFlag = systemConfig.getLeaf().getSnowflake().getEnable();
        if (segmentFlag) {
            idGen = new SegmentIDGenImpl();
            ((SegmentIDGenImpl) idGen).setMapper(mapper);
            if (idGen.init()) {
                logger.info("Segment Service Init Successfully");
            } else {
                throw new BusinessException("Segment Service Init Fail");
            }
        } else if (snowflakeFlag) {
            String zkAddress = systemConfig.getLeaf().getSnowflake().getZk().getAddress();
            int port = systemConfig.getLeaf().getSnowflake().getPort();
            idGen = new SnowflakeIDGenImpl(zkAddress, port);
            if (idGen.init()) {
                logger.info("Snowflake Service Init Successfully");
            } else {
                throw new BusinessException("Snowflake Service Init Fail");
            }
        } else {
            idGen = new ZeroIDGen();
            logger.info("Zero ID Gen Service Init Successfully");
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
