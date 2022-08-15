package com.olcow.tsso.constant;

public class Constant {

    /**
     * sha256加密算法
     */
    public static final String ENCRYPT_SHA256 = "SHA-256";

    /**
     * 未激活用户
     */
    public static final Integer USER_ROLE_INACTIVATED = 1;

    /**
     * 普通用户
     */
    public static final Integer USER_ROLE_NORMAL = 2;

    /**
     * 会员
     */
    public static final Integer USER_ROLE_VIP = 3;

    /**
     * 验证成功
     */
    public static final Integer VERIFIER_SUCCESS = 1;

    /**
     * 验证失败
     */
    public static final Integer VERIFIER_ERROR = 2;

    /**
     * 验证过期
     */
    public static final Integer VERIFIER_EXPIRED = 3;
}
