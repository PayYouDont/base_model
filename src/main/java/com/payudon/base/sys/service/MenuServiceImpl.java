package com.payudon.base.sys.service;

import com.payudon.base.sys.entity.Menu;
import com.payudon.base.sys.repository.MenuRepository;
import com.payudon.base.common.annotation.CURD;
import com.payudon.base.common.entity.LayuiTree;
import com.payudon.base.common.service.CURDService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl extends CURDService<Menu,Integer> implements MenuService {
    @Resource
    @CURD
    private MenuRepository repository;
    @Override
    public List<Menu> list() {
        return repository.findAll (Sort.by ( Sort.Direction.ASC, "number" ));
    }
    @Override
    public List<Menu> findAllByPid(Integer pid) {
        return repository.findAllByPidOrderByNumber (pid);
    }
    @Override
    public Map<String,Object> getTreeById(Integer id) {
        Map<String, Object> map = new HashMap<> ();
        map.put ("code", 0);
        if (id == null) {
            id = 0;
        }
        Menu menu = findById (id);
        setChildren (menu);
        List<LayuiTree> data = new ArrayList<> ();
        data.add (parseToTree (menu));
        map.put ("data", data);
        return map;
    }
    private void setChildren(Menu menu){
        List<Menu> childMenus = findAllByPid (menu.getId ());
        if(childMenus!=null){
            childMenus.forEach (childMenu -> setChildren (childMenu));
            menu.setChildren (childMenus);
        }
    }
    private LayuiTree parseToTree(Menu menu){
        LayuiTree layuiTree = new LayuiTree ();
        layuiTree.setLabel (menu.getTitle ());
        layuiTree.setId (menu.getId ());
        layuiTree.setChecked (menu.getChecked ());
        layuiTree.setHref (menu.getUrl ());
        List<Menu> childMenus = menu.getChildren ();
        if(childMenus!=null){
            childMenus.forEach (childMenu -> layuiTree.getChildren ().add (parseToTree (childMenu)));
        }
        return layuiTree;
    }
}
