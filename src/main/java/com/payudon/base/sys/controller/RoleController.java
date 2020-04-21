package com.payudon.base.sys.controller;

import com.payudon.base.sys.entity.Role;
import com.payudon.base.sys.entity.vo.MenuAuthVO;
import com.payudon.base.sys.service.MenuRoleRelationService;
import com.payudon.base.sys.service.RoleService;
import com.payudon.base.common.annotation.CURD;
import com.payudon.base.common.annotation.EditPath;
import com.payudon.base.common.annotation.ViewPath;
import com.payudon.base.common.controller.BaseController;
import com.payudon.base.common.util.JsonWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RoleController
 * @Description TODO
 * @Author pay
 * @DATE 2019/11/7 17:29
 **/
@Api(tags = "角色管理")
@RestController
@RequestMapping("role")
public class RoleController extends BaseController<Role,Integer> {
    @Resource
    @ViewPath("back/sys/role_list")
    @EditPath("back/sys/role_edit")
    @CURD
    private RoleService service;
    @Resource
    private MenuRoleRelationService relationService;

    @GetMapping("toAuth")
    public ModelAndView toAuth(Model model, Integer id){
        Role role = null;
        if(id!=null){
            role = service.findById (id);
        }
        if(role==null){
            return new ModelAndView ("404");
        }
        model.addAttribute ("role",role);
        return new ModelAndView ("back/sys/auth_edit");
    }
    @ApiOperation(value="获取授权", notes="menuAuth")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true ,dataType = "Integer")
    })
    @GetMapping("menuAuth")
    public Map<String,Object> menuAuth(Integer id){
        List<MenuAuthVO> menuAuthVOS = service.getAuthVOS (id);
        return JsonWrapper.wrapperPage (menuAuthVOS);
    }
    @ApiOperation(value="保存授权", notes="saveAuth")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authVOS", value = "授权列表", required = true ,dataType = "List"),
            @ApiImplicitParam(name = "id", value = "角色id", required = true ,dataType = "Integer")
    })
    @PostMapping("saveAuth")
    public HashMap<String,Object> saveAuth(@RequestBody List<MenuAuthVO> authVOS,Integer id){
        try {
            Role role = service.findById (id);
            relationService.save (authVOS,role);
            return JsonWrapper.successWrapper ();
        }catch (Exception e){
            logger.error (e.getMessage (),e);
            return JsonWrapper.failureWrapper ("保存失败",e.getMessage ());
        }
    }
}
