package com.payudon.base.sys.controller;

import com.payudon.base.sys.entity.Menu;
import com.payudon.base.sys.service.MenuService;
import com.payudon.base.common.annotation.CURD;
import com.payudon.base.common.annotation.ViewPath;
import com.payudon.base.common.controller.BaseController;
import com.payudon.base.common.util.JsonWrapper;
import io.swagger.annotations.Api;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName MenuController
 * @Description TODO
 * @Author pay
 * @DATE 2019/10/10 9:22
 **/
@RestController
@RequestMapping("menu")
@Api(tags = "菜单管理")
public class MenuController extends BaseController<Menu,Integer> {
    @CURD
    @Resource
    @ViewPath("back/sys/menu_list")
    private MenuService service;
    @PostMapping("tree")
    public Map<String,Object> tree(Integer id){
        return service.getTreeById (id);
    }
    @GetMapping("list")
    public Map<String,Object> list(){
        return JsonWrapper.wrapperPage (service.list ());
    }
    @GetMapping("findByPid")
    public Map<String,Object> findByPid(Integer pid){
        return JsonWrapper.wrapperPage (service.findAllByPid (pid));
    }
    @GetMapping("toEdit")
    @Override
    public ModelAndView toEdit(Model model, Integer id){
        Menu menu = null;
        Integer pid = 0;
        if(id!=null){
            menu = service.findById (id);
            pid = menu.getPid ();
        }
        if (menu==null){
            menu = new Menu ();
        }
        model.addAttribute ("entity",menu);
        model.addAttribute ("parentEntity",service.findById (pid));
        return new ModelAndView ("back/sys/menu_edit");
    }
}
