package com.payudon.base.sys.service;

import com.payudon.base.sys.entity.User;
import com.payudon.base.sys.repository.UserRepository;
import com.payudon.base.common.annotation.CURD;
import com.payudon.base.common.service.CURDService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends CURDService<User, Integer> implements UserService {
    @Resource
    @CURD
    private UserRepository repository;

    @Override
    public User findByAccount(String account) {
        return repository.findByAccount (account);
    }
}
