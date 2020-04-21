package com.payudon.base.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payudon.base.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName Role
 * @Description TODO
 * @Author pay
 * @DATE 2019/10/9 16:51
 **/
@Data
@Entity
@Table(name="sys_role")
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity {
    /**
     * 角色名
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;
    /**
     * 是否有效
     */
    private Integer state;
    /**
     * 角色在系统中的等级 0:不能删除和修改（一般指超级管理）
     */
    private String level;

    /*@ManyToMany
    @JoinTable(name = "sys_menu_role_relation",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")})
    @JsonIgnore
    private List<Menu> menus;*/

    @OneToMany(targetEntity = MenuRoleRelation.class)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    @JsonIgnore
    private List<MenuRoleRelation> menuRoleRelations;
}
