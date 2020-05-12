package cn.ann.financial.manager.commons.constant;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-21:22:30
 */
public class ProviderConstant {
    /** 交易类型——收入 */
    public static final int DEAL_TYPE_INCOME = 1;
    /** 交易类型——支出 */
    public static final int DEAL_TYPE_EXPAND = 2;

    /** 性别——男 */
    public static final int GENDER_MALE = 1;
    /** 性别——女 */
    public static final int GENDER_FEMALE = 2;

    /** 角色——创建者 */
    public static final long ROLE_CREATOR = 1L;
    /** 角色——管理员 */
    public static final long ROLE_ADMIN = 2L;
    /** 角色——家庭成员 */
    public static final long ROLE_MEMBER = 3L;
    /** 角色——普通用户 */
    public static final long ROLE_USER = 4L;
    /** 角色——公共 */
    public static final long ROLE_PUBLIC = 5L;
    /** 角色——家庭成员公共 */
    public static final long ROLE_FAMILY_PUBLIC = 6L;

    /** 计划状态——未完成 */
    public static final int PLAN_NO_FINISH = 2;
    /** 计划状态——已完成 */
    public static final int PLAN_FINISH = 1;
    /** 计划状态——已取消 */
    public static final int PLAN_CANCEL = 3;

    /** 公开 */
    public static final int OPEN = 1;
    /** 公开 */
    public static final int NO_OPEN = 0;

    /** 消息已读 */
    public static final int MESSAGE_READ = 1;
    /** 消息未读 */
    public static final int MESSAGE_NO_READ = 0;

    /** 用户可用（已激活） */
    public static final Integer USER_ENABLE = 1;
    /** 用户不可用（未激活） */
    public static final Integer USER_DISABLE = 0;

    private ProviderConstant() {}
}
