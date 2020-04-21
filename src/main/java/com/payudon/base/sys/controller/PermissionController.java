package com.payudon.base.sys.controller;

import com.payudon.base.sys.entity.MenuRoleRelation;
import com.payudon.base.sys.service.MenuRoleRelationService;
import com.payudon.base.common.annotation.CURD;
import com.payudon.base.common.annotation.EditPath;
import com.payudon.base.common.annotation.ViewPath;
import com.payudon.base.common.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName RoleController
 * @Description TODO
 * @Author pay
 * @DATE 2019/11/7 17:29
 */

@Api(tags = "权限管理")
@RestController
@RequestMapping("permission")
public class PermissionController extends BaseController<MenuRoleRelation,Integer> {
    @Resource
    @ViewPath("back/sys/menu_role_relation_list")
    @EditPath("back/sys/menu_role_relation_edit")
    @CURD
    private MenuRoleRelationService service;

}
