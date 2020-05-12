package cn.ann.financial.manager.commons.constant;

import cn.ann.financial.manager.commons.constant.serializer.IdentitySerializer;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.stereotype.Component;

/**
 * Description：身份
 * <p>
 * Date: 2020-4-27 21:05
 *
 * @author 88475
 * @version v1.0
 */
@JsonSerialize(using = IdentitySerializer.class)
public enum Identity {
    CREATOR(ProviderConstant.ROLE_CREATOR, "creator", "创建者"),
    ADMIN(ProviderConstant.ROLE_ADMIN, "admin", "管理员"),
    MEMBER(ProviderConstant.ROLE_MEMBER, "member", "家庭成员"),
    USER(ProviderConstant.ROLE_USER, "user", "普通用户");

    private Long id;
    private String enName;
    private String description;

    Identity(Long id, String enName, String description) {
        this.id = id;
        this.enName = enName;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getEnName() {
        return enName;
    }
}
