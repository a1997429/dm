package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-05 12:59
 * @classname: DmFoobar
 * @description: Foobar
 */
public class DmFoobar {

    private final static Logger logger = LoggerFactory.getLogger(DmFoobar.class);

    private DmSoft dm;

    public DmFoobar(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 根据指定的位图创建一个自定义形状的窗口
     *
     * @param hwnd        指定的窗口句柄,如果此值为0,那么就在桌面创建此窗口
     * @param x           左上角X坐标(相对于hwnd客户区坐标)
     * @param y           左上角Y坐标(相对于hwnd客户区坐标)
     * @param pic_name    位图名字
     * @param trans_color 透明色(RRGGBB)
     * @param sim         透明色的相似值 0.1-1.0
     *
     * @return Long 创建成功的窗口句柄
     *
     * @throws COMException
     * @example foobar = dm.CreateFoobarCustom(hwnd,10,10,"菜单.bmp","FF00FF",1.0)
     */
    public Long CreateFoobarCustom(Long hwnd, Integer x, Integer y, String pic_name, String trans_color, Double sim) throws COMException {
        return Long.parseLong(dm.invokeN("CreateFoobarCustom", new Object[]{hwnd, x, y, pic_name, trans_color, sim}).toString());
    }


    /**
     * 创建一个椭圆窗口
     *
     * @param hwnd 指定的窗口句柄,如果此值为0,那么就在桌面创建此窗口
     * @param x    左上角X坐标(相对于hwnd客户区坐标)
     * @param y    左上角Y坐标(相对于hwnd客户区坐标)
     * @param w    矩形区域的宽度
     * @param h    矩形区域的高度
     *
     * @return Long 创建成功的窗口句柄
     *
     * @throws COMException
     * @example foobar = dm.CreateFoobarEllipse(hwnd,10,10,200,200)
     */
    public Long CreateFoobarEllipse(Long hwnd, Integer x, Integer y, Integer w, Integer h) throws COMException {
        return Long.parseLong(dm.invokeN("CreateFoobarEllipse", new Object[]{hwnd, x, y, w, h}).toString());
    }


    /**
     * 创建一个矩形窗口
     *
     * @param hwnd 指定的窗口句柄,如果此值为0,那么就在桌面创建此窗口
     * @param x    左上角X坐标(相对于hwnd客户区坐标)
     * @param y    左上角Y坐标(相对于hwnd客户区坐标)
     * @param w    矩形区域的宽度
     * @param h    矩形区域的高度
     *
     * @return Long 创建成功的窗口句柄
     *
     * @throws COMException
     * @example foobar = dm.CreateFoobarRect(hwnd,10,10,200,200)
     */
    public Long CreateFoobarRect(Long hwnd, Integer x, Integer y, Integer w, Integer h) throws COMException {
        return Long.parseLong(dm.invokeN("CreateFoobarRect", new Object[]{hwnd, x, y, w, h}).toString());
    }


    /**
     * 创建一个圆角矩形窗口
     *
     * @param hwnd 指定的窗口句柄,如果此值为0,那么就在桌面创建此窗口
     * @param x    左上角X坐标(相对于hwnd客户区坐标)
     * @param y    左上角Y坐标(相对于hwnd客户区坐标)
     * @param w    矩形区域的宽度
     * @param h    矩形区域的高度
     * @param rw   圆角的宽度
     * @param rh   圆角的高度
     *
     * @return Long 创建成功的窗口句柄
     *
     * @throws COMException
     * @example foobar = dm.CreateFoobarRoundRect(hwnd,10,10,200,200,30,30)
     */
    public Long CreateFoobarRoundRect(Long hwnd, Integer x, Integer y, Integer w, Integer h, Integer rw, Integer rh) throws COMException {
        return Long.parseLong(dm.invokeN("CreateFoobarRoundRect", new Object[]{hwnd, x, y, w, h, rw, rh}).toString());
    }


    /**
     * 清除指定的Foobar滚动文本区
     *
     * @param hwnd 指定的Foobar窗口句柄,此句柄必须是通过CreateFoobarxxx创建而来
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarClearText(foobar)
     */
    public Integer FoobarClearText(Long hwnd) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarClearText", new Object[]{hwnd}).toString());
    }


