package com.github.imusk.dm.aide.core;

import org.jawin.COMException;
import org.jawin.DispatchPtr;
import org.jawin.GUID;
import org.jawin.IdentityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-04 21:56
 * @classname: DmSoft
 * @description: 大漠插件Java版
 * @version: 3.1233
 */
public class DmSoft extends DispatchPtr {

    private final static Logger logger = LoggerFactory.getLogger(DmSoft.class);

    /**
     * 单例：双重检查
     */
    private static volatile DmSoft dm;

    private DmSoft(String programId) throws COMException {
        super(programId);
    }

    public static DmSoft getInstance() {
        if (dm == null) {
            synchronized (DmSoft.class) {
                if (dm == null) {
                    try {
                        dm = new DmSoft("dm.dmsoft");
                    } catch (COMException e) {
                        logger.error("实例 dm.dll 插件异常: {}", e.getMessage(), e);
                    }
                }
            }
        }
        return dm;
    }


    public static final GUID PROXY_IID = new GUID("{1FCEAA71-2172-4F67-BC5A-B0BEA55A7D6D}");

    public int getGuidToken() {
        return IdentityManager.registerProxy(PROXY_IID, DmSoft.class);
    }


    /**
     * 调用函数方法
     *
     * @param methodName 方法名称
     * @return 返回值参考文档
     */
    public Object execute(String methodName) {

        try {

            Object result = invoke(methodName);
            return result;

        } catch (Exception e) {
            logger.error("调用函数: {}, 异常信息: {}", methodName, e.getMessage(), e);
        }

        return null;
    }

    /**
     * 调用函数方法
     *
     * @param methodName 方法名称
     * @param params     参数列表
     * @return 返回值参考文档
     */
    public Object execute(String methodName, Object[] params) {

        try {

            Object result = invokeN(methodName, params);
            return result;

        } catch (Exception e) {
            logger.error("调用函数: {}, 请求参数: {}, 异常信息: {}", methodName, params, e.getMessage(), e);
        }

        return null;
    }

    /**
     * 调用函数方法
     *
     * @param clazz      Class类
     * @param methodName 方法名称
     * @param params     参数列表
     * @return 返回值参考文档
     */
    public Object execute(Class clazz, String methodName, Object[] params) {
        Object result = null;

        try {

            Class<?> aClass = Class.forName(clazz.getName());

            Method method = aClass.getMethod(methodName, new Class[]{Object[].class});

            Constructor<?> constructor = aClass.getConstructor(DmSoft.class);

            result = method.invoke(constructor.newInstance(this), new Object[]{params});

        } catch (Exception e) {
            logger.error("调用函数: {}, 请求参数: {}, 异常信息: {}", methodName, params, e.getMessage(), e);
        }

        return result;
    }


}
