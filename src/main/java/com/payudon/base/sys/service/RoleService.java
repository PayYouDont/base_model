package com.payudon.base.sys.service;

import com.payudon.base.sys.entity.Role;
import com.payudon.base.sys.entity.vo.MenuAuthVO;
import com.payudon.base.common.service.BaseService;

import java.util.List;

public interface RoleService extends BaseService<Role,Integer> {
    List<MenuAuthVO> getAuthVOS(Integer id);
}
