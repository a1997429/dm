package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-08 18:36
 * @classname: DmSystem
 * @description: 系统
 */
public class DmSystem {

    private final static Logger logger = LoggerFactory.getLogger(DmSystem.class);

    private DmSoft dm;

    public DmSystem(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 蜂鸣器.
     *
     * @param f        频率
     * @param duration 时长(ms).
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm.Beep(1000, 1000)
     */
    public Integer Beep(Integer f, Integer duration) throws COMException {
        return Integer.parseInt(dm.invokeN("Beep", new Object[]{f, duration}).toString());
    }


    /**
     * 检测当前系统是否有开启屏幕字体平滑.(vip)
     *
     * @return Integer 0 : 检测当前系统是否有开启屏幕字体平滑. 1 : 系统有开启平滑字体.
     *
     * @throws COMException
     */
    public Integer CheckFontSmooth() throws COMException {
        return Integer.parseInt(dm.invoke("CheckFontSmooth").toString());
    }


    /**
     * 检测当前系统是否有开启UAC(用户账户控制).
     *
     * @return Integer 0 : 没开启UAC. 1 : 开启了UAC.
     *
     * @throws COMException
     */
    public Integer CheckUAC() throws COMException {
        return Integer.parseInt(dm.invoke("CheckUAC").toString());
    }


    /**
     * 延时指定的毫秒,过程中不阻塞UI操作. 一般高级语言使用.按键用不到.(vip)
     *
     * @param mis 毫秒数.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     */
    public Integer Delay(Integer mis) throws COMException {
        return Integer.parseInt(dm.invokeN("Delay", new Object[]{mis}).toString());
    }


    /**
     * 关闭当前系统屏幕字体平滑.同时关闭系统的ClearType功能.(vip)
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     */
    public Integer DisableFontSmooth() throws COMException {
        return Integer.parseInt(dm.invoke("DisableFontSmooth").toString());
    }


    /**
     * 关闭电源管理，不会进入睡眠.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     */
    public Integer DisablePowerSave() throws COMException {
        return Integer.parseInt(dm.invoke("DisablePowerSave").toString());
    }


    /**
     * 关闭屏幕保护.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     */
    public Integer DisableScreenSave() throws COMException {
        return Integer.parseInt(dm.invoke("DisableScreenSave").toString());
    }


    /**
     * 退出系统(注销 重启 关机)
     *
     * @param type 0 : 注销系统
     *             1 : 关机
     *             2 : 重新启动
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     */
    public Integer ExitOs(Integer type) throws COMException {
        return Integer.parseInt(dm.invoke("ExitOs", new Object[]{type}).toString());
    }


    /**
     * 获取剪贴板的内容.
     *
     * @return String 以字符串表示的剪贴板内容
     *
     * @throws COMException
     */
    public String GetClipboard() throws COMException {
        return dm.invoke("GetClipboard").toString();
    }


    /**
     * 得到系统的路径.
     *
     * @param type 0 : 获取当前路径
     *             1 : 获取系统路径(system32路径)
     *             2 : 获取windows路径(windows所在路径)
     *             3 : 获取临时目录路径(temp)
     *             4 : 获取当前进程(exe)所在的路径
     *
     * @return String 返回路径
     *
     * @throws COMException
     */
    public String GetDir(Integer type) throws COMException {
        return dm.invokeN("GetDir", new Object[]{type}).toString();
    }


    /**
     * 获取本机的硬盘序列号.支持ide scsi硬盘. 要求调用进程必须有管理员权限. 否则返回空串.
     *
     * @return String 字符串表达的硬盘序列号
     *
     * @throws COMException
     */
    public String GetDiskSerial() throws COMException {
        return dm.invoke("GetDiskSerial").toString();
    }


    /**
     * 获取本机的机器码.(带网卡). 此机器码用于插件网站后台. 要求调用进程必须有管理员权限. 否则返回空串.
     *
     * @return String 字符串表达的机器机器码
     *
     * @throws COMException
     */
    public String GetMachineCode() throws COMException {
        return dm.invoke("GetMachineCode").toString();
    }


    /**
     * 获取本机的机器码.(不带网卡) 要求调用进程必须有管理员权限. 否则返回空串.
     *
     * @return String 字符串表达的机器机器码
     *
     * @throws COMException
     */
    public String GetMachineCodeNoMac() throws COMException {
        return dm.invoke("GetMachineCodeNoMac").toString();
    }


    /**
     * 从网络获取当前北京时间.
     *
     * @return String 时间格式. 和now返回一致. 比如"2001-11-01 23:14:08"
     *
     * @throws COMException
     */
    public String GetNetTime() throws COMException {
        return dm.invoke("GetNetTime").toString();
    }


    /**
     * 从网络获取当前北京时间. 同GetNetTime. 但此接口数据是加密传送,以免被人破解.(vip)
     *
     * @return String 时间格式. 和now返回一致. 比如"2001-11-01 23:14:08"
     *
     * @throws COMException
     */
    public String GetNetTimeSafe() throws COMException {
        return dm.invoke("GetNetTimeSafe").toString();
    }


    /**
     * 得到操作系统的类型
     *
     * @return Integer 操作系统的类型
     * 0 : win95/98/me/nt4.0
     * 1 : xp/2000
     * 2 : 2003
     * 3 : win7/vista/2008
     *
     * @throws COMException
     */
    public Integer GetOsType() throws COMException {
        return Integer.parseInt(dm.invoke("GetOsType").toString());
    }


    /**
     * 获取屏幕的色深.
     *
     * @return Integer 返回系统颜色深度.(16或者32等)
     *
     * @throws COMException
     */
    public Integer GetScreenDepth() throws COMException {
        return Integer.parseInt(dm.invoke("GetScreenDepth").toString());
    }


    /**
     * 获取屏幕的高度.
     *
     * @return Integer 返回屏幕的高度
     *
     * @throws COMException
     */
    public Integer GetScreenHeight() throws COMException {
        return Integer.parseInt(dm.invoke("GetScreenHeight").toString());
    }


    /**
     * 获取屏幕的宽度.
     *
     * @return Integer 返回屏幕的宽度
     *
     * @throws COMException
     */
    public Integer GetScreenWidth() throws COMException {
        return Integer.parseInt(dm.invoke("GetScreenWidth").toString());
    }


    /**
     * 获取当前系统从开机到现在所经历过的时间，单位是毫秒
     *
     * @return Long 时间(单位毫秒)
     *
     * @throws COMException
     */
    public Long GetTime() throws COMException {
        return Long.parseLong(dm.invoke("GetTime").toString());
    }


    /**
     * 判断当前系统是否是64位操作系统
     *
     * @return Integer 0 : 不是64位系统 1 : 是64位系统
     *
     * @throws COMException
     */
    public Integer Is64Bit() throws COMException {
        return Integer.parseInt(dm.invoke("Is64Bit").toString());
    }


    /**
     * 播放指定的MP3或者wav文件.
     *
     * @param media_file 指定的音乐文件，可以采用文件名或者绝对路径的形式.
     *
     * @return Integer 0 : 失败 非0表示当前播放的ID。可以用Stop来控制播放结束.
     *
     * @throws COMException
     */
    public Integer Play(String media_file) throws COMException {
        return Integer.parseInt(dm.invokeN("Play", new Object[]{media_file}).toString());
    }


    /**
     * 运行指定的应用程序.(vip)
     *
     * @param app_path 指定的可执行程序全路径.
     * @param mode     0 : 普通模式
     *                 1 : 加强模式
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     */
    public Integer RunApp(String app_path, Integer mode) throws COMException {
        return Integer.parseInt(dm.invokeN("RunApp", new Object[]{app_path, mode}).toString());
    }


    /**
     * 以字符串表示的剪贴板内容
     *
     * @param value 以字符串表示的剪贴板内容
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     */
    public Integer SetClipboard(String value) throws COMException {
        return Integer.parseInt(dm.invokeN("SetClipboard", new Object[]{value}).toString());
    }


    /**
     * 设置当前系统的硬件加速级别.(vip)
     *
     * @param level 取值范围为0-5.  0表示关闭硬件加速。5表示完全打开硬件加速.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     */
    public Integer SetDisplayAcceler(Integer level) throws COMException {
        return Integer.parseInt(dm.invokeN("SetDisplayAcceler", new Object[]{level}).toString());
    }


    /**
     * 设置系统的分辨率 系统色深
     *
     * @param width  屏幕宽度
     * @param height 屏幕高度
     * @param depth  系统色深
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     */
    public Integer SetScreen(Integer width, Integer height, Integer depth) throws COMException {
        return Integer.parseInt(dm.invokeN("SetScreen", new Object[]{width, height, depth}).toString());
    }


    /**
     * 设置当前系统的UAC(用户账户控制).
     *
     * @param enable 0 : 关闭UAC
     *               1 : 开启UAC
     *
     * @return Integer 0 : 操作失败 1 : 操作成功
     *
     * @throws COMException
     */
    public Integer SetUAC(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("SetUAC", new Object[]{enable}).toString());
    }


    /**
     * 停止指定的音乐.
     *
     * @param id Play返回的播放id.
     *
     * @return Integer 0 : 失败 1 : 成功.
     *
     * @throws COMException
     */
    public Integer Stop(Integer id) throws COMException {
        return Integer.parseInt(dm.invokeN("Stop", new Object[]{id}).toString());
    }


}
