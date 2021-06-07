package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.jawin.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-07 11:14:27
 * @classname: DmKeyboard
 * @description: 键鼠 - 鼠标
 */
public class DmMouse {

    private final static Logger logger = LoggerFactory.getLogger(DmMouse.class);

    private DmSoft dm;

    public DmMouse(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 获取鼠标位置.
     *
     * @param x 返回X坐标
     * @param y 返回Y坐标
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm.GetCursorPos(x, y)
     */
    public Integer GetCursorPos(Variant.ByrefHolder x, Variant.ByrefHolder y) throws COMException {
        return Integer.parseInt(dm.invokeN("GetCursorPos", new Object[]{x, y}).toString());
    }


    /**
     * 获取鼠标特征码. 当BindWindow或者BindWindowEx中的mouse参数含有dx.mouse.cursor时，获取到的是后台鼠标特征，否则是前台鼠标特征.   后台特征码是收费功能.(vip)
     *
     * @return String 成功时，返回鼠标特征码. 失败时，返回空的串.
     *
     * @throws COMException
     * @example mouse_tz = dm.GetCursorShape()
     */
    public String GetCursorShape() throws COMException {
        return dm.invoke("GetCursorShape").toString();
    }


    /**
     * 获取鼠标特征码. 当BindWindow或者BindWindowEx中的mouse参数含有dx.mouse.cursor时，获取到的是后台鼠标特征，否则是前台鼠标特征.   后台特征码是收费功能.(vip)
     *
     * @param type 获取鼠标特征码的方式. 和工具中的方式1 方式2对应. 方式1此参数值为0. 方式2此参数值为1.
     *
     * @return String 成功时，返回鼠标特征码. 失败时，返回空的串.
     *
     * @throws COMException
     * @example mouse_tz = dm.GetCursorShapeEx(0)
     */
    public String GetCursorShapeEx(Integer type) throws COMException {
        return dm.invokeN("GetCursorShapeEx", new Object[]{type}).toString();
    }


    /**
     * 获取鼠标热点位置.(参考工具中抓取鼠标后，那个闪动的点就是热点坐标,不是鼠标坐标)当BindWindow或者BindWindowEx中的mouse参数含有dx.mouse.cursor时，获取到的是后台鼠标热点位置，否则是前台鼠标热点位置.  后台热点位置是收费功能.(vip)
     *
     * @return String 成功时，返回形如"x,y"的字符串. 失败时，返回空的串.
     *
     * @throws COMException
     * @example hot_pos = dm.GetCursorSpot()
     */
    public String GetCursorSpot() throws COMException {
        return dm.invoke("GetCursorSpot").toString();
    }


    /**
     * 按下鼠标左键
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.LeftClick()
     */
    public Integer LeftClick() throws COMException {
        return Integer.parseInt(dm.invoke("LeftClick").toString());
    }


    /**
     * 双击鼠标左键
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.LeftDoubleClick()
     */
    public Integer LeftDoubleClick() throws COMException {
        return Integer.parseInt(dm.invoke("LeftDoubleClick").toString());
    }


    /**
     * 按住鼠标左键
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.LeftDown()
     */
    public Integer LeftDown() throws COMException {
        return Integer.parseInt(dm.invoke("LeftDown").toString());
    }


    /**
     * 弹起鼠标左键
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.LeftUp()
     */
    public Integer LeftUp() throws COMException {
        return Integer.parseInt(dm.invoke("LeftUp").toString());
    }


    /**
     * 按下鼠标中键
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.MiddleClick()
     */
    public Integer MiddleClick() throws COMException {
        return Integer.parseInt(dm.invoke("MiddleClick").toString());
    }


    /**
     * 鼠标相对于上次的位置移动rx,ry
     *
     * @param rx 相对于上次的X偏移
     * @param ry 相对于上次的Y偏移
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.MoveR(100, 50)
     */
    public Integer MoveR(Integer rx, Integer ry) throws COMException {
        return Integer.parseInt(dm.invokeN("MoveR", new Object[]{rx, ry}).toString());
    }


    /**
     * 把鼠标移动到目的点(x,y)
     *
     * @param x X坐标
     * @param y Y坐标
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.MoveTo(100, 100)
     */
    public Integer MoveTo(Integer x, Integer y) throws COMException {
        return Integer.parseInt(dm.invokeN("MoveTo", new Object[]{x, y}).toString());
    }


    /**
     * 把鼠标移动到目的范围内的任意一点
     *
     * @param x X坐标
     * @param y Y坐标
     * @param w 宽度(从x计算起)
     * @param h 高度(从y计算起)
     *
     * @return String 返回要移动到的目标点. 格式为x,y.  比如MoveToEx 100,100,10,10,返回值可能是101,102
     *
     * @throws COMException
     * @example 移动鼠标到(100, 100)到(110, 110)这个矩形范围内的任意一点.
     * dm.MoveToEx(100,100,10,10)
     */
    public String MoveToEx(Integer x, Integer y, Integer w, Integer h) throws COMException {
        return dm.invokeN("MoveToEx", new Object[]{x, y, w, h}).toString();
    }


    /**
     * 按下鼠标右键
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.RightClick()
     */
    public Integer RightClick() throws COMException {
        return Integer.parseInt(dm.invoke("RightClick").toString());
    }


    /**
     * 按住鼠标右键
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.RightDown()
     */
    public Integer RightDown() throws COMException {
        return Integer.parseInt(dm.invoke("RightDown").toString());
    }


    /**
     * 弹起鼠标右键
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.RightUp()
     */
    public Integer RightUp() throws COMException {
        return Integer.parseInt(dm.invoke("RightUp").toString());
    }


    /**
     * 设置鼠标单击或者双击时,鼠标按下和弹起的时间间隔。高级用户使用。某些窗口可能需要调整这个参数才可以正常点击。
     *
     * @param type  "normal" : 对应normal鼠标 默认内部延时为 30ms
     *              "windows": 对应windows 鼠标 默认内部延时为 10ms
     *              "dx" :     对应dx鼠标 默认内部延时为40ms
     * @param delay 延时,单位是毫秒
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.SetMouseDelay(" dx ", 10)
     */
    public Integer SetMouseDelay(String type, Integer delay) throws COMException {
        return Integer.parseInt(dm.invokeN("SetMouseDelay", new Object[]{type, delay}).toString());
    }


    /**
     * 滚轮向下滚
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.WheelDown()
     */
    public Integer WheelDown() throws COMException {
        return Integer.parseInt(dm.invoke("WheelDown").toString());
    }


    /**
     * 滚轮向上滚
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.WheelUp()
     */
    public Integer WheelUp() throws COMException {
        return Integer.parseInt(dm.invoke("WheelUp").toString());
    }


}
