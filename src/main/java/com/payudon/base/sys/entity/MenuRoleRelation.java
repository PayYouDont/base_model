package com.payudon.base.sys.entity;

import com.payudon.base.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName MenuRoleRelation
 * @Description TODO
 * @Author pay
 * @DATE 2019/10/9 17:22
 **/
@Data
@Entity
@Table(name="sys_menu_role_relation")
@EqualsAndHashCode(callSuper = false)
public class MenuRoleRelation extends BaseEntity {
    /**
     * 菜单id
     */
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id",nullable=false)
    private Menu menu;
    /**
     * 角色id
     */
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id",nullable=false)
    private Role role;
    /**
     * 查看权
     */
    private Boolean isView;

    /**
     * 修改权
     */
    private Boolean isModify;

    /**
     * 添加权
     */
    private Boolean isAdd;

    /**
     * 删除权
     */
    private Boolean isDelete;

    @Transient
    public Set<String> getPermsSet(String perms) {
        Set<String> permsSet = new HashSet<> ();
        if(isView) {
            permsSet.add(perms+":view");
        }
        if(isAdd) {
            permsSet.add(perms+":add");
        }
        if(isModify) {
            permsSet.add(perms+":edit");
        }
        if(isDelete) {
            permsSet.add(perms+":delete");
        }
        return permsSet;
    }
}
