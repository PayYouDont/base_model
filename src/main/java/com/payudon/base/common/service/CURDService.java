
package com.payudon.base.common.service;

import com.payudon.base.common.annotation.CURD;
import com.payudon.base.common.entity.BaseEntity;
import com.payudon.base.common.entity.LayuiTabPage;
import com.payudon.base.common.repository.BaseRepository;
import com.payudon.base.common.util.ReflecUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.Attribute;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
* @ClassName: BaseService
* @Description: TODO(  基础服务类   )
* @author peiyongdong
* @date 2018年9月11日 上午11:19:21
**/
public class CURDService<T extends BaseEntity,K> implements Specification<T>, InitializingBean {
    private BaseRepository<T,K> repository;
    //模糊查询条件
    private String param;
    protected Logger logger = LoggerFactory.getLogger (this.getClass ());

    public void delete(K[] ids) throws Exception {
        repository.deleteAllByIds (Arrays.asList (ids));
    }

    public void insert(T record) throws Exception {
        repository.save (record);
    }
    //@Cacheable(value = "catCache")
    public T findById(K id) {
        initRepository();
        return repository.findById (id).get ();
    }
    //@Cacheable(value = "catCache")
    public List<T> list() {
        return repository.findAll ();
    }
    ///@Cacheable(value = "catCache")
    public Page<T> page(LayuiTabPage layuiTabPage) {
        if(!StringUtils.isEmpty (layuiTabPage.getQuery ())){
            return page (layuiTabPage,layuiTabPage.getQuery ());
        }
        return repository.findAll (PageRequest.of (layuiTabPage.getPageIndex ()-1,layuiTabPage.getPageSize (), Sort.by (Sort.Direction.ASC,"id")));
    }
    @Cacheable
    public Page<T> page(LayuiTabPage layuiTabPage,String param) {
        this.param = param;
        return repository.findAll (this::toPredicate,PageRequest.of (layuiTabPage.getPageIndex ()-1,layuiTabPage.getPageSize (), Sort.by (Sort.Direction.ASC,"id")));
    }

    /**
    * @Author peiyongdong
    * @Description ( 模糊查询：默认查询实体类中的所有字段和查询条件相似，其他查询需要单独实现)
    * @Date 16:53 2019/11/12
    * @Param [
    * root = Root接口，代表查询的根对象，可以通过root获取实体中的属性,
    * criteriaQuery = 代表一个顶层查询对象，用来自定义查询,
    * criteriaBuilder = 用来构建查询，此对象里有很多条件方法]
    * @return javax.persistence.criteria.Predicate
    **/
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if(param!=null){
            Set<Attribute<T, ?>> attributes = root.getModel ().getDeclaredAttributes ();
            Predicate[] predicates = new Predicate[attributes.size ()];
            int index = 0;
            for (Attribute<T, ?> tAttribute:attributes){
                Path<Object> custName = root.get(tAttribute.getName ());
                Predicate like = criteriaBuilder.like(custName.as(String.class), "%"+param+"%");
                predicates[index] = like;
                index++;
            }
            Predicate and = criteriaBuilder.or (predicates);
            return and;
        }
        return null;
    }
    private void initRepository(){
        ReflecUtil.initFieldByAnnotation (getClass (), CURD.class,(annotation, field) ->{
            try {
                repository = (BaseRepository<T, K>) field.get (this);
            }catch (Exception e){
                logger.error (e.getMessage (),e);
            }
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initRepository ();
    }
}
