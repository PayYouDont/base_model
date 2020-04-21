package com.payudon.base.common.repository;

import com.payudon.base.common.entity.BaseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName BaseRepository
 * @Description TODO
 * @Author pay
 * @DATE 2019/11/12 9:52
 **/
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity,K> extends JpaRepository<T,K>, JpaSpecificationExecutor {
    @Modifying
    @Query("delete from #{#entityName} where id in (?1)")
    void deleteAllByIds(@Param ("ids") List<K> ids);
}
