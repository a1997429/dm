package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-09 10:39:15
 * @classname: DmFoobarPlus
 * @description: Foobar
 * @version: 7.2107
 */
public class DmFoobarPlus extends DmFoobar {

    private final static Logger logger = LoggerFactory.getLogger(DmFoobarPlus.class);

    private DmSoft dm;

    public DmFoobarPlus(DmSoft dm) {
        super(dm);
        this.dm = dm;
    }


    /**
     * 设置指定Foobar窗口的是否透明
     *
     * @param hwnd     指定的Foobar窗口句柄,此句柄必须是通过CreateFoobarxxx创建而来
     * @param is_trans 是否透明. 0为不透明(此时,color和sim无效)，1为透明.
     * @param color    透明色(RRGGBB)
     * @param sim      透明色的相似值 0.1-1.0
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * foobar=dm.CreateFoobarRoundRect(hwnd,1,1,300,300,100,100)
     * dm_ret = dm.FoobarSetFont(foobar,"宋体",50,0)
     * dm.FoobarSetTrans(foobar,1,"000000",1.0)
     */
    public Integer FoobarSetTrans(Long hwnd, Integer is_trans, String color, Double sim) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarSetTrans", new Object[]{hwnd, is_trans, color, sim}).toString());
    }


    /**
     * 在指定的Foobar窗口绘制gif动画.
     *
     * @param hwnd         指定的Foobar窗口,注意,此句柄必须是通过CreateFoobarxxxx系列函数创建出来的
     * @param x            左上角X坐标(相对于hwnd客户区坐标)
     * @param y            左上角Y坐标(相对于hwnd客户区坐标)
     * @param pic_name     图像文件名
     * @param repeat_limit 表示重复GIF动画的次数，如果是0表示一直循环显示.大于0，则表示循环指定的次数以后就停止显示.
     * @param delay        表示每帧GIF动画之间的时间间隔.如果是0，表示使用GIF内置的时间，如果大于0，表示使用自定义的时间间隔.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarStartGif(foobar,0,0,"警报.gif",0,0)
     * @note 注 : 当foobar关闭时，所有播放的gif也会自动关闭，内部资源也会自动释放，没必要一定去调用FoobarStopGif函数.
     * 另外，所有gif动画是在顶层显示，在默认绘图层和Print层之上. gif之间的显示顺序按照调用FoobarStartGif的顺序决定.
     */
    public Integer FoobarStartGif(Long hwnd, Integer x, Integer y, String pic_name, Integer repeat_limit, Integer delay) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarStartGif", new Object[]{hwnd, x, y, pic_name, repeat_limit, delay}).toString());
    }


    /**
     * 停止在指定foobar里显示的gif动画.
     * @param hwnd 指定的Foobar窗口,注意,此句柄必须是通过CreateFoobarxxxx系列函数创建出来的
     * @param x 左上角X坐标(相对于hwnd客户区坐标)
     * @param y 左上角Y坐标(相对于hwnd客户区坐标)
     * @param pic_name 图像文件名
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm_ret = dm.FoobarStopGif(foobar,0,0,"警报.gif")
     */
    public Integer FoobarStopGif(Long hwnd, Integer x, Integer y, String pic_name) throws COMException {
        return Integer.parseInt(dm.invokeN("FoobarStopGif", new Object[]{hwnd, x, y, pic_name}).toString());
    }




}
