package com.payudon.base.sys.entity;

import com.payudon.base.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @ClassName User
 * @Description TODO
 * @Author pay
 * @DATE 2019/10/9 16:47
 **/
@Data
@Entity
@Table(name="sys_user")
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {
    //账号
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 用户编号
     */
    private String userCode;
    /**
     * 性别
     */
    private Integer sex;

    /**
     * 电话
     */
    private String phone;

    /**
     * 用户管理区域编号
     */
    private String areaCode;
    /**
     * 角色id
     */
    private Integer roleId;
    @Transient
    private List<Role> roleList;
}
