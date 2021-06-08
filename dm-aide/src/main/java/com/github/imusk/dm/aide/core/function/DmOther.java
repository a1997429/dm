package com.github.imusk.dm.aide.core.function;


import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-08 16:10:42
 * @classname: DmOther
 * @description: 杂项
 */
public class DmOther {

    private final static Logger logger = LoggerFactory.getLogger(DmOther.class);

    private DmSoft dm;

    public DmOther(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 激活指定窗口所在进程的输入法.
     *
     * @param hwnd         窗口句柄
     * @param input_method 输入法名字。具体输入法名字对应表查看注册表中以下位置:
     *                     HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Keyboard Layouts
     *                     下面的每一项下的Layout Text的值就是输入法名字
     *                     比如 "中文 - QQ拼音输入法"
     *                     以此类推.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm_ret = dm.ActiveInputMethod(hwnd,"中文 - QQ拼音输入法")
     */
    public Integer ActiveInputMethod(Long hwnd, String input_method) throws COMException {
        return Integer.parseInt(dm.invokeN("ActiveInputMethod", new Object[]{hwnd, input_method}).toString());
    }


    /**
     * 检测指定窗口所在线程输入法是否开启.
     *
     * @param hwnd         窗口句柄
     * @param input_method 输入法名字。具体输入法名字对应表查看注册表中以下位置:
     *                     HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Keyboard Layouts
     *                     下面的每一项下的Layout Text的值就是输入法名字
     *                     比如 "中文 - QQ拼音输入法"
     *                     以此类推.
     *
     * @return Integer 0 : 未开启 1 : 开启
     *
     * @throws COMException
     * @example dm_ret = dm.CheckInputMethod(hwnd,"中文 - QQ拼音输入法")
     */
    public Integer CheckInputMethod(Long hwnd, String input_method) throws COMException {
        return Integer.parseInt(dm.invokeN("CheckInputMethod", new Object[]{hwnd, input_method}).toString());
    }


    /**
     * 检测系统中是否安装了指定输入法.
     *
     * @param input_method 输入法名字。具体输入法名字对应表查看注册表中以下位置:
     *                     HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Keyboard Layouts
     *                     下面的每一项下的Layout Text的值就是输入法名字
     *                     比如 "中文 - QQ拼音输入法"
     *                     以此类推.
     *
     * @return Integer 0 : 未安装 1 : 已安装
     *
     * @throws COMException
     * @example dm_ret = dm.FindInputMethod("中文 - QQ拼音输入法")
     */
    public Integer FindInputMethod(String input_method) throws COMException {
        return Integer.parseInt(dm.invokeN("FindInputMethod", new Object[]{input_method}).toString());
    }

}


