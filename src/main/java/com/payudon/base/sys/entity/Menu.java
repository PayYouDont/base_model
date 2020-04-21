package com.payudon.base.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payudon.base.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName Menu
 * @Description TODO
 * @Author pay
 * @DATE 2019/10/9 17:01
 **/
@Data
@Entity
@Table(name="sys_menu")
@EqualsAndHashCode(callSuper = false)
public class Menu extends BaseEntity {
    /**
     * 菜单标题
     */
    private String title;
    /**
    * 菜单内容描述
    */
    private String content;
    /**
     * 父节点ID
     */
    private Integer pid;

    /**
     * 排序
     */
    private Integer number;

    /**
     * 关联地址
     */
    private String url;

    /**
     * 默认图标
     */
    private String icon;

    /**
     * 授权
     */
    private String permissions;

    private String state = "open";

    private Boolean checked = false;

    /*@ManyToMany(mappedBy = "menus")
    private List<Role> roles;*/
    @OneToMany(targetEntity = MenuRoleRelation.class)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    @JsonIgnore
    private List<MenuRoleRelation> menuRoleRelations;
    @OneToMany
    @JoinColumn(name = "pid",referencedColumnName = "id")
    @JsonIgnore
    private List<Menu> children;
}
