package com.github.imusk.dm.aide.core;

import org.jawin.COMException;
import org.jawin.FuncPtr;
import org.jawin.ReturnFlags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-04 22:06
 * @classname: DmReg
 * @description: 利用 DmReg.dll 实现免注册大漠插件 dm.dll
 */
public class DmReg {

    private final static Logger logger = LoggerFactory.getLogger(DmReg.class);

    public static FuncPtr dmRegFuncPtr = null;

    /**
     * 获取当前操作系统名称
     */
    private static final String OS_NAME = System.getProperty("os.name");

    /**
     * 获取当前操作系统的位数
     */
    private static final String OS_ARCH = System.getProperty("os.arch");


    /**
     * 设置lib目录到JDK环境中
     *
     * @param libPath lib目录(默认：/lib)
     * @return true: 执行成功, false: 执行异常
     */
    public static boolean setLibPath(String libPath) {
        try {
            // 默认 /lib 目录
            if (libPath == null || libPath.length() == 0) {
                libPath = "/lib";
            }

            // 获取Lib目录地址
            String libraryPath = ClassLoader.class.getResource(libPath).getPath();

            // 获取系统Path参数
            Field userPathsField = ClassLoader.class.getDeclaredField("usr_paths");
            userPathsField.setAccessible(true);
            String[] paths = (String[]) userPathsField.get(null);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.length; i++) {
                if (libraryPath.equals(paths[i])) {
                    continue;
                }
                sb.append(paths[i]).append(';');
            }
            sb.append(libraryPath);

            System.setProperty("java.library.path", sb.toString());

            final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
            sysPathsField.setAccessible(true);
            sysPathsField.set(null, null);

            return true;
        } catch (Exception e) {
            logger.error("设置Lib目录[{}]异常: {}", libPath, e.getMessage(), e);
        }

        return false;
    }


    /**
     * 免注册 dm.dll
     *
     * @param dmRegPath DmReg.dll路径地址
     * @param dmPath    dm.dll路径地址
     * @return true: 执行成功, false: 执行异常
     * @throws COMException
     */
    public static boolean register(String dmRegPath, String dmPath) {

        try {
            // 调用 DmReg.dll 的 SetDllPathW 方法
            dmRegFuncPtr = new FuncPtr(dmRegPath, "SetDllPathW");

            // 设置 SetDllPathW 的值为 dm.dll 地址, 返回值: 0 失败 1 成功
            if (dmRegFuncPtr.invoke_I(dmPath, ReturnFlags.CHECK_NONE) == 1) {
                return true;
            }

        } catch (COMException e) {
            logger.error("DmReg.dll路径: {}, dm.dll路径: {}, 免注册大漠插件异常: {}", dmRegPath, dmPath, e.getMessage(), e);
        }

        return false;
    }


    /**
     * 注册后关闭, 释放dll资源, 即使后续还在调用大漠插件也没关系
     */
    public static void close() {
        try {

            if (dmRegFuncPtr != null) {
                dmRegFuncPtr.close();
            }

        } catch (COMException e) {
            logger.error("关闭[DmReg.dll]异常: {}", e.getMessage(), e);
        }
    }


    /**
     * 判断是否是 Windows 系统
     */
    public static boolean isWindows() {
        return OS_NAME != null && OS_NAME.toLowerCase().startsWith("windows");
    }


    /**
     * 判断是否是 32位 操作系统
     */
    private static boolean x86() {
        return OS_ARCH != null && OS_ARCH.contains("x86");
    }


}
