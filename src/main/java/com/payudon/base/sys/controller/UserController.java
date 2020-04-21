package com.payudon.base.sys.controller;

import com.payudon.base.sys.entity.User;
import com.payudon.base.sys.service.UserService;
import com.payudon.base.sys.util.MD5Util;
import com.payudon.base.common.annotation.CURD;
import com.payudon.base.common.annotation.EditPath;
import com.payudon.base.common.annotation.ViewPath;
import com.payudon.base.common.controller.BaseController;
import com.payudon.base.common.util.JsonWrapper;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
@RequestMapping("user")
@Api(tags = "用户管理")
public class UserController extends BaseController<User, Integer> {
    @Resource
    @CURD
    @ViewPath("back/sys/user_list")
    @EditPath("back/sys/user_edit")
    private UserService service;
    @GetMapping("login")
    public ModelAndView toLogin(){
        return new ModelAndView ("login");
    }
    @PostMapping("login")
    public Map<String,Object> login(User user){
        String password = MD5Util.encrypt(user.getAccount (), user.getPassword());
        AuthenticationToken token = new UsernamePasswordToken(user.getAccount (), password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return JsonWrapper.successWrapper ();
        }catch(AuthenticationException e) {
            logger.error(e.getMessage(),e);
            return JsonWrapper.failureWrapper (e.getMessage (),e);
        }
    }
    @GetMapping("logout")
    public Map<String,Object> logout(){
        SecurityUtils.getSubject().logout ();
        return JsonWrapper.successWrapper ();
    }
    /*@GetMapping("captcha")
    public Map<String,Object> getCaptcha() throws Exception{
        String url = "http://nos.netease.com/necaptcha/b046cda84d2c42ea9842e4279245ffff.jpg";
        Map<String,Object> resultMap = CaptchaUtil.createImage (url);
        return resultMap;
    }*/
}
