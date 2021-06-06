package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.jawin.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-05 14:13
 * @classname: DmWindow
 * @description: 窗口
 */
public class DmWindow {

    private final static Logger logger = LoggerFactory.getLogger(DmWindow.class);

    private DmSoft dm;

    public DmWindow(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 把窗口坐标转换为屏幕坐标
     *
     * @param hwnd 指定的窗口句柄
     * @param x    窗口X坐标
     * @param y    窗口Y坐标
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.ClientToScreen(hwnd,0,0)
     */
    public Integer ClientToScreen(Long hwnd, Variant.ByrefHolder x, Variant.ByrefHolder y) throws COMException {
        return Integer.parseInt(dm.invokeN("ClientToScreen", new Object[]{hwnd, x, y}).toString());
    }


    /**
     * 根据指定条件,枚举系统中符合条件的窗口,可以枚举到按键自带的无法枚举到的窗口
     *
     * @param parent     获得的窗口句柄是该窗口的子窗口的窗口句柄,取0时为获得桌面句柄
     * @param title      窗口标题. 此参数是模糊匹配.
     * @param class_name 窗口类名. 此参数是模糊匹配.
     * @param filter     1 : 匹配窗口标题,参数title有效
     *                   2 : 匹配窗口类名,参数class_name有效.
     *                   4 : 只匹配指定父窗口的第一层孩子窗口
     *                   8 : 匹配所有者窗口为0的窗口,即顶级窗口
     *                   16 : 匹配可见的窗口
     *                   32 : 匹配出的窗口按照窗口打开顺序依次排列(vip)
     *                   这些值可以相加,比如4+8+16就是类似于任务管理器中的窗口列表
     *
     * @return String 返回所有匹配的窗口句柄字符串,格式"hwnd1,hwnd2,hwnd3"
     *
     * @throws COMException
     * @example hwnds = dm.EnumWindow(0,"QQ三国","",1+4+8+16)
     */
    public String EnumWindow(Long parent, String title, String class_name, Integer filter) throws COMException {
        return dm.invokeN("EnumWindow", new Object[]{parent, title, class_name, filter}).toString();
    }


    /**
     * 根据指定进程以及其它条件,枚举系统中符合条件的窗口,可以枚举到按键自带的无法枚举到的窗口
     *
     * @param process_name 进程映像名.比如(svchost.exe). 此参数是精确匹配,但不区分大小写.
     * @param title        窗口标题. 此参数是模糊匹配.
     * @param class_name   窗口类名. 此参数是模糊匹配.
     * @param filter       1 : 匹配窗口标题,参数title有效
     *                     2 : 匹配窗口类名,参数class_name有效.
     *                     4 : 只匹配指定父窗口的第一层孩子窗口
     *                     8 : 匹配所有者窗口为0的窗口,即顶级窗口
     *                     16 : 匹配可见的窗口
     *                     32 : 匹配出的窗口按照窗口打开顺序依次排列(vip)
     *                     这些值可以相加,比如4+8+16就是类似于任务管理器中的窗口列表
     *
     * @return String 返回所有匹配的窗口句柄字符串,格式"hwnd1,hwnd2,hwnd3"
     *
     * @throws COMException
     * @example hwnds = dm.EnumWindowByProcess("game.exe","天龙八部","",1+8+16)
     */
    public String EnumWindowByProcess(String process_name, String title, String class_name, Integer filter) throws COMException {
        return dm.invokeN("EnumWindowByProcess", new Object[]{process_name, title, class_name, filter}).toString();
    }


    /**
     * 根据两组设定条件来枚举指定窗口.(vip)
     *
     * @param spec1 查找串1. (内容取决于flag1的值)
     * @param flag1 0表示spec1的内容是标题
     *              1表示spec1的内容是程序名字. (比如notepad)
     *              2表示spec1的内容是类名
     *              3表示spec1的内容是程序路径.(不包含盘符,比如\windows\system32)
     *              4表示spec1的内容是父句柄.(十进制表达的串)
     *              5表示spec1的内容是父窗口标题
     *              6表示spec1的内容是父窗口类名
     *              7表示spec1的内容是顶级窗口句柄.(十进制表达的串)
     *              8表示spec1的内容是顶级窗口标题
     *              9表示spec1的内容是顶级窗口类名
     * @param type1 0精确判断
     *              1模糊判断
     * @param spec2 查找串2. (内容取决于flag2的值)
     * @param flag2 0表示spec2的内容是标题
     *              1表示spec2的内容是程序名字. (比如notepad)
     *              2表示spec2的内容是类名
     *              3表示spec2的内容是程序路径.(不包含盘符,比如\windows\system32)
     *              4表示spec2的内容是父句柄.(十进制表达的串)
     *              5表示spec2的内容是父窗口标题
     *              6表示spec2的内容是父窗口类名
     *              7表示spec2的内容是顶级窗口句柄.(十进制表达的串)
     *              8表示spec2的内容是顶级窗口标题
     *              9表示spec2的内容是顶级窗口类名
     * @param type2 0精确判断
     *              1模糊判断
     * @param sort  0不排序.
     *              1对枚举出的窗口进行排序,按照窗口打开顺序.
     *
     * @return String 返回所有匹配的窗口句柄字符串,格式"hwnd1,hwnd2,hwnd3"
     *
     * @throws COMException
     * @example hwnds = dm.EnumWindowSuper("记事本",0,1,"notepad",1,0,0)
     */
    public String EnumWindowSuper(String spec1, Integer flag1, Integer type1, String spec2, Integer flag2, Integer type2, Integer sort) throws COMException {
        return dm.invokeN("EnumWindowSuper", new Object[]{spec1, flag1, type1, spec2, flag2, type2, sort}).toString();
    }


    /**
     * 查找符合类名或者标题名的顶层可见窗口
     *
     * @param class_name 窗口类名，如果为空，则匹配所有. 这里的匹配是模糊匹配.
     * @param title      窗口标题,如果为空，则匹配所有.这里的匹配是模糊匹配.
     *
     * @return Long 整形数表示的窗口句柄，没找到返回0
     *
     * @throws COMException
     * @example hwnd = dm.FindWindow("","记事本")
     */
    public Long FindWindow(String class_name, String title) throws COMException {
        return Long.parseLong(dm.invokeN("FindWindow", new Object[]{class_name, title}).toString());
    }


    /**
     * 根据指定的进程名字，来查找可见窗口.(vip)
     *
     * @param process_name 进程名. 比如(notepad.exe).这里是精确匹配,但不区分大小写.
     * @param class_name   窗口类名，如果为空，则匹配所有. 这里的匹配是模糊匹配.
     * @param title        窗口标题,如果为空，则匹配所有.这里的匹配是模糊匹配.
     *
     * @return Long 整形数表示的窗口句柄，没找到返回0
     *
     * @throws COMException
     * @example hwnd = dm.FindWindowByProcess("noteapd.exe","","记事本")
     */
    public Long FindWindowByProcess(String process_name, String class_name, String title) throws COMException {
        return Long.parseLong(dm.invokeN("FindWindowByProcess", new Object[]{process_name, class_name, title}).toString());
    }


    /**
     * 根据指定的进程Id，来查找可见窗口.(vip)
     *
     * @param process_id 进程id.
     * @param class_name 窗口类名，如果为空，则匹配所有. 这里的匹配是模糊匹配.
     * @param title      窗口标题,如果为空，则匹配所有.这里的匹配是模糊匹配.
     *
     * @return Long 整形数表示的窗口句柄，没找到返回0
     *
     * @throws COMException
     * @example hwnd = dm.FindWindowByProcessId(123456,"","记事本")
     */
    public Long FindWindowByProcessId(Long process_id, String class_name, String title) throws COMException {
        return Long.parseLong(dm.invokeN("FindWindowByProcessId", new Object[]{process_id, class_name, title}).toString());
    }


    /**
     * 查找符合类名或者标题名的顶层可见窗口,如果指定了parent,则在parent的第一层子窗口中查找.
     *
     * @param process    父窗口句柄，如果为空，则匹配所有顶层窗口
     * @param class_name 窗口类名，如果为空，则匹配所有. 这里的匹配是模糊匹配.
     * @param title      窗口标题,如果为空，则匹配所有.这里的匹配是模糊匹配.
     *
     * @return Long 整形数表示的窗口句柄，没找到返回0
     *
     * @throws COMException
     * @example hwnd = dm.FindWindowEx(0,"","记事本")
     */
    public Long FindWindowEx(Long process, String class_name, String title) throws COMException {
        return Long.parseLong(dm.invokeN("FindWindowEx", new Object[]{process, class_name, title}).toString());
    }


    /**
     * 根据两组设定条件来查找指定窗口.(vip)
     *
     * @param spec1 查找串1. (内容取决于flag1的值)
     * @param flag1 0表示spec1的内容是标题
     *              1表示spec1的内容是程序名字. (比如notepad)
     *              2表示spec1的内容是类名
     *              3表示spec1的内容是程序路径.(不包含盘符,比如\windows\system32)
     *              4表示spec1的内容是父句柄.(十进制表达的串)
     *              5表示spec1的内容是父窗口标题
     *              6表示spec1的内容是父窗口类名
     *              7表示spec1的内容是顶级窗口句柄.(十进制表达的串)
     *              8表示spec1的内容是顶级窗口标题
     *              9表示spec1的内容是顶级窗口类名
     * @param type1 0精确判断
     *              1模糊判断
     * @param spec2 查找串2. (内容取决于flag2的值)
     * @param flag2 0表示spec2的内容是标题
     *              1表示spec2的内容是程序名字. (比如notepad)
     *              2表示spec2的内容是类名
     *              3表示spec2的内容是程序路径.(不包含盘符,比如\windows\system32)
     *              4表示spec2的内容是父句柄.(十进制表达的串)
     *              5表示spec2的内容是父窗口标题
     *              6表示spec2的内容是父窗口类名
     *              7表示spec2的内容是顶级窗口句柄.(十进制表达的串)
     *              8表示spec2的内容是顶级窗口标题
     *              9表示spec2的内容是顶级窗口类名
     * @param type2 0精确判断
     *              1模糊判断
     *
     * @return Long 整形数表示的窗口句柄，没找到返回0
     *
     * @throws COMException
     * @example hwnd = dm.FindWindowSuper("记事本",0,1,"notepad",1,0)
     */
    public Long FindWindowSuper(String spec1, Integer flag1, Integer type1, String spec2, Integer flag2, Integer type2) throws COMException {
        return Long.parseLong(dm.invokeN("FindWindowSuper", new Object[]{spec1, flag1, type1, spec2, flag2, type2}).toString());
    }


    /**
     * 获取窗口客户区域在屏幕上的位置
     *
     * @param hwnd 指定的窗口句柄
     * @param x1   返回窗口客户区左上角X坐标
     * @param y1   返回窗口客户区左上角Y坐标
     * @param x2   返回窗口客户区右下角X坐标
     * @param y2   返回窗口客户区右下角Y坐标
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.GetClientRect(hwnd,x1,y1,x2,y2)
     */
    public Integer GetClientRect(Long hwnd, Variant.ByrefHolder x1, Variant.ByrefHolder y1, Variant.ByrefHolder x2, Variant.ByrefHolder y2) throws COMException {
        return Integer.parseInt(dm.invokeN("GetClientRect", new Object[]{hwnd, x1, y1, x2, y2}).toString());
    }


    /**
     * 获取窗口客户区域的宽度和高度
     *
     * @param hwnd   指定的窗口句柄
     * @param width  宽度
     * @param height 高度
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.GetClientSize(hwnd,w,h)
     */
    public Integer GetClientSize(Long hwnd, Variant.ByrefHolder width, Variant.ByrefHolder height) throws COMException {
        return Integer.parseInt(dm.invokeN("GetClientSize", new Object[]{hwnd, width, height}).toString());
    }


    /**
     * 获取顶层活动窗口中具有输入焦点的窗口句柄
     *
     * @return Long 返回整型表示的窗口句柄
     *
     * @throws COMException
     * @example hwnd = dm.GetForegroundFocus()
     */
    public Long GetForegroundFocus() throws COMException {
        return Long.parseLong(dm.invoke("GetForegroundFocus").toString());
    }


    /**
     * 获取顶层活动窗口,可以获取到按键自带插件无法获取到的句柄
     *
     * @return Long 返回整型表示的窗口句柄
     *
     * @throws COMException
     * @example hwnd = dm.GetForegroundWindow()
     */
    public Long GetForegroundWindow() throws COMException {
        return Long.parseLong(dm.invoke("GetForegroundWindow").toString());
    }


    /**
     * 获取鼠标指向的窗口句柄,可以获取到按键自带的插件无法获取到的句柄
     *
     * @return Long 返回整型表示的窗口句柄
     *
     * @throws COMException
     * @example hwnd = dm.GetMousePointWindow()
     */
    public Long GetMousePointWindow() throws COMException {
        return Long.parseLong(dm.invoke("GetMousePointWindow").toString());
    }


    /**
     * 获取给定坐标的窗口句柄,可以获取到按键自带的插件无法获取到的句柄
     *
     * @param x 屏幕X坐标
     * @param y 屏幕Y坐标
     *
     * @return Long 返回整型表示的窗口句柄
     *
     * @throws COMException
     * @example hwnd = dm.GetPointWindow(100,100)
     */
    public Long GetPointWindow(Integer x, Integer y) throws COMException {
        return Long.parseLong(dm.invokeN("GetPointWindow", new Object[]{x, y}).toString());
    }


    /**
     * 获取特殊窗口
     *
     * @param flag 0 : 获取桌面窗口
     *             1 : 获取任务栏窗口
     *
     * @return Long 以整型数表示的窗口句柄
     *
     * @throws COMException
     * @example desk_win = dm.GetSpecialWindow(0)
     */
    public Long GetSpecialWindow(Integer flag) throws COMException {
        return Long.parseLong(dm.invokeN("GetSpecialWindow", new Object[]{flag}).toString());
    }


    /**
     * 获取给定窗口相关的窗口句柄
     *
     * @param hwnd 窗口句柄
     * @param flag 0 : 获取父窗口
     *             1 : 获取第一个儿子窗口
     *             2 : 获取First 窗口
     *             3 : 获取Last窗口
     *             4 : 获取下一个窗口
     *             5 : 获取上一个窗口
     *             6 : 获取拥有者窗口
     *             7 : 获取顶层窗口
     *
     * @return Long 以整型数表示的窗口句柄
     *
     * @throws COMException
     * @example own_hwnd = dm.GetWindow(hwnd,6)
     */
    public Long GetWindow(Long hwnd, Integer flag) throws COMException {
        return Long.parseLong(dm.invokeN("GetWindow", new Object[]{hwnd, flag}).toString());
    }


    /**
     * 获取窗口的类名
     *
     * @param hwnd 指定的窗口句柄
     *
     * @return String 指定的窗口句柄
     *
     * @throws COMException
     * @example class_name = dm.GetWindowClass(hwnd)
     */
    public String GetWindowClass(Long hwnd) throws COMException {
        return dm.invokeN("GetWindowClass", new Object[]{hwnd}).toString();
    }


    /**
     * 获取指定窗口所在的进程ID.
     *
     * @param hwnd 窗口句柄
     *
     * @return Long 返回整型表示的是进程ID
     *
     * @throws COMException
     * @example process_id = dm.GetWindowProcessId(hwnd)
     */
    public Long GetWindowProcessId(Long hwnd) throws COMException {
        return Long.parseLong(dm.invokeN("GetWindowProcessId", new Object[]{hwnd}).toString());
    }


    /**
     * 获取指定窗口所在的进程的exe文件全路径.
     *
     * @param hwnd 窗口句柄
     *
     * @return String 返回字符串表示的是exe全路径名
     *
     * @throws COMException
     * @example process_path = dm.GetWindowProcessPath(hwnd)
     */
    public String GetWindowProcessPath(Long hwnd) throws COMException {
        return dm.invokeN("GetWindowProcessPath", new Object[]{hwnd}).toString();
    }


    /**
     * 获取窗口在屏幕上的位置
     *
     * @param hwnd 指定的窗口句柄
     * @param x1   返回窗口左上角X坐标
     * @param y1   返回窗口左上角Y坐标
     * @param x2   返回窗口右下角X坐标
     * @param y2   返回窗口右下角Y坐标
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.GetWindowRect(hwnd,x1,y1,x2,y2)
     */
    public Integer GetWindowRect(Long hwnd, Variant.ByrefHolder x1, Variant.ByrefHolder y1, Variant.ByrefHolder x2, Variant.ByrefHolder y2) throws COMException {
        return Integer.parseInt(dm.invokeN("GetWindowRect", new Object[]{hwnd, x1, y1, x2, y2}).toString());
    }


    /**
     * 获取指定窗口的一些属性
     *
     * @param hwnd 指定的窗口句柄
     * @param flag 0 : 判断窗口是否存在
     *             1 : 判断窗口是否处于激活
     *             2 : 判断窗口是否可见
     *             3 : 判断窗口是否最小化
     *             4 : 判断窗口是否最大化
     *             5 : 判断窗口是否置顶
     *             6 : 判断窗口是否无响应
     *
     * @return Integer 0: 不满足条件，1: 满足条件
     *
     * @throws COMException
     * @example dm_ret = dm.GetWindowState(hwnd,3)
     */
    public Integer GetWindowState(Long hwnd, Integer flag) throws COMException {
        return Integer.parseInt(dm.invokeN("GetWindowState", new Object[]{hwnd, flag}).toString());
    }


    /**
     * 获取窗口的标题
     *
     * @param hwnd 指定的窗口句柄
     *
     * @return String 窗口的标题
     *
     * @throws COMException
     * @example title = dm.GetWindowTitle(hwnd)
     */
    public String GetWindowTitle(Long hwnd) throws COMException {
        return dm.invokeN("GetWindowTitle", new Object[]{hwnd}).toString();
    }


    /**
     * 移动指定窗口到指定位置
     *
     * @param hwnd 指定的窗口句柄
     * @param x    X坐标
     * @param y    Y坐标
     *
     * @return Integer 0: 失败，1: 失败
     *
     * @throws COMException
     * @example dm_ret = dm.MoveWindow(hwnd,-10,-10)
     */
    public Integer MoveWindow(Long hwnd, Integer x, Integer y) throws COMException {
        return Integer.parseInt(dm.invokeN("MoveWindow", new Object[]{hwnd, x, y}).toString());
    }


    /**
     * 把屏幕坐标转换为窗口坐标
     *
     * @param hwnd 指定的窗口句柄
     * @param x    屏幕X坐标
     * @param y    屏幕Y坐标
     *
     * @return Integer 0: 失败，1: 失败
     *
     * @throws COMException
     * @example dm_ret = dm.ScreenToClient(hwnd,10,10)
     */
    public Integer ScreenToClient(Long hwnd, Variant.ByrefHolder x, Variant.ByrefHolder y) throws COMException {
        return Integer.parseInt(dm.invokeN("ScreenToClient", new Object[]{hwnd, x, y}).toString());
    }


    /**
     * 向指定窗口发送粘贴命令. 把剪贴板的内容发送到目标窗口.
     *
     * @param hwnd 指定的窗口句柄
     *
     * @return Integer 0: 失败，1: 失败
     *
     * @throws COMException
     * @example dm.SetClipboard(' abcd ')
     * dm_ret = dm.SendPaste(hwnd)
     */
    public Integer SendPaste(Long hwnd) throws COMException {
        return Integer.parseInt(dm.invokeN("SendPaste", new Object[]{hwnd}).toString());
    }


    /**
     * 向指定窗口发送文本数据
     *
     * @param hwnd 指定的窗口句柄
     * @param str  发送的文本数据
     *
     * @return Integer 0: 失败，1: 失败
     *
     * @throws COMException
     * @example dm.SendString(hwnd, ' abcd ')
     */
    public Integer SendString(Long hwnd, String str) throws COMException {
        return Integer.parseInt(dm.invokeN("SendString", new Object[]{hwnd, str}).toString());
    }


    /**
     * 向指定窗口发送文本数据
     *
     * @param hwnd 指定的窗口句柄
     * @param str  发送的文本数据
     *
     * @return Integer 0: 失败，1: 失败
     *
     * @throws COMException
     * @example dm.SendString2(hwnd, " abcd ")
     * @note 注: 此接口为老的SendString，如果新的SendString不能输入，可以尝试此接口.
     */
    public Integer SendString2(Long hwnd, String str) throws COMException {
        return Integer.parseInt(dm.invokeN("SendString2", new Object[]{hwnd, str}).toString());
    }


    /**
     * 向绑定的窗口发送文本数据.必须配合dx.public.input.ime属性.(vip)
     *
     * @param str 发送的文本数据
     *
     * @return Integer 0: 失败，1: 失败
     *
     * @throws COMException
     * @example dm_ret = dm.BindWindowEx(hwnd,"normal","normal","normal","dx.public.input.ime",0)
     * dm.SendStringIme("我是来测试的")
     */
    public Integer SendStringIme(String str) throws COMException {
        return Integer.parseInt(dm.invokeN("SendStringIme", new Object[]{str}).toString());
    }


    /**
     * 设置窗口客户区域的宽度和高度
     *
     * @param hwnd   指定的窗口句柄
     * @param width  宽度
     * @param height 高度
     *
     * @return Integer 0: 失败，1: 失败
     *
     * @throws COMException
     * @example dm_ret = dm.SetClientSize(hwnd,800,600)
     */
    public Integer SetClientSize(Long hwnd, Integer width, Integer height) throws COMException {
        return Integer.parseInt(dm.invokeN("SetClientSize", new Object[]{hwnd, width, height}).toString());
    }


    /**
     * 设置窗口的大小
     *
     * @param hwnd   指定的窗口句柄
     * @param width  宽度
     * @param height 高度
     *
     * @return Integer 0: 失败，1: 失败
     *
     * @throws COMException
     * @example dm_ret = dm.SetWindowSize(hwnd,300,400)
     */
    public Integer SetWindowSize(Long hwnd, Integer width, Integer height) throws COMException {
        return Integer.parseInt(dm.invokeN("SetWindowSize", new Object[]{hwnd, width, height}).toString());
    }


    /**
     * 设置窗口的状态
     *
     * @param hwnd 指定的窗口句柄
     * @param flag 0 : 关闭指定窗口
     *             1 : 激活指定窗口
     *             2 : 最小化指定窗口,但不激活
     *             3 : 最小化指定窗口,并释放内存,但同时也会激活窗口.
     *             4 : 最大化指定窗口,同时激活窗口.
     *             5 : 恢复指定窗口 ,但不激活
     *             6 : 隐藏指定窗口
     *             7 : 显示指定窗口
     *             8 : 置顶指定窗口
     *             9 : 取消置顶指定窗口
     *             10 : 禁止指定窗口
     *             11 : 取消禁止指定窗口
     *             12 : 恢复并激活指定窗口
     *             13 : 强制结束窗口所在进程.
     *
     * @return Integer 0: 失败，1: 失败
     *
     * @throws COMException
     * @example dm_ret = dm.SetWindowState(hwnd,0)
     */
    public Integer SetWindowState(Long hwnd, Integer flag) throws COMException {
        return Integer.parseInt(dm.invokeN("SetWindowState", new Object[]{hwnd, flag}).toString());
    }


    /**
     * 设置窗口的标题
     *
     * @param hwnd  指定的窗口句柄
     * @param title 标题
     *
     * @return Integer 0: 失败，1: 失败
     *
     * @throws COMException
     * @example dm_ret = dm.SetWindowText(hwnd,"test")
     */
    public Integer SetWindowText(Long hwnd, String title) throws COMException {
        return Integer.parseInt(dm.invokeN("SetWindowText", new Object[]{hwnd, title}).toString());
    }


    /**
     * 设置窗口的透明度
     *
     * @param hwnd  指定的窗口句柄
     * @param trans 透明度取值(0-255) 越小透明度越大 0为完全透明(不可见) 255为完全显示(不透明)
     *
     * @return Integer 0: 失败，1: 失败
     *
     * @throws COMException
     * @example dm_ret = dm.SetWindowTransparent(hwnd,200)
     */
    public Integer SetWindowTransparent(Long hwnd, Integer trans) throws COMException {
        return Integer.parseInt(dm.invokeN("SetWindowTransparent", new Object[]{hwnd, trans}).toString());
    }


}
