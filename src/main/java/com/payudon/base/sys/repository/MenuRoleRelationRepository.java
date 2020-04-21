package com.payudon.base.sys.repository;

import com.payudon.base.sys.entity.Menu;
import com.payudon.base.sys.entity.MenuRoleRelation;
import com.payudon.base.sys.entity.Role;
import com.payudon.base.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRoleRelationRepository extends BaseRepository<MenuRoleRelation, Integer> {
    @Query("select relation from MenuRoleRelation relation where relation.role=?1")
    List<MenuRoleRelation> findAllByRoleId(@Param ("roleId")Integer roleId);
    MenuRoleRelation findByRoleAndMenu(Role role, Menu menu);
}