    /**
     * 关闭一个Foobar,注意,必须调用此函数来关闭窗口,用SetWindowState也可以关闭,但会造成内存泄漏.
     *
     * @param hwnd 指定的Foobar窗口句柄
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarClose(foobar)
     */
    public Integer FoobarClose(Long hwnd) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarClose", new Object[]{hwnd}).toString());
    }


    /**
     * 在指定的Foobar窗口内部画线条.(vip)
     *
     * @param hwnd  指定的Foobar窗口,注意,此句柄必须是通过CreateFoobarxxxx系列函数创建出来的
     * @param x1    左上角X坐标(相对于hwnd客户区坐标)
     * @param y1    左上角Y坐标(相对于hwnd客户区坐标)
     * @param x2    右下角X坐标(相对于hwnd客户区坐标)
     * @param y2    右下角Y坐标(相对于hwnd客户区坐标)
     * @param color 填充的颜色值
     * @param style 画笔类型.
     *              0为实线.
     *              1为虚线
     * @param width 线条宽度.
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarDrawLine(foobar,0,0,200,200,"FF0000",1,1)
     */
    public Integer FoobarDrawLine(Long hwnd, Integer x1, Integer y1, Integer x2, Integer y2, String color, Integer style, Integer width) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarDrawLine", new Object[]{hwnd, x1, y1, x2, y2, color, style, width}).toString());
    }


    /**
     * 在指定的Foobar窗口绘制图像 此图片不能是加密的图片
     *
     * @param hwnd        指定的Foobar窗口,注意,此句柄必须是通过CreateFoobarxxxx系列函数创建出来的
     * @param x           左上角X坐标(相对于hwnd客户区坐标)
     * @param y           左上角Y坐标(相对于hwnd客户区坐标)
     * @param pic_name    图像文件名
     * @param trans_color 图像透明色
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarDrawPic(foobar,0,0,"menu.bmp","FF0000")
     */
    public Integer FoobarDrawPic(Long hwnd, Integer x, Integer y, String pic_name, String trans_color) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarDrawPic", new Object[]{hwnd, x, y, pic_name, trans_color}).toString());
    }


    /**
     * 在指定的Foobar窗口绘制文字
     *
     * @param hwnd  指定的Foobar窗口,注意,此句柄必须是通过CreateFoobarxxxx系列函数创建出来的
     * @param x     左上角X坐标(相对于hwnd客户区坐标)
     * @param y     左上角Y坐标(相对于hwnd客户区坐标)
     * @param w     矩形区域的宽度
     * @param h     矩形区域的高度
     * @param text  字符串
     * @param color 文字颜色值
     * @param align 1 : 左对齐
     *              2 : 中间对齐
     *              4 : 右对齐
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarDrawText(foobar,0,0,200,30,"测试","FF0000",1)
     */
    public Integer FoobarDrawText(Long hwnd, Integer x, Integer y, Integer w, Integer h, String text, String color, Integer align) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarDrawText", new Object[]{hwnd, x, y, w, h, text, color, align}).toString());
    }


    /**
     * 在指定的Foobar窗口内部填充矩形
     *
     * @param hwnd  指定的Foobar窗口,注意,此句柄必须是通过CreateFoobarxxxx系列函数创建出来的
     * @param x1    左上角X坐标(相对于hwnd客户区坐标)
     * @param y1    左上角Y坐标(相对于hwnd客户区坐标)
     * @param x2    右下角X坐标(相对于hwnd客户区坐标)
     * @param y2    右下角Y坐标(相对于hwnd客户区坐标)
     * @param color 填充的颜色值
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarFillRect(foobar,0,0,200,200,"FF0000")
     */
    public Integer FoobarFillRect(Long hwnd, Integer x1, Integer y1, Integer x2, Integer y2, String color) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarFillRect", new Object[]{hwnd, x1, y1, x2, y2, color}).toString());
    }


    /**
     * 锁定指定的Foobar窗口,不能通过鼠标来移动
     *
     * @param hwnd 指定的Foobar窗口句柄,此句柄必须是通过CreateFoobarxxx创建而来
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarLock(foobar)
     */
    public Integer FoobarLock(Long hwnd) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarLock", new Object[]{hwnd}).toString());
    }


    /**
     * 向指定的Foobar窗口区域内输出滚动文字
     *
     * @param hwnd  指定的Foobar窗口句柄,此句柄必须是通过CreateFoobarxxx创建而来
     * @param text  文本内容
     * @param color 文本颜色
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarPrintText(foobar,"大漠测试","ff0000")
     */
    public Integer FoobarPrintText(Long hwnd, String text, String color) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarPrintText", new Object[]{hwnd, text, color}).toString());
    }


    /**
     * 设置指定Foobar窗口的字体
     *
     * @param hwnd      指定的Foobar窗口句柄,此句柄必须是通过CreateFoobarxxx创建而来
     * @param font_name 系统字体名,注意,必须保证系统中有此字体
     * @param size      字体大小
     * @param flag      0 : 正常字体
     *                  1 : 粗体
     *                  2 : 斜体
     *                  4 : 下划线
     *                  文字可以是以上的组合 比如粗斜体就是1+2,斜体带下划线就是:2+4等.
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarSetFont(foobar,"宋体",25,2+4)
     */
    public Integer FoobarSetFont(Long hwnd, String font_name, Integer size, Integer flag) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarSetFont", new Object[]{hwnd, font_name, size, flag}).toString());
    }


    /**
     * 设置保存指定的Foobar滚动文本区信息到文件.
     *
     * @param hwnd   指定的Foobar窗口句柄,此句柄必须是通过CreateFoobarxxx创建而来
     * @param file   保存的文件名
     * @param enable 0 : 关闭向文件输出 (默认是0)
     *               1 : 开启向文件输出
     * @param header 输出的附加头信息. (比如行数 日期 时间信息) 格式是如下格式串的顺序组合.如果为空串，表示无附加头.
     *               "%L0nd%" 表示附加头信息带有行号，并且是按照十进制输出. n表示按多少个十进制数字补0对齐. 比如"%L04d%",输出的行号为0001  0002 0003等. "%L03d",输出的行号为001 002 003..等.
     *               "%L0nx%"表示附加头信息带有行号，并且是按照16进制小写输出. n表示按多少个16进制数字补0对齐. 比如"%L04x%",输出的行号为0009  000a 000b等. "%L03x",输出的行号为009 00a 00b..等.
     *               "%L0nX%"表示附加头信息带有行号，并且是按照16进制大写输出. n表示按多少个16进制数字补0对齐. 比如"%L04X%",输出的行号为0009  000A 000B等. "%L03X",输出的行号为009 00A 00B..等.
     *               "%yyyy%"表示年. 比如2012
     *               "%MM%"表示月. 比如12
     *               "%dd%"表示日. 比如28
     *               "%hh%"表示小时. 比如13
     *               "%mm%"表示分钟. 比如59
     *               "%ss%"表示秒. 比如48.
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarSetSave(foobar,"output.txt",1,"{%yyyy%-%MM%-%dd%} ")
     */
    public Integer FoobarSetSave(Long hwnd, String file, Integer enable, String header) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarSetSave", new Object[]{hwnd, file, enable, header}).toString());
    }


    /**
     * 设置滚动文本区的文字行间距,默认是3
     *
     * @param hwnd     指定的Foobar窗口句柄,此句柄必须是通过CreateFoobarxxx创建而来
     * @param line_gap 文本行间距
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarTextLineGap(foobar,5)
     */
    public Integer FoobarTextLineGap(Long hwnd, Integer line_gap) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarTextLineGap", new Object[]{hwnd, line_gap}).toString());
    }


    /**
     * 设置滚动文本区的文字输出方向,默认是0
     *
     * @param hwnd 指定的Foobar窗口句柄,此句柄必须是通过CreateFoobarxxx创建而来
     * @param dir  0 表示向下输出
     *             1 表示向上输出
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarTextPrintDir(foobar,1)
     */
    public Integer FoobarTextPrintDir(Long hwnd, Integer dir) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarTextPrintDir", new Object[]{hwnd, dir}).toString());
    }


    /**
     * 设置指定Foobar窗口的滚动文本框范围,默认的文本框范围是窗口区域
     *
     * @param hwnd 指定的Foobar窗口句柄,此句柄必须是通过CreateFoobarxxx创建而来
     * @param x    x坐标
     * @param y    y坐标
     * @param w    宽度
     * @param h    高度
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarTextRect(foobar,10,10,100,200)
     */
    public Integer FoobarTextRect(Long hwnd, Integer x, Integer y, Integer w, Integer h) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarTextRect", new Object[]{hwnd, x, y, w, h}).toString());
    }


    /**
     * 解锁指定的Foobar窗口,可以通过鼠标来移动
     *
     * @param hwnd 指定的Foobar窗口句柄,此句柄必须是通过CreateFoobarxxx创建而来
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarUnlock(foobar)
     */
    public Integer FoobarUnlock(Long hwnd) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarUnlock", new Object[]{hwnd}).toString());
    }


    /**
     * 刷新指定的Foobar窗口
     *
     * @param hwnd 指定的Foobar窗口,注意,此句柄必须是通过CreateFoobarxxxx系列函数创建出来的
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarUpdate(foobar)
     * @note 注意： 所有绘制完成以后,必须通过调用此函数来刷新窗口,否则窗口内容不会改变.
     */
    public Integer FoobarUpdate(Long hwnd) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarUpdate", new Object[]{hwnd}).toString());
    }


}
