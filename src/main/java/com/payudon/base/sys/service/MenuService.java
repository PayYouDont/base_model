package com.payudon.base.sys.service;

import com.payudon.base.sys.entity.Menu;
import com.payudon.base.common.service.BaseService;

import java.util.List;
import java.util.Map;

public interface MenuService extends BaseService<Menu,Integer> {
    Map<String,Object> getTreeById(Integer id);
    List<Menu> findAllByPid(Integer pid);
}
