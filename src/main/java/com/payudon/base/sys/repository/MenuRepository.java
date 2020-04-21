package com.payudon.base.sys.repository;

import com.payudon.base.sys.entity.Menu;
import com.payudon.base.common.repository.BaseRepository;

import java.util.List;

public interface MenuRepository extends BaseRepository<Menu, Integer> {
    List<Menu> findAllByPidOrderByNumber(Integer pid);
}
