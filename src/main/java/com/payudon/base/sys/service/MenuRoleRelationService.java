package com.payudon.base.sys.service;

import com.payudon.base.sys.entity.MenuRoleRelation;
import com.payudon.base.sys.entity.Role;
import com.payudon.base.sys.entity.vo.MenuAuthVO;
import com.payudon.base.common.service.BaseService;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface MenuRoleRelationService extends BaseService<MenuRoleRelation,Integer> {
    List<MenuRoleRelation> findByRoleId(@Param ("roleId")Integer roleId);
    Set<String> getRolePerms(Integer roleId);
    void save(List<MenuAuthVO> authVO,Role role) throws Exception;
}
