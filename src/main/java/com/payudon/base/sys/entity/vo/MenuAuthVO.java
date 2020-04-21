package com.payudon.base.sys.entity.vo;

import com.payudon.base.sys.entity.Menu;
import com.payudon.base.sys.entity.MenuRoleRelation;
import com.payudon.base.sys.entity.Role;
import lombok.Data;

/**
 * @ClassName MenuAuthVO
 * @Description TODO
 * @Author pay
 * @DATE 2020/4/8 11:40
 **/
@Data
public class MenuAuthVO {
    private Integer menuId;
    private Integer menuPid;
    private Integer menuNumber;
    private String menuTitle;
    private String permissions;
    private boolean authView;
    private boolean authModify;
    private boolean authAdd;
    private boolean authDelete;
    public MenuRoleRelation createRelation(Role role, Menu menu){
        MenuRoleRelation relation = new MenuRoleRelation ();
        relation.setRole (role);
        relation.setMenu (menu);
        relation.setIsAdd (authAdd);
        relation.setIsView (authView);
        relation.setIsModify (authModify);
        relation.setIsDelete (authDelete);
        return relation;
    }
    public void test(){

    }
}
