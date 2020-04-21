package com.payudon.base.sys.service;

import com.payudon.base.sys.entity.Menu;
import com.payudon.base.sys.entity.Role;
import com.payudon.base.sys.entity.vo.MenuAuthVO;
import com.payudon.base.sys.repository.MenuRepository;
import com.payudon.base.sys.repository.RoleRepository;
import com.payudon.base.common.annotation.CURD;
import com.payudon.base.common.service.CURDService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends CURDService<Role, Integer> implements RoleService {
    @Resource
    @CURD
    private RoleRepository repository;
    @Resource
    private MenuRepository menuRepository;
    @Override
    public List<MenuAuthVO> getAuthVOS(Integer id) {
        Role role = findById (id);
        List<Menu> menus = menuRepository.findAll ();
        List<MenuAuthVO> menuAuthVOS = new ArrayList<> ();
        menus.forEach (menu -> {
            MenuAuthVO vo = new MenuAuthVO ();
            vo.setMenuId (menu.getId ());
            vo.setMenuNumber (menu.getNumber ());
            vo.setMenuTitle (menu.getTitle ());
            vo.setMenuPid (menu.getPid ());
            vo.setPermissions (menu.getPermissions ());
            role.getMenuRoleRelations ().forEach (menuRoleRelation -> {
                if (menu.getId () == menuRoleRelation.getMenu ().getId ()) {
                    vo.setAuthAdd (menuRoleRelation.getIsAdd ());
                    vo.setAuthView (menuRoleRelation.getIsView ());
                    vo.setAuthModify (menuRoleRelation.getIsModify ());
                    vo.setAuthDelete (menuRoleRelation.getIsDelete ());
                }
            });
            menuAuthVOS.add (vo);
        });
        return menuAuthVOS;
    }
}
