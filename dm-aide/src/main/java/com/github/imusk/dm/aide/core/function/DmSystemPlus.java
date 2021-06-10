package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-10 22:20
 * @classname: DmSystemPlus
 * @description: 系统
 * @version: 7.2107
 */
public class DmSystemPlus extends DmSystem {

    private final static Logger logger = LoggerFactory.getLogger(DmSystemPlus.class);

    private DmSoft dm;

    public DmSystemPlus(DmSoft dm) {
        super(dm);
        this.dm = dm;
    }


    /**
     * 延时指定范围内随机毫秒,过程中不阻塞UI操作. 一般高级语言使用.按键用不到.
     *
     * @param mis_min 最小毫秒数. 必须大于0
     * @param mis_max 最大毫秒数. 必须大于0
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm.Delays(200, 1000)
     */
    public Integer Delays(Integer mis_min, Integer mis_max) throws COMException {
        return Integer.parseInt(dm.invokeN("Delays", new Object[]{mis_min, mis_max}).toString());
    }


    /**
     * 设置当前的电源设置，禁止关闭显示器，禁止关闭硬盘，禁止睡眠，禁止待机. 不支持XP.
     *
     * @return Integer 0:失败. 1:成功.
     *
     * @throws COMException
     */
    public Integer DisableCloseDisplayAndSleep() throws COMException {
        return Integer.parseInt(dm.invoke("DisableCloseDisplayAndSleep").toString());
    }


    /**
     * 开启当前系统屏幕字体平滑.同时开启系统的ClearType功能.
     *
     * @return Integer 0:失败. 1:成功.
     *
     * @throws COMException
     */
    public Integer EnableFontSmooth() throws COMException {
        return Integer.parseInt(dm.invoke("EnableFontSmooth").toString());
    }


    /**
     * 获取当前CPU类型(intel或者amd).
     *
     * @return Integer 0 : 未知 1 : Intel cpu 2 : AMD cpu
     *
     * @throws COMException
     */
    public Integer GetCpuType() throws COMException {
        return Integer.parseInt(dm.invoke("GetCpuType").toString());
    }


    /**
     * 获取当前CPU的使用率. 用百分比返回.
     *
     * @return Integer 0-100表示的百分比
     *
     * @throws COMException
     */
    public Integer GetCpuUsage() throws COMException {
        return Integer.parseInt(dm.invoke("GetCpuUsage").toString());
    }


    /**
     * 获取本机的指定硬盘的厂商信息. 要求调用进程必须有管理员权限. 否则返回空串.
     *
     * @param index 硬盘序号. 表示是第几块硬盘. 从0开始编号,最小为0,最大为5,也就是最多支持6块硬盘的厂商信息获取.
     *
     * @return String 字符串表达的硬盘厂商信息
     *
     * @throws COMException
     * @example 示例
     * // 获取第一块硬盘的厂商信息
     * model = dm.GetDiskModel(0)
     */
    public String GetDiskModel(Integer index) throws COMException {
        return dm.invokeN("GetDiskModel", new Object[]{index}).toString();
    }


    /**
     * 获取本机的指定硬盘的修正版本信息. 要求调用进程必须有管理员权限. 否则返回空串.
     *
     * @param index 硬盘序号. 表示是第几块硬盘. 从0开始编号,最小为0,最大为5,也就是最多支持6块硬盘的厂商信息获取.
     *
     * @return String 字符串表达的修正版本信息
     *
     * @throws COMException
     * @example 示例
     * // 获取第一块硬盘的修正版本信息
     * reversion = dm.GetDiskReversion(0)
     */
    public String GetDiskReversion(Integer index) throws COMException {
        return dm.invokeN("GetDiskReversion", new Object[]{index}).toString();
    }


    /**
     * 获取本机的显卡信息.
     *
     * @return String 字符串表达的显卡描述信息. 如果有多个显卡,用"|"连接
     *
     * @throws COMException
     */
    public String GetDisplayInfo() throws COMException {
        return dm.invoke("GetDisplayInfo").toString();
    }


    /**
     * 判断当前系统的DPI(文字缩放)是不是100%缩放.
     *
     * @return Integer 0 : 不是 1 : 是
     *
     * @throws COMException
     */
    public Integer GetDPI() throws COMException {
        return Integer.parseInt(dm.invoke("GetDPI").toString());
    }


    /**
     * 判断当前系统使用的非UNICODE字符集是否是GB2312(简体中文)(由于设计插件时偷懒了,使用的是非UNICODE字符集，导致插件必须运行在GB2312字符集环境下).
     *
     * @return Integer 0 : 不是GB2312(简体中文) 1 : 是GB2312(简体中文)
     *
     * @throws COMException
     */
    public Integer GetLocale() throws COMException {
        return Integer.parseInt(dm.invoke("GetLocale").toString());
    }


    /**
     * 获取当前内存的使用率. 用百分比返回.
     *
     * @return 0-100表示的百分比
     *
     * @throws COMException
     */
    public Integer GetMemoryUsage() throws COMException {
        return Integer.parseInt(dm.invoke("GetMemoryUsage").toString());
    }


    /**
     * 根据指定时间服务器IP,从网络获取当前北京时间.
     *
     * @param ip IP或者域名,并且支持多个IP或者域名连接
     *
     * @return String 时间格式. 和now返回一致. 比如"2001-11-01 23:14:08"
     *
     * @throws COMException
     * @example t = dm.GetNetTimeByIp("210.72.145.44|ntp.sjtu.edu.cn")
     */
    public String GetNetTimeByIp(String ip) throws COMException {
        return dm.invokeN("GetNetTimeByIp", new Object[]{ip}).toString();
    }


    /**
     * 得到操作系统的build版本号.  比如win10 16299,那么返回的就是16299. 其他类似
     *
     * @return Integer build 版本号 失败返回0
     *
     * @throws COMException
     */
    public Integer GetOsBuildNumber() throws COMException {
        return Integer.parseInt(dm.invoke("GetOsBuildNumber").toString());
    }


    /**
     * 判断当前CPU是否支持vt,并且是否在bios中开启了vt. 仅支持intel的CPU.
     *
     * @return Integer 0 : 当前cpu不是intel的cpu,或者当前cpu不支持vt,或者bios中没打开vt. 1 : 支持
     *
     * @throws COMException
     */
    public Integer IsSurrpotVt() throws COMException {
        return Integer.parseInt(dm.invoke("IsSurrpotVt").toString());
    }


    /**
     * 设置当前系统的非UNICOD字符集. 会弹出一个字符集选择列表,用户自己选择到简体中文即可.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     */
    public Integer SetLocale() throws COMException {
        return Integer.parseInt(dm.invoke("SetLocale").toString());
    }


    /**
     * 显示或者隐藏指定窗口在任务栏的图标.
     *
     * @param hwnd    指定的窗口句柄
     * @param is_show 0为隐藏,1为显示
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     */
    public Integer ShowTaskBarIcon(Long hwnd, Integer is_show) throws COMException {
        return Integer.parseInt(dm.invokeN("ShowTaskBarIcon", new Object[]{hwnd, is_show}).toString());
    }


}
