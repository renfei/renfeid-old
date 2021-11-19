package net.renfei.model;

/**
 * 保密等级
 *
 * @author renfei
 */
public enum SecretLevel {
    /**
     * 非密
     */
    UNCLASSIFIED(1),
    /**
     * 秘密
     */
    SECRET(2),
    /**
     * 机密
     */
    CONFIDENTIAL(3);

    private final int LEVEL;

    SecretLevel(int level) {
        this.LEVEL = level;
    }

    public int getLevel() {
        return LEVEL;
    }

    public static SecretLevel valueOf(int level) {
        switch (level) {
            case 2:
                return SECRET;
            case 3:
                return CONFIDENTIAL;
            default:
                return UNCLASSIFIED;
        }
    }
}
