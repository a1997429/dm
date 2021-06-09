package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-09 10:52:03
 * @classname: DmWindowPlus
 * @description: 窗口
 * @version: 7.2107
 */
public class DmWindowPlus extends DmWindow {

    private final static Logger logger = LoggerFactory.getLogger(DmWindowPlus.class);

    private DmSoft dm;

    public DmWindowPlus(DmSoft dm) {
        super(dm);
        this.dm = dm;
    }


    /**
     * 根据指定进程名,枚举系统中符合条件的进程PID,并且按照进程打开顺序排序.
     *
     * @param name 进程名,比如qq.exe
     *
     * @return String 返回所有匹配的进程PID,并按打开顺序排序,格式"pid1,pid2,pid3"
     *
     * @throws COMException
     * @example pids = dm.EnumProcess("notepad.exe")
     */
    public String EnumProcess(String name) throws COMException {
        return dm.invokeN("EnumProcess", new Object[]{name}).toString();
    }


    /**
     * 根据指定进程pid以及其它条件,枚举系统中符合条件的窗口,可以枚举到按键自带的无法枚举到的窗口
     *
     * @param pid        进程pid.
     * @param title      窗口标题. 此参数是模糊匹配.
     * @param class_name 窗口类名. 此参数是模糊匹配.
     * @param filter     1 : 匹配窗口标题,参数title有效
     *                   2 : 匹配窗口类名,参数class_name有效
     *                   8 : 匹配父窗口为0的窗口,即顶级窗口
     *                   16 : 匹配可见的窗口
     *                   这些值可以相加,比如2+8+16
     *
     * @return String 返回所有匹配的窗口句柄字符串,格式"hwnd1,hwnd2,hwnd3"
     *
     * @throws COMException
     * @example hwnds = dm.EnumWindowByProcessId(1124,"天龙八部","",1+8+16)
     */
    public String EnumWindowByProcessId(Long pid, String title, String class_name, Integer filter) throws COMException {
        return dm.invokeN("EnumWindowByProcessId", new Object[]{pid, title, class_name, filter}).toString();
    }


    /**
     * 根据指定的pid获取进程详细信息,(进程名,进程全路径,CPU占用率(百分比),内存占用量(字节))
     *
     * @param pid 进程pid
     *
     * @return String 格式"进程名|进程路径|cpu|内存"
     *
     * @throws COMException
     * @example infos = dm.GetProcessInfo(1348)
     */
    public String GetProcessInfo(Long pid) throws COMException {
        return dm.invokeN("GetProcessInfo", new Object[]{pid}).toString();
    }


    /**
     * 利用真实的输入法，对指定的窗口输入文字.
     *
     * @param hwnd 窗口句柄
     * @param str  发送的文本数据
     * @param mode 0 : 向hwnd的窗口输入文字(前提是必须先用模式200安装了输入法)
     *             1 : 同模式0,如果由于保护无效，可以尝试此模式.(前提是必须先用模式200安装了输入法)
     *             2 : 同模式0,如果由于保护无效，可以尝试此模式. (前提是必须先用模式200安装了输入法)
     *             200 : 向系统中安装输入法,多次调用没问题. 全局只用安装一次.
     *             300 : 卸载系统中的输入法. 全局只用卸载一次. 多次调用没关系.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm.SendStringIme2(hwnd, "", 200)
     */
    public Integer SendStringIme2(Long hwnd, String str, Integer mode) throws COMException {
        return Integer.parseInt(dm.invokeN("SendStringIme2", new Object[]{hwnd, str, mode}).toString());
    }

}
