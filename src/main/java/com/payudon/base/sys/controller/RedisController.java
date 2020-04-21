package com.payudon.base.sys.controller;

import com.payudon.base.common.service.RedisService;
import com.payudon.base.common.util.JsonWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName RedisController
 * @Description TODO
 * @Author pay
 * @DATE 2020/4/20 15:52
 **/
@RestController
@RequestMapping("redis")
public class RedisController {
    @Resource
    private RedisService service;

    @GetMapping("test")
    private Map<String,Object> test(String key){
        try {
            return JsonWrapper.successWrapper (service.findByKey (key));
        }catch (Exception e){
            e.printStackTrace ();
            return JsonWrapper.failureWrapper ();
        }
    }
}
