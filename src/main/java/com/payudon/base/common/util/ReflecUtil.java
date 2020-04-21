package com.payudon.base.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ReflecUtil {

	private static final Logger logger = LoggerFactory.getLogger(ReflecUtil.class);

	public static List<Field> getFields(Class<?> clazz) {
		//属性集合
		List<Field> fieldList = new ArrayList<>();
		//获取实体类属性
		Field[] fields = clazz.getDeclaredFields();
		fieldList = addFildToList(fieldList, fields);
		//获取父类属性
		if(clazz.getSuperclass() instanceof Class) {
			Field[] superFields = clazz.getSuperclass().getDeclaredFields();
			fieldList = addFildToList(fieldList, superFields);
		}
		return fieldList;
	}
	private static List<Field> addFildToList(List<Field> fieldList,Field[] fields){
		for(int i=0;i<fields.length;i++) {
			Field field = fields[i];
			field.setAccessible(true);
			fieldList.add(field);
		}
		return fieldList;
	}

    public static List<Method> getMethods(Class<?> clazz) {
        //方法集合
        List<Method> methodList = new ArrayList<>();
        //获取实体类方法
        Method[] methods = clazz.getDeclaredMethods ();
        methodList.addAll (Arrays.asList (methods));
        //获取父类方法
        if(clazz.getSuperclass() instanceof Class) {
            Method[] superMethods = clazz.getSuperclass().getMethods ();
            methodList.addAll (Arrays.asList (superMethods));
        }
        return methodList;
    }
	public static Class<?> getGeneric(Collection<?> collection){
		if(collection.size()>0) {
			return collection.iterator().next().getClass();
		}
		return null;
	}
	public static List<Field> getFields(Collection<?> collection) {
		return getFields(getGeneric(collection));
	}
    /**
     * @Author peiyongdong
     * @Description ( 初始化指定注解属性并回调 )
     * @Date 16:08 2019/11/11
     * @Param [clazz, annotationClass, onAnnotationCallback]
     * @return void
     **/
    public static void initFieldByAnnotation(Class clazz,Class annotationClass,OnAnnotationCallback onAnnotationCallback){
        initFieldByAnnotation (clazz,annotationClass,onAnnotationCallback,true);
    }
    /**
    * @Author peiyongdong
    * @Description ( 初始化指定注解属性并回调 )
    * @Date 16:08 2019/11/11
    * @Param [clazz, annotationClass, onAnnotationCallback, isSingle=是否只操作单个属性]
    * @return void
    **/
    public static void initFieldByAnnotation(Class clazz,Class annotationClass,OnAnnotationCallback onAnnotationCallback,boolean isSingle){
        List<Field> fieldList = getFields (clazz);
        fieldList.forEach (field -> {
            Annotation annotation = field.getAnnotation (annotationClass);
            if(annotation!=null){
                onAnnotationCallback.setField (annotation,field);
                if(isSingle){
                    return;
                }
            }
        });
    }
    public interface OnAnnotationCallback{
        void setField(Annotation annotation,Field field);
    }
    /**
    * @Author peiyongdong
    * @Description ( 获取泛型class )
    * @Date 16:08 2019/11/11
    * @Param [clazz]
    * @return java.lang.Class
    **/
    public static Class getGenericClass(Class clazz){
        Type genericSuperclass = clazz.getGenericSuperclass();
        if(genericSuperclass instanceof ParameterizedType){
            //参数化类型
            ParameterizedType parameterizedType= (ParameterizedType) genericSuperclass;
            //返回表示此类型实际类型参数的 Type 对象的数组
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            return (Class) actualTypeArguments[0];
        }else {
            return (Class) genericSuperclass;
        }
    }
    /**
    * @Author peiyongdong
    * @Description ( 根据泛型类型创建实体类 )
    * @Date 16:08 2019/11/11
    * @Param [clazz]
    * @return java.lang.Object
    **/
    public static Object createEntityByGeneric(Class clazz){
        Class tClass = getGenericClass (clazz);
        try {
            return tClass.getDeclaredConstructor ().newInstance ();
        }catch (Exception e){
            logger.error (e.getMessage (),e);
        }
        return null;
    }
}
