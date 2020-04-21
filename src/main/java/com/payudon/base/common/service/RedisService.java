package com.payudon.base.common.service;

import com.payudon.base.common.entity.vo.RedisVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * 可自行扩展
 *
 * @ClassName RedisService
 * @Description TODO
 * @Author pay
 * @DATE 2020/4/20 15:10
 **/
public interface RedisService {
    /**
     * @return org.springframework.data.domain.Page
     * @Author peiyongdong
     * @Description (根据key查询缓存)
     * @Date 15:32 2020/4/20
     * @Param [key, pageable]
     **/
    Page findByKey(String key, Pageable pageable);

    /**
     * findById
     *
     * @param key 键
     * @return /
     */
    List<RedisVo> findByKey(String key);

    /**
     * delete
     *
     * @param key 键
     */
    void delete(String key);

    void deleteKey(Collection<String> keys);

    /**
     * 清空缓存
     */
    void deleteAll();

    /**
     * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
     *
     * @param oldKey
     * @param newKey
     */
    void renameKey(String oldKey, String newKey);

    boolean existsKey(String key);
}
