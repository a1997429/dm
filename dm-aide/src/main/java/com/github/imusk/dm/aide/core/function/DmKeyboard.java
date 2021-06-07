package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-07 11:14:27
 * @classname: DmKeyboard
 * @description: 键鼠 - 键盘
 *
 * key_str     虚拟键码
 * "1",          49
 * "2",          50
 * "3",          51
 * "4",          52
 * "5",          53
 * "6",          54
 * "7",          55
 * "8",          56
 * "9",          57
 * "0",          48
 * "-",          189
 * "=",          187
 * "back",       8
 * "a",          65
 * "b",          66
 * "c",          67
 * "d",          68
 * "e",          69
 * "f",          70
 * "g",          71
 * "h",          72
 * "i",          73
 * "j",          74
 * "k",          75
 * "l",          76
 * "m",          77
 * "n",          78
 * "o",          79
 * "p",          80
 * "q",          81
 * "r",          82
 * "s",          83
 * "t",          84
 * "u",          85
 * "v",          86
 * "w",          87
 * "x",          88
 * "y",          89
 * "z",          90
 * "ctrl",       17
 * "alt",        18
 * "shift",      16
 * "win",        91
 * "space",      32
 * "cap",        20
 * "tab",        9
 * "~",          192
 * "esc",        27
 * "enter",      13
 * "up",         38
 * "down",       40
 * "left",       37
 * "right",      39
 * "option",     93
 * "print",      44
 * "delete",     46
 * "home",       36
 * "end",        35
 * "pgup",       33
 * "pgdn",       34
 * "f1",         112
 * "f2",         113
 * "f3",         114
 * "f4",         115
 * "f5",         116
 * "f6",         117
 * "f7",         118
 * "f8",         119
 * "f9",         120
 * "f10",        121
 * "f11",        122
 * "f12",        123
 * "[",          219
 * "]",          221
 * "\\",         220
 * ";",          186
 * "'",          222
 * ",",          188
 * ".",          190
 * "/",          191
 */
public class DmKeyboard {

    private final static Logger logger = LoggerFactory.getLogger(DmKeyboard.class);

    private DmSoft dm;

    public DmKeyboard(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 获取指定的按键状态.(前台信息,不是后台)
     *
     * @param vk_code 虚拟按键码
     *
     * @return Integer 0:弹起 1:按下
     *
     * @throws COMException
     * @example dm.GetKeyState(13)
     */
    public Integer GetKeyState(Integer vk_code) throws COMException {
        return Integer.parseInt(dm.invokeN("GetKeyState", new Object[]{vk_code}).toString());
    }


    /**
     * 按住指定的虚拟键码
     *
     * @param vk_code 虚拟按键码
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.KeyDown(13)
     */
    public Integer KeyDown(Integer vk_code) throws COMException {
        return Integer.parseInt(dm.invokeN("KeyDown", new Object[]{vk_code}).toString());
    }


    /**
     * 按住指定的虚拟键码
     *
     * @param key_str 字符串描述的键码. 大小写无所谓.
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.KeyDownChar(" enter ")
     * dm.KeyDownChar("1")
     * dm.KeyDownChar("F1")
     * dm.KeyDownChar("a")
     * dm.KeyDownChar("B")
     */
    public Integer KeyDownChar(String key_str) throws COMException {
        return Integer.parseInt(dm.invokeN("KeyDownChar", new Object[]{key_str}).toString());
    }


    /**
     * 按下指定的虚拟键码
     *
     * @param vk_code 虚拟按键码
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.KeyPress(13)
     */
    public Integer KeyPress(Integer vk_code) throws COMException {
        return Integer.parseInt(dm.invokeN("KeyPress", new Object[]{vk_code}).toString());
    }


    /**
     * 按下指定的虚拟键码
     *
     * @param key_str 字符串描述的键码. 大小写无所谓.
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.KeyPressChar(" enter ")
     * dm.KeyPressChar("1")
     * dm.KeyPressChar("F1")
     * dm.KeyPressChar("a")
     * dm.KeyPressChar("B")
     */
    public Integer KeyPressChar(String key_str) throws COMException {
        return Integer.parseInt(dm.invokeN("KeyPressChar", new Object[]{key_str}).toString());
    }


    /**
     * 根据指定的字符串序列，依次按顺序按下其中的字符.(vip)
     *
     * @param key_str 需要按下的字符串序列. 比如"1234","abcd","7389,1462"等.
     * @param delay   每按下一个按键，需要延时多久. 单位毫秒.这个值越大，按的速度越慢。
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.KeyPressStr(" 123, 456 ", 20)
     */
    public Integer KeyPressStr(String key_str, Integer delay) throws COMException {
        return Integer.parseInt(dm.invokeN("KeyPressStr", new Object[]{key_str, delay}).toString());
    }


    /**
     * 弹起来虚拟键vk_code
     *
     * @param vk_code 虚拟按键码
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.KeyUp(13)
     */
    public Integer KeyUp(Integer vk_code) throws COMException {
        return Integer.parseInt(dm.invokeN("KeyUp", new Object[]{vk_code}).toString());
    }


    /**
     * 弹起来虚拟键key_str
     *
     * @param key_str 字符串描述的键码. 大小写无所谓.
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.KeyUpChar(" enter ")
     * dm.KeyUpChar("1")
     * dm.KeyUpChar("F1")
     * dm.KeyUpChar("a")
     * dm.KeyUpChar("B")
     */
    public Integer KeyUpChar(String key_str) throws COMException {
        return Integer.parseInt(dm.invokeN("KeyUpChar", new Object[]{key_str}).toString());
    }


    /**
     * 设置按键时,键盘按下和弹起的时间间隔。高级用户使用。某些窗口可能需要调整这个参数才可以正常按键。
     *
     * @param type  "normal" : 对应normal键盘  默认内部延时为30ms
     *              "windows": 对应windows 键盘 默认内部延时为10ms
     *              "dx" :     对应dx 键盘 默认内部延时为50ms
     * @param delay 延时,单位是毫秒
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.SetKeypadDelay(" dx ", 10)
     */
    public Integer SetKeypadDelay(String type, Integer delay) throws COMException {
        return Integer.parseInt(dm.invokeN("SetKeypadDelay", new Object[]{type, delay}).toString());
    }


    /**
     * 等待指定的按键按下 (前台,不是后台)
     *
     * @param vk_code  虚拟按键码
     * @param time_out 等待多久,单位毫秒. 如果是0，表示一直等待
     *
     * @return Integer 0:超时 1:指定的按键按下
     *
     * @throws COMException
     * @example dm.WaitKey(66, 0)
     */
    public Integer WaitKey(Integer vk_code, Integer time_out) throws COMException {
        return Integer.parseInt(dm.invokeN("WaitKey", new Object[]{vk_code, time_out}).toString());
    }


}
