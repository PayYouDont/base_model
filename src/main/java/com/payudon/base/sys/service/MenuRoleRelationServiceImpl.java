package com.payudon.base.sys.service;

import com.payudon.base.sys.entity.Menu;
import com.payudon.base.sys.entity.MenuRoleRelation;
import com.payudon.base.sys.entity.Role;
import com.payudon.base.sys.entity.vo.MenuAuthVO;
import com.payudon.base.sys.repository.MenuRepository;
import com.payudon.base.sys.repository.MenuRoleRelationRepository;
import com.payudon.base.common.annotation.CURD;
import com.payudon.base.common.service.CURDService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MenuRoleRelationServiceImpl extends CURDService<MenuRoleRelation,Integer> implements MenuRoleRelationService {
    @Resource
    @CURD
    private MenuRoleRelationRepository repository;
    @Resource
    private MenuRepository menuRepository;

    @Override
    public List<MenuRoleRelation> findByRoleId(Integer roleId) {
        return repository.findAllByRoleId (roleId);
    }

    @Override
    public Set<String> getRolePerms(Integer roleId) {
        List<MenuRoleRelation> menuRoleRelationList = findByRoleId (roleId);
        Set<String> permsSet = new HashSet<> ();
        menuRoleRelationList.forEach (menuRoleRelation -> {
            Integer mid = menuRoleRelation.getMenu ().getId ();
            Menu menu = menuRepository.findById (mid).get ();
            if(menu!=null){
                String perms = menu.getPermissions ();
                permsSet.addAll(menuRoleRelation.getPermsSet(perms));
            }
        });
        return permsSet;
    }

    @Override
    public void insert(MenuRoleRelation relation) throws Exception {
        MenuRoleRelation menuRoleRelation = repository.findByRoleAndMenu (relation.getRole (),relation.getMenu ());
        if(menuRoleRelation!=null){
            relation.setId (menuRoleRelation.getId ());
        }
        repository.save (relation);
    }

    @Override
    public void save(List<MenuAuthVO> authVOS, Role role) throws Exception {
        authVOS.forEach (authVO -> {
            Menu menu = menuRepository.findById (authVO.getMenuId ()).get ();
            MenuRoleRelation relation = authVO.createRelation (role,menu);
            try {
                save (relation);
            }catch (Exception e){
                logger.error (e.getMessage (),e);
            }
        });
    }
}
