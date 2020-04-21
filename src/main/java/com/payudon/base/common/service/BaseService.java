
package com.payudon.base.common.service;

import com.payudon.base.common.entity.BaseEntity;
import com.payudon.base.common.entity.LayuiTabPage;
import com.payudon.base.common.util.UpdateTool;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/** 
* @ClassName: BaseService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月11日 上午11:19:21 
*  
*/
public interface BaseService<T extends Serializable, K>{
	void delete(K[] ids) throws Exception;

    void insert(T record) throws Exception;
   
    T findById(K id);

    default List<T> list(){
    	return null;
    };

    default void save(BaseEntity entity) throws Exception {
        if(entity!=null&&entity.getId ()!=null){
            T source = findById ((K)entity.getId ());
            UpdateTool.copyNullProperties(source,entity);
        }
        insert ((T)entity);
    };
    default Page<T> page(LayuiTabPage layuiTabPage){
        return null;
    }
}
