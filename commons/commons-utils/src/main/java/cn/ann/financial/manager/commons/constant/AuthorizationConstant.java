package cn.ann.financial.manager.commons.constant;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-22:10:23
 */
public class AuthorizationConstant {
    /** 授权类型——密码模式 */
    public static final String GRANT_TYPE_PASSWORD = "password";
    /** client_id */
    public static final String CLIENT_ID = "financial-manager";
    /** client_secret */
    public static final String CLIENT_SECRET = "ann-zhgy";

    /** token 超时时间，单位秒 */
    public static final Long TOKEN_TIMEOUT = 3600L;
    /** token 获取 url */
    public static final String TOKEN_URL = "http://localhost:9502/oauth/token";

    public static final String REQUEST_ATTRIBUTE_TOKEN_KEY = "OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE";
}
