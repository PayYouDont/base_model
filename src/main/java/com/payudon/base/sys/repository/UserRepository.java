package com.payudon.base.sys.repository;

import com.payudon.base.sys.entity.User;
import com.payudon.base.common.repository.BaseRepository;

public interface UserRepository extends BaseRepository<User, Integer> {
    User findByAccount(String account);
}
