package net.renfei.model.log;

/**
 * 操作日志中操作类型
 *
 * @author renfei
 */
public enum OperationTypeEnum {
    /**
     * 创建操作
     */
    CREATE("创建"),
    /**
     * 查询操作
     */
    RETRIEVE("取回"),
    /**
     * 修改操作
     */
    UPDATE("修改"),
    /**
     * 删除操作
     */
    DELETE("删除");
    private final String describe;

    OperationTypeEnum(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }
}
