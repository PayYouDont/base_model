package com.payudon.base.common.util;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonWrapper {

	public static HashMap<String, Object> successWrapper(Object pojo) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", Boolean.valueOf(true));
		wrapper.put("data", pojo);
		return wrapper;
	}
	
	public static HashMap<String, Object> successWrapper(Object pojo,String msg) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", Boolean.valueOf(true));
		wrapper.put("data", pojo);
		wrapper.put("msg", msg);
		return wrapper;
	}
	
	public static HashMap<String, Object> successWrapperMsg(String msg) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", Boolean.valueOf(true));
		wrapper.put("msg", msg);
		return wrapper;
	}

	public static HashMap<String, Object> failureWrapper(Object pojo) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", Boolean.valueOf(false));
		wrapper.put("data", pojo);
		return wrapper;
	}

	public static HashMap<String, Object> failureWrapperMsg(String msg) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", Boolean.valueOf(false));
		wrapper.put("msg", msg);
		return wrapper;
	}

	public static HashMap<String, Object> failureWrapper(Object... pojo) {
		return wrapper(false, pojo);
	}

	public static HashMap<String, Object> successWrapper(Object... pojo) {
		return wrapper(true, pojo);
	}

	public static HashMap<String, Object> wrapper(boolean success,
			Object... pojo) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", Boolean.valueOf(success));
		int length = pojo.length;
		if (pojo.length % 2 == 1) {
			length--;
		}
		length /= 2;
		for (int i = 0; i < length; i++) {
			wrapper.put(String.valueOf(pojo[(i * 2)]), pojo[(i * 2 + 1)]);
		}
		return wrapper;
	}

	public static HashMap<String, Object> wrapperMap(boolean success,
			Map<String, Object> map) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", Boolean.valueOf(success));
		wrapper.put("data", map);
		return wrapper;
	}
	  //转换为LayUI适配的Json数组
    public static HashMap<String,Object> wrapperPage(List<?> list,int count){
        HashMap<String, Object> wrapper = new HashMap<>();
        wrapper.put("code",0);
        wrapper.put("count",count);
        wrapper.put("data", list);
        return wrapper;
    }
    //转换为LayUI适配的Json数组
    public static HashMap<String,Object> wrapperPage(List<?> list){
        HashMap<String, Object> wrapper = new HashMap<>();
        wrapper.put("code",0);//数据状态的字段名称，默认：code
        wrapper.put("msg","success");//状态信息的字段名称，默认：msg
        wrapper.put("count",list.size ());//数据总数的字段名称，默认：count
        wrapper.put("data", list);//数据列表的字段名称，默认：data
        return wrapper;
    }
    public static HashMap<String,Object> wrapperPage(Page<?> page ){
        HashMap<String, Object> wrapper = new HashMap<>();
        wrapper.put("code",0);//数据状态的字段名称，默认：code
        wrapper.put("msg","success");//状态信息的字段名称，默认：msg
        wrapper.put("count",page.getSize ());//数据总数的字段名称，默认：count
        wrapper.put("data", page.getContent ());//数据列表的字段名称，默认：data
        return wrapper;
    }
}
