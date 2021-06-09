package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-09 12:32:06
 * @classname: DmKeyboardPlus
 * @description: 键鼠 - 键盘
 * @version: 7.2107
 */
public class DmKeyboardPlus extends DmKeyboard {

    private final static Logger logger = LoggerFactory.getLogger(DmKeyboardPlus.class);

    private DmSoft dm;

    public DmKeyboardPlus(DmSoft dm) {
        super(dm);
        this.dm = dm;
    }


    /**
     * 按住鼠标中键
     * @return Integer 0:失败 1:成功
     * @throws COMException
     */
    public Integer MiddleDown() throws COMException {
        return Integer.parseInt(dm.invoke("MiddleDown").toString());
    }


    /**
     * 弹起鼠标中键
     * @return Integer 0:失败 1:成功
     * @throws COMException
     */
    public Integer MiddleUp() throws COMException {
        return Integer.parseInt(dm.invoke("MiddleUp").toString());
    }


    /**
     * 设置前台键鼠的模拟方式.
     * 驱动功能支持的系统版本号为(win7/win8/win8.1/win10(10240)/win10(10586)/win10(14393)/win10(15063)/win10(16299)/win10(17134)/win10(17763)/win10(18362)/win10(18363)/win10(19041)/win10(19042)
     * 不支持所有的预览版本,仅仅支持正式版本.  除了模式3,其他模式同时支持32位系统和64位系统.
     *
     * @param mode 0 正常模式(默认模式)
     *             1 硬件模拟
     *             2 硬件模拟2(ps2)（仅仅支持标准的3键鼠标，即左键，右键，中键，带滚轮的鼠标,2键和5键等扩展鼠标不支持）
     *             3 硬件模拟3
     *
     * @return Integer 返回值如下：
     * 0  : 插件没注册
     * -1 : 32位系统不支持
     * -2 : 驱动释放失败.
     * -3 : 驱动加载失败.可能是权限不够. 参考UAC权限设置. 或者是被安全软件拦截.
     * 如果是WIN10 1607之后的系统，出现这个错误，可参考这里
     * -10: 设置失败
     * -7 : 系统版本不支持. 可以用winver命令查看系统内部版本号. 驱动只支持正式发布的版本，所有预览版本都不支持.
     * 1  : 成功
     *
     * @throws COMException
     */
    public Integer SetSimMode(Integer mode) throws COMException {
        return Integer.parseInt(dm.invokeN("SetSimMode", new Object[]{mode}).toString());
    }









}
