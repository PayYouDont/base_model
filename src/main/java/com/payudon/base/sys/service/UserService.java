package com.payudon.base.sys.service;

import com.payudon.base.sys.entity.User;
import com.payudon.base.common.service.BaseService;

public interface UserService extends BaseService<User,Integer> {
    User findByAccount(String account);
}
