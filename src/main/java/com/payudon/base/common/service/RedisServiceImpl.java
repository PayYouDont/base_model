package com.payudon.base.common.service;

import com.payudon.base.common.entity.vo.RedisVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName RedisServiceImpl
 * @Description TODO
 * @Author pay
 * @DATE 2020/4/20 15:12
 **/
@Service
public class RedisServiceImpl implements RedisService{
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public Page findByKey(String key, Pageable pageable) {
        List<RedisVo> redisVos = findByKey(key);
        return new PageImpl(redisVos,pageable,redisVos.size ());
    }

    @Override
    public List<RedisVo> findByKey(String key) {
        List<RedisVo> redisVos = new ArrayList<> ();
        if(!"*".equals(key)){
            key = "*" + key + "*";
        }
        Set<String> keys = redisTemplate.keys(key);
        for (String s : keys) {
            // 过滤掉权限的缓存
            /*if (s.contains("role::loadPermissionByUser") || s.contains("user::loadUserByUsername") || s.contains(onlineKey) || s.contains(codeKey)) {
                continue;
            }*/
            RedisVo redisVo = new RedisVo(s, Objects.requireNonNull(redisTemplate.opsForValue().get(s)).toString());
            redisVos.add(redisVo);
        }
        return redisVos;
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
    @Override
    public void deleteKey(Collection<String> keys) {
        Set<String> kSet = keys.stream().collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }
    @Override
    public void deleteAll() {
        Set<String> keys = redisTemplate.keys(  "*");
        deleteKey (keys);
    }

    @Override
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename (oldKey,newKey);
    }

    @Override
    public boolean existsKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
