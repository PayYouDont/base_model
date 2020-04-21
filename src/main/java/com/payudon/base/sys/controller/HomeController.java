package com.payudon.base.sys.controller;

import com.payudon.base.sys.service.MenuService;
import com.payudon.base.sys.util.ShiroUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @ClassName HomeController
 * @Description TODO
 * @Author pay
 * @DATE 2019/10/10 16:46
 **/
@RestController
@RequestMapping("/")
public class HomeController{
    @Resource
    private MenuService menuService;
    @GetMapping("index")
    public ModelAndView toIndex(Model model){
        model.addAttribute ("menus",menuService.findAllByPid (0));
        model.addAttribute ("user", ShiroUtils.getUser ());
        return new ModelAndView ("index");
    }
    @GetMapping("home/console")
    public ModelAndView toWelcome(){
        return new ModelAndView ("back/home/console");
    }
    @GetMapping("set/user/info")
    public ModelAndView setUserInfo(){
        return new ModelAndView ("back/home/set/user_info");
    }
    @GetMapping("set/user/password")
    public ModelAndView setUserPassword(){
        return new ModelAndView ("back/home/set/user_password");
    }
    @GetMapping("set/website")
    public ModelAndView toSysWebsite(){
        return new ModelAndView ("back/home/website");
    }
}
