package cn.ann.financial.manager.provider.user.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Description：
 * 
 * Date: 2020-5-4 16:41
 * @author 88475
 * @version v1.0
 */

/**
    * 用户角色表
    */
@Data
@Table(name = "tb_user_role")
public class TbUserRole implements Serializable {
    private static final long serialVersionUID = 4894760077187302551L;

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户 ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色 ID
     */
    @Column(name = "role_id")
    private Long roleId;
}