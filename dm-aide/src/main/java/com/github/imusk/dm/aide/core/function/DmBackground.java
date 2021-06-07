package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-07 09:25:04
 * @classname: DmBackground
 * @description: 后台设置
 */
public class DmBackground {

    private final static Logger logger = LoggerFactory.getLogger(DmBackground.class);

    private DmSoft dm;

    public DmBackground(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 绑定指定的窗口,并指定这个窗口的屏幕颜色获取方式,鼠标仿真模式,键盘仿真模式,以及模式设定
     *
     * @param hwnd    指定的窗口句柄
     * @param display 屏幕颜色获取方式 取值有以下几种
     *                "normal" : 正常模式,平常我们用的前台截屏模式
     *                "gdi" : gdi模式,用于窗口采用GDI方式刷新时. 此模式占用CPU较大.
     *                "gdi2" : gdi2模式,此模式兼容性较强,但是速度比gdi模式要慢许多,如果gdi模式发现后台不刷新时,可以考虑用gdi2模式.
     *                "dx2" : dx2模式,用于窗口采用dx模式刷新,如果dx方式会出现窗口所在进程崩溃的状况,可以考虑采用这种.采用这种方式要保证窗口有一部分在屏幕外.win7或者vista不需要移动也可后台.此模式占用CPU较大.
     *                "dx3" : dx3模式,同dx2模式,但是如果发现有些窗口后台不刷新时,可以考虑用dx3模式,此模式比dx2模式慢许多. 此模式占用CPU较大.
     *                "dx" : dx模式,等同于BindWindowEx中，display设置的"dx.graphic.2d|dx.graphic.3d",具体参考BindWindowEx，注意此模式需要管理员权限
     * @param mouse   鼠标仿真模式 取值有以下几种
     *                "normal" : 正常模式,平常我们用的前台鼠标模式
     *                "windows": Windows模式,采取模拟windows消息方式 同按键自带后台插件.
     *                "windows2": Windows2 模式,采取模拟windows消息方式(锁定鼠标位置) 此模式等同于BindWindowEx中的mouse为以下组合： "dx.mouse.position.lock.api|dx.mouse.position.lock.message|dx.mouse.state.message"，注意此模式需要管理员权限
     *                "windows3": Windows3模式，采取模拟windows消息方式,可以支持有多个子窗口的窗口后台.
     *                "dx": dx模式,采用模拟dx后台鼠标模式,这种方式会锁定鼠标输入.有些窗口在此模式下绑定时，需要先激活窗口再绑定(或者绑定以后激活)，否则可能会出现绑定后鼠标无效的情况.此模式等同于BindWindowEx中的mouse为以下组合："dx.public.active.api|dx.public.active.message|dx.mouse.position.lock.api|dx.mouse.position.lock.message|dx.mouse.state.api|dx.mouse.state.message|dx.mouse.api|dx.mouse.focus.input.api|dx.mouse.focus.input.message|dx.mouse.clip.lock.api|dx.mouse.input.lock.api|dx.mouse.cursor"，注意此模式需要管理员权限
     *                "dx2"：dx2模式,这种方式类似于dx模式,但是不会锁定外部鼠标输入. 有些窗口在此模式下绑定时，需要先激活窗口再绑定(或者绑定以后手动激活)，否则可能会出现绑定后鼠标无效的情况. 此模式等同于BindWindowEx中的mouse为以下组合："dx.public.active.api|dx.public.active.message|dx.mouse.position.lock.api|dx.mouse.state.api|dx.mouse.api|dx.mouse.focus.input.api|dx.mouse.focus.input.message|dx.mouse.clip.lock.api|dx.mouse.input.lock.api| dx.mouse.cursor"，注意此模式需要管理员权限
     * @param keypad  键盘仿真模式 取值有以下几种
     *                "normal" : 正常模式,平常我们用的前台键盘模式
     *                "windows": Windows模式,采取模拟windows消息方式 同按键的后台插件.
     *                "dx": dx模式,采用模拟dx后台键盘模式。有些窗口在此模式下绑定时，需要先激活窗口再绑定(或者绑定以后激活)，否则可能会出现绑定后键盘无效的情况. 此模式等同于BindWindowEx中的keypad为以下组合："dx.public.active.api|dx.public.active.message| dx.keypad.state.api|dx.keypad.api|dx.keypad.input.lock.api"，注意此模式需要管理员权限
     * @param mode    0 : 推荐模式此模式比较通用，而且后台效果是最好的.
     *                1 : 和模式0效果一样，如果模式0会失败时，可以尝试此模式.(vip)
     *                2 : 同模式0,此模式为老的模式0,尽量不要用此模式，除非有兼容性问题.
     *                3 : 同模式1,此模式为老的模式1,尽量不要用此模式，除非有兼容性问题.(vip)
     *                4 : 同模式0,如果模式0有崩溃问题，可以尝试此模式.
     *                5 : 同模式1, 如果模式0有崩溃问题，可以尝试此模式.(vip)
     *                6 : 同模式0，如果模式0有崩溃问题，可以尝试此模式.(vip)
     *                7 : 同模式1，如果模式1有崩溃问题，可以尝试此模式.(vip)
     *                101 : 超级绑定模式. 可隐藏目标进程中的dm.dll.避免被恶意检测.效果要比dx.public.hide.dll好. 推荐使用.(vip)
     *                103 : 同模式101，如果模式101有崩溃问题，可以尝试此模式.(vip)
     *                需要注意的是: 模式1 3 5 7 101 103在大部分窗口下绑定都没问题。但也有少数特殊的窗口，比如有很多子窗口的窗口，对于这种窗口，在绑定时，一定要把，鼠标指向一个可以输入文字的窗口，比如一个文本框，最好能激活这个文本框，这样可以保证绑定的成功.
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example display: dx 鼠标:dx 后台 键盘: dx后台 模式1
     * dm_ret = dm.BindWindow(hwnd,"dx","dx","dx",1)
     */
    public Integer BindWindow(Long hwnd, String display, String mouse, String keypad, Integer mode) throws COMException {
        return Integer.parseInt(dm.invokeN("BindWindow", new Object[]{hwnd, display, mouse, keypad, mode}).toString());
    }


    /**
     * 绑定指定的窗口,并指定这个窗口的屏幕颜色获取方式,鼠标仿真模式,键盘仿真模式.(vip)
     *
     * @param hwnd    指定的窗口句柄
     * @param display 屏幕颜色获取方式 取值有以下几种
     *                "normal" : 正常模式,平常我们用的前台截屏模式
     *                "gdi" : gdi模式,用于窗口采用GDI方式刷新时. 此模式占用CPU较大.
     *                "gdi2" : gdi2模式,此模式兼容性较强,但是速度比gdi模式要慢许多,如果gdi模式发现后台不刷新时,可以考虑用gdi2模式.
     *                "dx2" : dx2模式,用于窗口采用dx模式刷新,如果dx方式会出现窗口所在进程崩溃的状况,可以考虑采用这种.采用这种方式要保证窗口有一部分在屏幕外.win7或者vista不需要移动也可后台.此模式占用CPU较大.
     *                "dx3" : dx3模式,同dx2模式,但是如果发现有些窗口后台不刷新时,可以考虑用dx3模式,此模式比dx2模式慢许多. 此模式占用CPU较大.
     *                dx模式,用于窗口采用dx模式刷新,取值可以是以下任意组合，组合采用"|"符号进行连接  注意此模式需要管理员权限. 支持BindWindow中的缩写模式. 比如dx代表" dx.graphic.2d| dx.graphic.3d"
     *                1. "dx.graphic.2d"  2d窗口的dx图色模式
     *                2. "dx.graphic.2d.2"  2d窗口的dx图色模式  是dx.graphic.2d的增强模式.兼容性更好.(vip)
     *                3. "dx.graphic.3d"  3d窗口的dx图色模式,注意采用这个模式，必须关闭窗口3D视频设置的全屏抗锯齿选项.
     *                4. "dx.graphic.3d.8"  3d窗口的dx8图色模式,注意采用这个模式，必须关闭窗口3D视频设置的全屏抗锯齿选项. 这个模式支持某些老的引擎.(vip)
     * @param mouse   鼠标仿真模式 取值有以下几种
     *                "normal" : 正常模式,平常我们用的前台鼠标模式
     *                "windows": Windows模式,采取模拟windows消息方式 同按键自带后台插件.
     *                "windows2": Windows2 模式,采取模拟windows消息方式(锁定鼠标位置) 此模式等同于BindWindowEx中的mouse为以下组合： "dx.mouse.position.lock.api|dx.mouse.position.lock.message|dx.mouse.state.message"，注意此模式需要管理员权限
     *                "windows3": Windows3模式，采取模拟windows消息方式,可以支持有多个子窗口的窗口后台.
     *                dx模式,取值可以是以下任意组合. 组合采用"|"符号进行连接 注意此模式需要管理员权限.支持BindWindow中的缩写模式,比如windows2代表"dx.mouse.position.lock.api|dx.mouse.position.lock.message|dx.mouse.state.message"
     *                1. "dx.mouse.position.lock.api"  此模式表示通过封锁系统API，来锁定鼠标位置.
     *                2. "dx.mouse.position.lock.message" 此模式表示通过封锁系统消息，来锁定鼠标位置.
     *                3. "dx.mouse.focus.input.api" 此模式表示通过封锁系统API来锁定鼠标输入焦点.
     *                4. "dx.mouse.focus.input.message"此模式表示通过封锁系统消息来锁定鼠标输入焦点.
     *                5. "dx.mouse.clip.lock.api" 此模式表示通过封锁系统API来锁定刷新区域。注意，使用这个模式，在绑定前，必须要让窗口完全显示出来.
     *                6. "dx.mouse.input.lock.api" 此模式表示通过封锁系统API来锁定鼠标输入接口.
     *                7. "dx.mouse.state.api" 此模式表示通过封锁系统API来锁定鼠标输入状态.
     *                8. "dx.mouse.state.message" 此模式表示通过封锁系统消息来锁定鼠标输入状态.
     *                9. "dx.mouse.api"  此模式表示通过封锁系统API来模拟dx鼠标输入.
     *                10. "dx.mouse.cursor"  开启此模式，可以后台获取鼠标特征码. (vip)
     *                11. "dx.mouse.raw.input"  有些窗口需要这个才可以正常操作鼠标. (vip)
     *                12. "dx.mouse.input.lock.api2"  部分窗口在后台操作时，前台鼠标会移动,需要这个属性. (vip)
     *                13. "dx.mouse.input.lock.api3"  部分窗口在后台操作时，前台鼠标会移动,需要这个属性. (vip)
     * @param keypad  键盘仿真模式 取值有以下几种
     *                "normal" : 正常模式,平常我们用的前台键盘模式
     *                "windows": Windows模式,采取模拟windows消息方式 同按键的后台插件.
     *                dx模式,取值可以是以下任意组合. 组合采用"|"符号进行连接 注意此模式需要管理员权限.支持BindWindow中的缩写模式.比如dx代表" dx.public.active.api|dx.public.active.message| dx.keypad.state.api|dx.keypad.api|dx.keypad.input.lock.api"
     *                1. "dx.keypad.input.lock.api" 此模式表示通过封锁系统API来锁定键盘输入接口.
     *                2. "dx.keypad.state.api" 此模式表示通过封锁系统API来锁定键盘输入状态.
     *                3. "dx.keypad.api" 此模式表示通过封锁系统API来模拟dx键盘输入.
     *                4. "dx.keypad.raw.input"  有些窗口需要这个才可以正常操作键盘.(vip)
     * @param plc     公共属性 dx模式共有  注意以下列表中,前面打五角星的表示需要管理员权限
     *                取值可以是以下任意组合. 组合采用"|"符号进行连接 这个值可以为空
     *                1. ★ "dx.public.active.api" 此模式表示通过封锁系统API来锁定窗口激活状态.  注意，部分窗口在此模式下会耗费大量资源慎用.
     *                2. ★ "dx.public.active.message" 此模式表示通过封锁系统消息来锁定窗口激活状态.  注意，部分窗口在此模式下会耗费大量资源 慎用. 另外如果要让此模式生效，必须在绑定前，让绑定窗口处于激活状态,否则此模式将失效. 比如dm.SetWindowState hwnd,1 然后再绑定.
     *                3.    "dx.public.disable.window.position" 此模式将锁定绑定窗口位置.不可与"dx.public.fake.window.min"共用.
     *                4.    "dx.public.disable.window.size" 此模式将锁定绑定窗口,禁止改变大小. 不可与"dx.public.fake.window.min"共用.
     *                5.    "dx.public.disable.window.minmax" 此模式将禁止窗口最大化和最小化,但是付出的代价是窗口同时也会被置顶. 不可与"dx.public.fake.window.min"共用.
     *                6.    "dx.public.fake.window.min" 此模式将允许目标窗口在最小化状态时，仍然能够像非最小化一样操作.. 另注意，此模式会导致任务栏顺序重排，所以如果是多开模式下，会看起来比较混乱，建议单开使用，多开不建议使用. (vip)
     *                7.    "dx.public.hide.dll" 此模式将会隐藏目标进程的大漠插件，避免被检测..另外使用此模式前，请仔细做过测试，此模式可能会造成目标进程不稳定，出现崩溃。(vip)
     *                8. ★ "dx.public.active.api2" 此模式表示通过封锁系统API来锁定窗口激活状态. 部分窗口遮挡无法后台,需要这个属性. (vip)
     *                9. ★ "dx.public.input.ime" 此模式是配合SendStringIme使用. 具体可以查看SendStringIme接口. (vip)
     *                10 ★ "dx.public.graphic.protect" 此模式可以保护dx图色不被恶意检测.同时对dx.keypad.api和dx.mouse.api也有保护效果. (vip)
     *                11 ★ "dx.public.disable.window.show" 禁止目标窗口显示,这个一般用来配合dx.public.fake.window.min来使用. (vip)
     *                12 ★ "dx.public.anti.api" 此模式可以突破部分窗口对后台的保护. (vip)
     *                13 ★ "dx.public.memory" 此模式可以让内存读写函数突破保护.只要绑定成功即可操作内存函数. (vip)
     *                14 ★ "dx.public.km.protect" 此模式可以保护dx键鼠不被恶意检测.最好配合dx.public.anti.api一起使用. 此属性可能会导致部分后台功能失效. (vip)
     *                15    "dx.public.prevent.block"  绑定模式1 3 5 7 101 103下，可能会导致部分窗口卡死. 这个属性可以避免卡死. (vip)
     *                16    "dx.public.ori.proc"  此属性只能用在模式0 1 2 3和101下. 有些窗口在不同的界面下(比如登录界面和登录进以后的界面)，键鼠的控制效果不相同. 那可以用这个属性来尝试让保持一致. 注意的是，这个属性不可以滥用，确保测试无问题才可以使用. 否则可能会导致后台失效. (vip)
     * @param mode    0 : 推荐模式此模式比较通用，而且后台效果是最好的.
     *                1 : 和模式0效果一样，如果模式0会失败时，可以尝试此模式.(vip)
     *                2 : 同模式0,此模式为老的模式0,尽量不要用此模式，除非有兼容性问题.
     *                3 : 同模式1,此模式为老的模式1,尽量不要用此模式，除非有兼容性问题.(vip)
     *                4 : 同模式0,如果模式0有崩溃问题，可以尝试此模式.
     *                5 : 同模式1, 如果模式0有崩溃问题，可以尝试此模式.(vip)
     *                6 : 同模式0，如果模式0有崩溃问题，可以尝试此模式.(vip)
     *                7 : 同模式1，如果模式1有崩溃问题，可以尝试此模式.(vip)
     *                101 : 超级绑定模式. 可隐藏目标进程中的dm.dll.避免被恶意检测.效果要比dx.public.hide.dll好. 推荐使用.(vip)
     *                103 : 同模式101，如果模式101有崩溃问题，可以尝试此模式.(vip)
     *                需要注意的是: 模式1 3 5 7 101 103在大部分窗口下绑定都没问题。但也有少数特殊的窗口，比如有很多子窗口的窗口，对于这种窗口，在绑定时，一定要把，鼠标指向一个可以输入文字的窗口，比如一个文本框，最好能激活这个文本框，这样可以保证绑定的成功.
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.BindWindowEx(hwnd,"normal","dx.mouse.position.lock.api|dx.mouse.position.lock.message","windows","dx.public.active.api",0)
     * dm_ret = dm.BindWindowEx(hwnd,"dx.graphic.2d","dx.mouse.position.lock.api|dx.mouse.position.lock.message","dx.keypad.state.api|dx.keypad.api","",0)
     */
    public Integer BindWindowEx(Long hwnd, String display, String mouse, String keypad, String plc, Integer mode) throws COMException {
        return Integer.parseInt(dm.invokeN("BindWindowEx", new Object[]{hwnd, display, mouse, keypad, plc, mode}).toString());
    }


    /**
     * 降低目标窗口所在进程的CPU占用
     *
     * @param rate 取值范围0到100   取值为0 表示关闭CPU优化. 这个值越大表示降低CPU效果越好.
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.BindWindowEx(hwnd,"dx.graphic.3d","normal","normal","",0)
     * dm.DownCpu(50)
     * @note 注意: 此接口必须在绑定窗口成功以后调用，而且必须保证目标窗口可以支持dx.graphic.3d或者dx.graphic.3d.8或者dx.graphic.2d或者dx.graphic.2d.2方式截图，否则降低CPU无效.
     * 因为降低CPU是通过降低窗口刷新速度来实现，所以注意，开启此功能以后会导致窗口刷新速度变慢.
     */
    public Integer DownCpu(Integer rate) throws COMException {
        return Integer.parseInt(dm.invokeN("DownCpu", new Object[]{rate}).toString());
    }


    /**
     * 设置是否暂时关闭或者开启后台功能. 默认是开启.(vip)
     *
     * @param enable 0 全部关闭(图色键鼠都关闭,也就是说图色,键鼠都是前台,但是如果有指定dx.public.active.message时，在窗口前后台切换时，这个属性会失效.)
     *               -1 只关闭图色.(也就是说图色是normal前台. 键鼠不变)
     *               1 开启(恢复原始状态)
     *               5 同0，也是全部关闭，但是这个模式下，就算窗口在前后台切换时，属性dx.public.active.message的效果也一样不会失效.
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.BindWindow(hwnd,"dx","dx","dx",101)
     * 切换到前台 dm.EnableBind(0)
     * 再切换回后台 dm.EnableBind(1)
     */
    public Integer EnableBind(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("EnableBind", new Object[]{enable}).toString());
    }


