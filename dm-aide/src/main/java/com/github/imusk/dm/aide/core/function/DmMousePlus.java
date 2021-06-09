package com.github.imusk.dm.aide.core.function;


import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-09 12:27:20
 * @classname: DmMousePlus
 * @description: 键鼠 - 鼠标
 * @version: 7.2107
 */
public class DmMousePlus extends DmMouse {

    private final static Logger logger = LoggerFactory.getLogger(DmMousePlus.class);

    private DmSoft dm;

    public DmMousePlus(DmSoft dm) {
        super(dm);
        this.dm = dm;
    }


    /**
     * 设置当前系统鼠标的精确度开关. 如果所示。 此接口仅仅对MoveR接口起作用.
     *
     * @param enable 0 关闭指针精确度开关.
     *               1打开指针精确度开关. 一般推荐关闭
     *
     * @return Integer 设置之前的精确度开关.
     *
     * @throws COMException
     * @example dm.SetMouseAccuracy(0)
     */
    public Integer EnableMouseAccuracy(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("EnableMouseAccuracy", new Object[]{enable}).toString());
    }


    /**
     * 获取系统鼠标的移动速度.  如图所示红色区域. 一共分为11个级别. 从1开始,11结束 此接口仅仅对MoveR接口起作用.
     *
     * @return Integer 0:失败 其他值,当前系统鼠标的移动速度
     *
     * @throws COMException
     */
    public Integer GetMouseSpeed() throws COMException {
        return Integer.parseInt(dm.invoke("GetMouseSpeed").toString());
    }


    /**
     * 设置系统鼠标的移动速度.  如图所示红色区域. 一共分为11个级别. 从1开始,11结束。此接口仅仅对MoveR接口起作用.
     *
     * @param speed 鼠标移动速度, 最小1，最大11.  居中为6. 推荐设置为6
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     */
    public Integer SetMouseSpeed(Integer speed) throws COMException {
        return Integer.parseInt(dm.invokeN("SetMouseSpeed", new Object[]{speed}).toString());
    }


}
