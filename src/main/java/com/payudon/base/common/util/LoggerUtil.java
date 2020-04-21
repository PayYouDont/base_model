package com.payudon.base.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 */
public class LoggerUtil {
    private static Logger logger;
    public static void log(Class clzz,String logMsg,Object log){
        logger = LoggerFactory.getLogger(clzz);
        logger.info(logMsg,log);
    }
    public static void log(Class clzz,Exception e){
        log(clzz,e.getMessage(),e);
    }
    public static void print(String msg){
        logger = LoggerFactory.getLogger(LoggerUtil.class);
        logger.info(msg);
    }
    public static void print(Class clzz,String msg){
        logger = LoggerFactory.getLogger(clzz);
        logger.info(msg);
    }
}
