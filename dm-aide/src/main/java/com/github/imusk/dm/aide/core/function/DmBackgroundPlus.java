package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-09 11:25:34
 * @classname: DmBackgroundPlus
 * @description: 后台设置
 * @version: 7.2107
 */
public class DmBackgroundPlus extends DmBackground {

    private final static Logger logger = LoggerFactory.getLogger(DmBackgroundPlus.class);

    private DmSoft dm;

    public DmBackgroundPlus(DmSoft dm) {
        super(dm);
        this.dm = dm;
    }


    /**
     * 获取当前对象已经绑定的窗口句柄. 无绑定返回0
     *
     * @return Integer 0: 无绑定，非0: 窗口句柄
     *
     * @throws COMException
     * @example bind_hwnd = dm.GetBindWindow()
     */
    public Long GetBindWindow() throws COMException {
        return Long.parseLong(dm.invoke("GetBindWindow").toString());
    }


    /**
     * 获取绑定窗口的fps. (即时fps,不是平均fps).
     * 要想获取fps,那么图色模式必须是dx模式的其中一种.  比如dx.graphic.3d  dx.graphic.opengl等.
     *
     * @return Integer fps
     *
     * @throws COMException
     * @example fps = dm.GetFps()
     */
    public Integer GetFps() throws COMException {
        return Integer.parseInt(dm.invoke("GetFps").toString());
    }


    /**
     * 对目标窗口设置加速功能(类似变速齿轮),必须在绑定参数中有dx.public.hack.speed时才会生效.
     *
     * @param rate 取值范围大于0. 默认是1.0 表示不加速，也不减速. 小于1.0表示减速,大于1.0表示加速. 精度为小数点后1位. 也就是说1.5 和 1.56其实是一样的.
     *
     * @return Integer 0: 失败, 1:成功
     *
     * @throws COMException
     * @example 示例
     * dm_ret = dm.BindWindowEx(hwnd,"normal","normal","normal","dx.public.hack.speed",0)
     * // 2倍加速
     * dm.HackSpeed 2.0
     * // 2.5倍
     * dm.HackSpeed 2.5
     * // 10.1倍
     * dm.HackSpeed 10.1
     * // 速度降低为原来的一半
     * dm.HackSpeed 0.5
     * // 速度降低为原来的十分之一
     * dm.HackSpeed 0.1
     */
    public Integer HackSpeed(Double rate) throws COMException {
        return Integer.parseInt(dm.invokeN("HackSpeed", new Object[]{rate}).toString());
    }


    /**
     * 设置开启或者关闭系统的Aero效果. (仅对WIN7及以上系统有效)
     *
     * @param enable 0 关闭
     *               1 开启
     *
     * @return Integer 0: 失败, 1:成功
     *
     * @throws COMException
     * @example dm.SetAero(0)
     */
    public Integer SetAero(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("SetAero", new Object[]{enable}).toString());
    }


    /**
     * 设置opengl图色模式的强制刷新窗口等待时间. 内置为400毫秒.
     *
     * @param time 等待时间，单位是毫秒。 这个值越小,强制刷新的越频繁，相应的窗口可能会导致闪烁.
     *
     * @return Integer 0: 失败, 1:成功
     *
     * @throws COMException
     * @example dm.SetDisplayRefreshDelay(800)
     */
    public Integer SetDisplayRefreshDelay(Integer time) throws COMException {
        return Integer.parseInt(dm.invokeN("SetDisplayRefreshDelay", new Object[]{time}).toString());
    }


    /**
     * 在不解绑的情况下,切换绑定窗口.(必须是同进程窗口)
     *
     * @param hwnd 需要切换过去的窗口句柄
     *
     * @return Integer 0: 失败, 1:成功
     *
     * @throws COMException
     * @example 示例
     * // 绑定为后台
     * dm_ret = dm.BindWindow(hwnd,"dx","dx","dx",101)
     * // 切换
     * hwnd1 = 111
     * dm.SwitchBindWindow(hwnd1)
     */
    public Integer SwitchBindWindow(Long hwnd) throws COMException {
        return Integer.parseInt(dm.invokeN("SwitchBindWindow", new Object[]{hwnd}).toString());
    }


}
