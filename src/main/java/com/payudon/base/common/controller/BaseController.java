package com.payudon.base.common.controller;

import com.payudon.base.sys.entity.User;
import com.payudon.base.sys.util.ShiroUtils;
import com.payudon.base.common.annotation.CURD;
import com.payudon.base.common.annotation.EditPath;
import com.payudon.base.common.annotation.ViewPath;
import com.payudon.base.common.entity.BaseEntity;
import com.payudon.base.common.entity.LayuiTabPage;
import com.payudon.base.common.service.BaseService;
import com.payudon.base.common.util.JsonWrapper;
import com.payudon.base.common.util.ReflecUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author pay
 * @DATE 2019/10/10 9:18
 **/
public class BaseController<T extends BaseEntity,K> implements InitializingBean, DisposableBean {
    protected final Logger logger = LoggerFactory.getLogger (getClass ());
    private BaseService<T,K> baseService;
    private String editPath;
    private String viewPath;

    protected User getUser(){
        return ShiroUtils.getUser ();
    }
    @GetMapping("toView")
    public ModelAndView toView(){
        if(viewPath!=null){
            return new ModelAndView (viewPath);
        }
        return new ModelAndView ("404");
    }
    @GetMapping("toEdit")
    public ModelAndView toEdit(Model model, K id){
        T t = null;
        if(id!=null){
            t = baseService.findById (id);
        }
        if(t==null){
           try {
               t = (T)ReflecUtil.createEntityByGeneric (getClass ());
           }catch (Exception e){
               logger.error (e.getMessage (),e);
           }
        }
        model.addAttribute ("entity",t);
        if(editPath!=null){
            return new ModelAndView (editPath);
        }
        return new ModelAndView ("404");
    }
    @PostMapping("save")
    public Map<String,Object> save(T t){
        try {
            baseService.save (t);
            return JsonWrapper.successWrapper ();
        }catch (Exception e){
            logger.error (e.getMessage (),e);
            return JsonWrapper.failureWrapper ("保存实体失败！",e.getMessage ());
        }
    }
    @ApiOperation(value="删除实体", notes="delete")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "实体id", required = true ,dataType = "String")
    })
    @PostMapping("delete")
    public Map<String,Object> delete(K[] ids){
        try {
            baseService.delete (ids);
            return JsonWrapper.successWrapper ();
        }catch (Exception e){
            logger.error (e.getMessage (),e);
            return JsonWrapper.failureWrapper ("删除实体失败！",e.getMessage ());
        }
    }
    @ApiOperation(value="分页查询", notes="page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页page", required = true ,dataType = "LayuiTabPage")
    })
    @GetMapping("page")
    public Map<String,Object> page(LayuiTabPage page){
        return JsonWrapper.wrapperPage (baseService.page (page));
    }

    private void initEditPath(){
        ReflecUtil.initFieldByAnnotation (getClass (), EditPath.class,(annotation, field) ->  editPath = ((EditPath)annotation).value ());
    }
    private void initViewPath(){
        ReflecUtil.initFieldByAnnotation (getClass (), ViewPath.class,(annotation, field) ->  viewPath = ((ViewPath)annotation).value ());
    }
    private void initCURD(){
        ReflecUtil.initFieldByAnnotation (getClass (), CURD.class,(annotation, field) ->{
            try {
                baseService = (BaseService) field.get (this);
            }catch (Exception e){
                logger.error (e.getMessage (),e);
            }
        });
    }
    @Override
    public void destroy() throws Exception {
        //销毁时操作
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化时
        initEditPath();
        initViewPath();
        initCURD();
    }
}