    /**
     * 设置是否开启后台假激活功能. 默认是关闭. 一般用不到. 除非有人有特殊需求.（vip）
     *
     * @param enable 0 关闭
     *               1 开启
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.BindWindow(hwnd,"dx","dx","dx",101)
     * 开启 dm.EnableFakeActive(1)
     * 恢复 dm.EnableFakeActive(0)
     */
    public Integer EnableFakeActive(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("EnableFakeActive", new Object[]{enable}).toString());
    }


    /**
     * 设置是否关闭绑定窗口所在进程的输入法.
     *
     * @param enable 1 开启
     *               0 关闭
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.BindWindow(hwnd,"dx","dx","dx",101)
     * 开启 dm.EnableIme(1)
     */
    public Integer EnableIme(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("EnableIme", new Object[]{enable}).toString());
    }


    /**
     * 是否在使用dx键盘时开启windows消息.默认开启.（vip)
     *
     * @param enable 0 禁止
     *               1 开启
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.BindWindow(hwnd,"dx","dx","dx",101)
     * 禁止 dm.EnableKeypadMsg(0)
     */
    public Integer EnableKeypadMsg(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("EnableKeypadMsg", new Object[]{enable}).toString());
    }


    /**
     * 键盘消息发送补丁. 默认是关闭.（vip)
     *
     * @param enable 0 禁止
     *               1 开启
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.BindWindow(hwnd,"dx","dx","dx",101)
     * 禁止 dm.EnableKeypadPatch(1)
     */
    public Integer EnableKeypadPatch(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("EnableKeypadPatch", new Object[]{enable}).toString());
    }


    /**
     * 键盘消息采用同步发送模式.默认异步.(vip)
     *
     * @param enable   0 禁止同步
     *                 1 开启同步
     * @param time_out 单位是毫秒,表示同步等待的最大时间.
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.BindWindow(hwnd,"dx","dx","dx",101)
     * dm.EnableKeypadSync(1, 200)
     */
    public Integer EnableKeypadSync(Integer enable, Integer time_out) throws COMException {
        return Integer.parseInt(dm.invokeN("EnableKeypadSync", new Object[]{enable, time_out}).toString());
    }


    /**
     * 是否在使用dx鼠标时开启windows消息.默认开启.（vip)
     *
     * @param enable 0 禁止
     *               1 开启
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.BindWindow(hwnd,"dx","dx","dx",101)
     * dm.EnableMouseMsg(0)
     */
    public Integer EnableMouseMsg(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("EnableMouseMsg", new Object[]{enable}).toString());
    }


    /**
     * 鼠标消息采用同步发送模式.默认异步.(vip)
     *
     * @param enable   0 禁止同步
     *                 1 开启同步
     * @param time_out 单位是毫秒,表示同步等待的最大时间.
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.BindWindow(hwnd,"dx","dx","dx",101)
     * dm.EnableMouseSync(1, 200)
     */
    public Integer EnableMouseSync(Integer enable, Integer time_out) throws COMException {
        return Integer.parseInt(dm.invokeN("EnableMouseSync", new Object[]{enable, time_out}).toString());
    }


    /**
     * 键盘动作模拟真实操作,点击延时随机.（vip)
     *
     * @param enable 0 关闭模拟
     *               1 开启模拟
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.BindWindow(hwnd,"dx","dx","dx",101)
     * dm.EnableRealKeypad(1)
     * dm.KeyPressChar("E")
     */
    public Integer EnableRealKeypad(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("EnableRealKeypad", new Object[]{enable}).toString());
    }


    /**
     * 鼠标动作模拟真实操作,带移动轨迹,以及点击延时随机.(vip)
     *
     * @param enable     0 关闭模拟
     *                   1 开启模拟
     * @param mousedelay 单位是毫秒. 表示在模拟鼠标移动轨迹时,每移动一次的时间间隔.这个值越大,鼠标移动越慢.
     * @param mousestep  表示在模拟鼠标移动轨迹时,每移动一次的距离. 这个值越大，鼠标移动越快速.
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.BindWindow(hwnd,"dx","dx","dx",101)
     * dm.EnableRealMouse(1, 20, 30)
     * dm.MoveTo(100, 100)
     */
    public Integer EnableRealMouse(Integer enable, Integer mousedelay, Integer mousestep) throws COMException {
        return Integer.parseInt(dm.invokeN("EnableRealMouse", new Object[]{enable, mousedelay, mousestep}).toString());
    }


    /**
     * 设置是否开启高速dx键鼠模式。 默认是关闭.
     *
     * @param enable 0 关闭
     *               1 开启
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm.EnableSpeedDx(1)
     */
    public Integer EnableSpeedDx(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("EnableSpeedDx", new Object[]{enable}).toString());
    }


    /**
     * 强制解除绑定窗口,并释放系统资源.(vip)
     *
     * @param hwnd 需要强制解除绑定的窗口句柄.
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.ForceUnBindWindow(hwnd)
     */
    public Integer ForceUnBindWindow(Long hwnd) throws COMException {
        return Integer.parseInt(dm.invokeN("ForceUnBindWindow", new Object[]{hwnd}).toString());
    }


    /**
     * 判定指定窗口是否已经被后台绑定. (前台无法判定)(vip)
     *
     * @param hwnd 窗口句柄
     *
     * @return Integer 0: 没绑定,或者窗口不存在. 1: 已经绑定.
     *
     * @throws COMException
     * @example dm_ret = dm.IsBind(hwnd)
     */
    public Integer IsBind(Long hwnd) throws COMException {
        return Integer.parseInt(dm.invokeN("ForceUnBindWindow", new Object[]{hwnd}).toString());
    }


    /**
     * 锁定指定窗口的图色数据(不刷新).(vip)
     *
     * @param lock 0关闭锁定
     *             1 开启锁定
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.BindWindow(hwnd,"dx","dx2","dx",0)
     * dm.LockDisplay(1)
     * // 这里做需要锁定做的事情
     * dm.LockDisplay(0)
     */
    public Integer LockDisplay(Integer lock) throws COMException {
        return Integer.parseInt(dm.invokeN("LockDisplay", new Object[]{lock}).toString());
    }


    /**
     * 禁止外部输入到指定窗口
     *
     * @param lock 0关闭锁定
     *             1 开启锁定(键盘鼠标都锁定)
     *             2 只锁定鼠标
     *             3 只锁定键盘
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.BindWindow(hwnd,"dx","dx2","dx",0)
     * dm.LockInput(1)
     * // 这里做需要锁定做的事情
     * dm.LockInput(0)
     * @note 注意:此接口只针对dx键鼠. 普通键鼠无效.
     */
    public Integer LockInput(Integer lock) throws COMException {
        return Integer.parseInt(dm.invokeN("LockInput", new Object[]{lock}).toString());
    }


    /**
     * 设置前台鼠标在屏幕上的活动范围.
     *
     * @param x1 区域的左上X坐标. 屏幕坐标.
     * @param y1 区域的左上Y坐标. 屏幕坐标.
     * @param x2 区域的右下X坐标. 屏幕坐标.
     * @param y2 区域的右下Y坐标. 屏幕坐标.
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example 限制鼠标只能在10, 10, 800, 600区域内活动.
     * dm.LockMouseRect(10,10,800,600)
     * 恢复,4个参数都是0,表示恢复鼠标活动范围为整个屏幕区域.
     * dm.LockMouseRect(0,0,0,0)
     */
    public Integer LockMouseRect(Integer x1, Integer y1, Integer x2, Integer y2) throws COMException {
        return Integer.parseInt(dm.invokeN("LockMouseRect", new Object[]{x1, y1, x2, y2}).toString());
    }


    /**
     * 设置dx截图最长等待时间。内部默认是3000毫秒. 一般用不到调整这个.
     *
     * @param time 等待时间，单位是毫秒。 注意这里不能设置的过小，否则可能会导致截图失败,从而导致图色函数和文字识别失败.
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm.SetDisplayDelay(500)
     */
    public Integer SetDisplayDelay(Integer time) throws COMException {
        return Integer.parseInt(dm.invokeN("SetDisplayDelay", new Object[]{time}).toString());
    }


    /**
     * 设置前台键鼠的模拟方式.(vip)
     *
     * @param mode 0 正常模式(默认模式)
     *             1 超级模拟
     *             2 硬件模拟
     *
     * @return Integer 设置结果
     * 0  : 插件没注册
     * -1 : 64位平台不支持
     * -2 : 驱动释放失败.
     * -3 : 驱动加载失败.可能是权限不够. 参考UAC权限设置.
     * 1  : 成功
     *
     * @throws COMException
     * @example dm.SetSimMode(1)
     */
    public Integer SetSimMode(Integer mode) throws COMException {
        return Integer.parseInt(dm.invokeN("SetSimMode", new Object[]{mode}).toString());
    }


    /**
     * 解除绑定窗口,并释放系统资源.一般在OnScriptExit调用
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.UnBindWindow()
     */
    public Integer UnBindWindow() throws COMException {
        return Integer.parseInt(dm.invoke("UnBindWindow").toString());
    }


}
