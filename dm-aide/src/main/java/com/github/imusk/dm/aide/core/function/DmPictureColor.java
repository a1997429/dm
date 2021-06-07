package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.jawin.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-07 19:45
 * @classname: DmPicture
 * @description: 图色
 */
public class DmPictureColor {

    private final static Logger logger = LoggerFactory.getLogger(DmPictureColor.class);

    private DmSoft dm;

    public DmPictureColor(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 对指定的数据地址和长度，组合成新的参数. FindPicMem FindPicMemE 以及FindPicMemEx专用
     *
     * @param pic_info 老的地址描述串
     * @param addr     数据地址
     * @param size     数据长度
     *
     * @return String 新的地址描述串
     *
     * @throws COMException
     * @example 示例
     * pic_info = ""
     * pic_info = dm.AppendPicAddr(pic_info,12034,643)
     * pic_info = dm.AppendPicAddr(pic_info,328435,8935)
     * pic_info = dm.AppendPicAddr(pic_info,809234,789)
     */
    public String AppendPicAddr(String pic_info, Integer addr, Integer size) throws COMException {
        return dm.invokeN("AppendPicAddr", new Object[]{pic_info, addr, size}).toString();
    }


    /**
     * 把BGR(按键格式)的颜色格式转换为RGB
     *
     * @param bgr_color bgr格式的颜色字符串
     *
     * @return String RGB格式的字符串
     *
     * @throws COMException
     * @example rgb_color = dm.BGR2RGB(bgr_color)
     */
    public String BGR2RGB(String bgr_color) throws COMException {
        return dm.invokeN("BGR2RGB", new Object[]{bgr_color}).toString();
    }


    /**
     * 抓取指定区域(x1, y1, x2, y2)的图像,保存为file(24位位图)
     *
     * @param x1   区域的左上X坐标
     * @param y1   区域的左上Y坐标
     * @param x2   区域的右下X坐标
     * @param y2   区域的右下Y坐标
     * @param file 保存的文件名,保存的地方一般为SetPath中设置的目录，当然这里也可以指定全路径名.
     *
     * @return Integer 0：失败，1：成功
     *
     * @throws COMException
     * @example dm_ret = dm.Capture(0,0,2000,2000,"screen.bmp")
     */
    public Integer Capture(Integer x1, Integer y1, Integer x2, Integer y2, String file) throws COMException {
        return Integer.parseInt(dm.invokeN("Capture", new Object[]{x1, y1, x2, y2, file}).toString());
    }


    /**
     * 抓取指定区域(x1, y1, x2, y2)的动画，保存为gif格式
     *
     * @param x1    区域的左上X坐标
     * @param y1    区域的左上Y坐标
     * @param x2    区域的右下X坐标
     * @param y2    区域的右下Y坐标
     * @param file  保存的文件名,保存的地方一般为SetPath中设置的目录，当然这里也可以指定全路径名.
     * @param delay 动画间隔，单位毫秒。如果为0，表示只截取静态图片
     * @param time  总共截取多久的动画，单位毫秒。
     *
     * @return Integer 0：失败，1：成功
     *
     * @throws COMException
     * @example 示例
     * 截取动画
     * dm_ret = dm.CaptureGif(0,0,2000,2000,"screen.gif",100,3000)
     * 截取静态
     * dm_ret = dm.CaptureGif(0,0,2000,2000,"screen.gif",0,0)
     */
    public Integer CaptureGif(Integer x1, Integer y1, Integer x2, Integer y2, String file, Integer delay, Integer time) throws COMException {
        return Integer.parseInt(dm.invokeN("CaptureGif", new Object[]{x1, y1, x2, y2, file, delay, time}).toString());
    }


    /**
     * 抓取指定区域(x1, y1, x2, y2)的动画，保存为gif格式
     *
     * @param x1      区域的左上X坐标
     * @param y1      区域的左上Y坐标
     * @param x2      区域的右下X坐标
     * @param y2      区域的右下Y坐标
     * @param file    保存的文件名,保存的地方一般为SetPath中设置的目录，当然这里也可以指定全路径名.
     * @param quality jpg压缩比率(1-100) 越大图片质量越好
     *
     * @return Integer 0：失败，1：成功
     *
     * @throws COMException
     * @example dm_ret = dm.CaptureJpg(0,0,2000,2000,"screen.jpg",50)
     */
    public Integer CaptureJpg(Integer x1, Integer y1, Integer x2, Integer y2, String file, Integer quality) throws COMException {
        return Integer.parseInt(dm.invokeN("CaptureJpg", new Object[]{x1, y1, x2, y2, file, quality}).toString());
    }


    /**
     * 同Capture函数，只是保存的格式为PNG
     *
     * @param x1   区域的左上X坐标
     * @param y1   区域的左上Y坐标
     * @param x2   区域的右下X坐标
     * @param y2   区域的右下Y坐标
     * @param file 保存的文件名,保存的地方一般为SetPath中设置的目录，当然这里也可以指定全路径名.
     *
     * @return Integer 0：失败，1：成功
     *
     * @throws COMException
     * @example dm_ret = dm.CapturePng(0,0,2000,2000,"screen.png")
     */
    public Integer CapturePng(Integer x1, Integer y1, Integer x2, Integer y2, String file) throws COMException {
        return Integer.parseInt(dm.invokeN("CapturePng", new Object[]{x1, y1, x2, y2, file}).toString());
    }


    /**
     * 抓取上次操作的图色区域，保存为file(24位位图)
     *
     * @param file 保存的文件名,保存的地方一般为SetPath中设置的目录，当然这里也可以指定全路径名.
     *
     * @return Integer 0：失败，1：成功
     *
     * @throws COMException
     * @example dm_ret = dm.CapturePre("screen.bmp")
     * @note 注意，要开启此函数，必须先调用EnableDisplayDebug
     */
    public Integer CapturePre(String file) throws COMException {
        return Integer.parseInt(dm.invokeN("CapturePre", new Object[]{file}).toString());
    }


    /**
     * 比较指定坐标点(x,y)的颜色
     *
     * @param x     X坐标
     * @param y     Y坐标
     * @param color 颜色字符串,可以支持偏色,多色,例如 "ffffff-202020|000000-000000" 这个表示白色偏色为202020,和黑色偏色为000000.颜色最多支持10种颜色组合. 注意，这里只支持RGB颜色.
     * @param sim   相似度(0.1-1.0)
     *
     * @return Integer 0：颜色匹配 1：颜色不匹配
     *
     * @throws COMException
     * @example dm_ret = dm.CmpColor(200,300,"000000-000000|ff00ff-101010",0.9)
     */
    public Integer CmpColor(Integer x, Integer y, String color, Double sim) throws COMException {
        return Integer.parseInt(dm.invokeN("CmpColor", new Object[]{x, y, color, sim}).toString());
    }


    /**
     * 开启图色调试模式，此模式会稍许降低图色和文字识别的速度.默认不开启.
     *
     * @param enable_debug 0 为关闭
     *                     1 为开启
     *
     * @return Integer 0：失败 1：成功
     *
     * @throws COMException
     * @example 示例
     * dm.EnableDisplayDebug(1)
     * dm_ret = dm.CapturePre("screen.bmp")
     */
    public Integer EnableDisplayDebug(Integer enable_debug) throws COMException {
        return Integer.parseInt(dm.invokeN("EnableDisplayDebug", new Object[]{enable_debug}).toString());
    }


    /**
     * 允许调用GetColor GetColorBGR GetColorHSV 以及 CmpColor时，以截图的方式来获取颜色。
     *
     * @param enable 0 关闭
     *               1 打开
     *
     * @return Integer 0：失败 1：成功
     *
     * @throws COMException
     * @example 示例
     * dm.EnableGetColorByCapture(1)
     * dm.GetColor(300,300)
     */
    public Integer EnableGetColorByCapture(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("EnableGetColorByCapture", new Object[]{enable}).toString());
    }


    /**
     * 查找指定区域内的颜色,颜色格式"RRGGBB-DRDGDB",注意,和按键的颜色格式相反
     *
     * @param x1    区域的左上X坐标
     * @param y1    区域的左上Y坐标
     * @param x2    区域的右下X坐标
     * @param y2    区域的右下Y坐标
     * @param color 颜色 格式为"RRGGBB-DRDGDB",比如"123456-000000|aabbcc-202020".注意，这里只支持RGB颜色.
     * @param sim   相似度,取值范围0.1-1.0
     * @param dir   查找方向
     *              0: 从左到右,从上到下
     *              1: 从左到右,从下到上
     *              2: 从右到左,从上到下
     *              3: 从右到左,从下到上
     *              4：从中心往外查找
     *              5: 从上到下,从左到右
     *              6: 从上到下,从右到左
     *              7: 从下到上,从左到右
     *              8: 从下到上,从右到左
     * @param intX  返回X坐标
     * @param intY  返回Y坐标
     *
     * @return Integer 0：没找到 1：找到
     *
     * @throws COMException
     * @example dm_ret = dm.FindColor(0,0,2000,2000,"123456-000000|aabbcc-030303|ddeeff-202020",1.0,0,intX,intY)
     */
    public Integer FindColor(Integer x1, Integer y1, Integer x2, Integer y2, String color, Double sim, Integer dir, Variant.ByrefHolder intX, Variant.ByrefHolder intY) throws COMException {
        return Integer.parseInt(dm.invokeN("FindColor", new Object[]{x1, y1, x2, y2, color, sim, dir, intX, intY}).toString());
    }


    /**
     * 查找指定区域内的颜色,颜色格式"RRGGBB-DRDGDB",注意,和按键的颜色格式相反（易语言用不了FindColor可以用此接口来代替）
     *
     * @param x1    区域的左上X坐标
     * @param y1    区域的左上Y坐标
     * @param x2    区域的右下X坐标
     * @param y2    区域的右下Y坐标
     * @param color 颜色 格式为"RRGGBB-DRDGDB",比如"123456-000000|aabbcc-202020".注意，这里只支持RGB颜色.
     * @param sim   相似度,取值范围0.1-1.0
     * @param dir   查找方向
     *              0: 从左到右,从上到下
     *              1: 从左到右,从下到上
     *              2: 从右到左,从上到下
     *              3: 从右到左,从下到上
     *              4：从中心往外查找
     *              5: 从上到下,从左到右
     *              6: 从上到下,从右到左
     *              7: 从下到上,从左到右
     *              8: 从下到上,从右到左
     *
     * @return String 返回X和Y坐标 形式如"x|y", 比如"100|200"
     *
     * @throws COMException
     * @example pos = dm.FindColorE(0,0,2000,2000,"123456-000000|aabbcc-030303|ddeeff-202020",1.0,0)
     */
    public String FindColorE(Integer x1, Integer y1, Integer x2, Integer y2, String color, Double sim, Integer dir) throws COMException {
        return dm.invokeN("FindColorE", new Object[]{x1, y1, x2, y2, color, sim, dir}).toString();
    }


    /**
     * 查找指定区域内的所有颜色,颜色格式"RRGGBB-DRDGDB",注意,和按键的颜色格式相反
     *
     * @param x1    区域的左上X坐标
     * @param y1    区域的左上Y坐标
     * @param x2    区域的右下X坐标
     * @param y2    区域的右下Y坐标
     * @param color 颜色 格式为"RRGGBB-DRDGDB",比如"123456-000000|aabbcc-202020".注意，这里只支持RGB颜色.
     * @param sim   相似度,取值范围0.1-1.0
     * @param dir   查找方向
     *              0: 从左到右,从上到下
     *              1: 从左到右,从下到上
     *              2: 从右到左,从上到下
     *              3: 从右到左,从下到上
     *              4：从中心往外查找
     *              5: 从上到下,从左到右
     *              6: 从上到下,从右到左
     *              7: 从下到上,从左到右
     *              8: 从下到上,从右到左
     *
     * @return String 返回所有颜色信息的坐标值,然后通过GetResultCount等接口来解析 (由于内存限制,返回的颜色数量最多为1800个左右)
     *
     * @throws COMException
     * @example s = dm.FindColorEx(0,0,2000,2000,"123456-000000|abcdef-202020",1.0,0)
     */
    public String FindColorEx(Integer x1, Integer y1, Integer x2, Integer y2, String color, Double sim, Integer dir) throws COMException {
        return dm.invokeN("FindColorEx", new Object[]{x1, y1, x2, y2, color, sim, dir}).toString();
    }


    /**
     * 查找指定区域内的所有颜色.(vip)
     *
     * @param x1    区域的左上X坐标
     * @param y1    区域的左上Y坐标
     * @param x2    区域的右下X坐标
     * @param y2    区域的右下Y坐标
     * @param color 颜色 格式为"RRGGBB-DRDGDB",比如"123456-000000|aabbcc-202020".注意，这里只支持RGB颜色.
     * @param sim   相似度,取值范围0.1-1.0
     *
     * @return Integer 0：没找到或者部分颜色没找到 1：所有颜色都找到
     *
     * @throws COMException
     * @example dm_ret = dm.FindMulColor(0,0,2000,2000,"123456-000000|aabbcc-030303|ddeeff-202020",1.0)
     */
    public Integer FindMulColor(Integer x1, Integer y1, Integer x2, Integer y2, String color, Double sim) throws COMException {
        return Integer.parseInt(dm.invokeN("FindMulColor", new Object[]{x1, y1, x2, y2, color, sim}).toString());
    }


    /**
     * 根据指定的多点查找颜色坐标
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param first_color  颜色 格式为"RRGGBB-DRDGDB",比如"123456-000000"，这里的含义和按键自带Color插件的意义相同，只不过我的可以支持偏色，所有的偏移色坐标都相对于此颜色.注意，这里只支持RGB颜色.
     * @param offset_color 偏移颜色 可以支持任意多个点 格式和按键自带的Color插件意义相同，格式为"x1|y1|RRGGBB-DRDGDB,……xn|yn|RRGGBB-DRDGDB"，比如"1|3|aabbcc,-5|-3|123456-000000"等任意组合都可以，支持偏色，还可以支持反色模式，比如"1|3|-aabbcc,-5|-3|-123456-000000","-"表示除了指定颜色之外的颜色.
     * @param sim          相似度,取值范围0.1-1.0
     * @param dir          查找方向
     *                     0: 从左到右,从上到下
     *                     1: 从左到右,从下到上
     *                     2: 从右到左,从上到下
     *                     3: 从右到左, 从下到上
     * @param intX         返回X坐标(坐标为first_color所在坐标)
     * @param intY         返回Y坐标(坐标为first_color所在坐标)
     *
     * @return Integer 0：没找到 1：找到
     *
     * @throws COMException
     * @example 示例
     * dm_ret = dm.FindMultiColor(0,0,2000,2000,"cc805b-020202","9|2|-00ff00,15|2|2dff1c-010101,6|11|a0d962,11|14|-ffffff",1.0,1,intX,intY)
     * dm.MoveTo(intX,intY)
     */
    public Integer FindMultiColor(Integer x1, Integer y1, Integer x2, Integer y2, String first_color, String offset_color, Double sim, Integer dir, Variant.ByrefHolder intX, Variant.ByrefHolder intY) throws COMException {
        return Integer.parseInt(dm.invokeN("FindMultiColor", new Object[]{x1, y1, x2, y2, first_color, offset_color, sim, dir, intX, intY}).toString());
    }


    /**
     * 根据指定的多点查找颜色坐标（易语言用不了FindMultiColor可以用此接口来代替）
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param first_color  颜色 格式为"RRGGBB-DRDGDB",比如"123456-000000"，这里的含义和按键自带Color插件的意义相同，只不过我的可以支持偏色，所有的偏移色坐标都相对于此颜色.注意，这里只支持RGB颜色.
     * @param offset_color 偏移颜色 可以支持任意多个点 格式和按键自带的Color插件意义相同，格式为"x1|y1|RRGGBB-DRDGDB,……xn|yn|RRGGBB-DRDGDB"，比如"1|3|aabbcc,-5|-3|123456-000000"等任意组合都可以，支持偏色，还可以支持反色模式，比如"1|3|-aabbcc,-5|-3|-123456-000000","-"表示除了指定颜色之外的颜色.
     * @param sim          相似度,取值范围0.1-1.0
     * @param dir          查找方向
     *                     0: 从左到右,从上到下
     *                     1: 从左到右,从下到上
     *                     2: 从右到左,从上到下
     *                     3: 从右到左, 从下到上
     *
     * @return String 返回X和Y坐标 形式如"x|y", 比如"100|200"
     *
     * @throws COMException
     * @example pos = dm.FindMultiColorE(0,0,2000,2000,"cc805b-020202","9|2|-00ff00,15|2|-2dff1c-010101,6|11|a0d962,11|14|-ffffff",1.0,1)
     */
    public String FindMultiColorE(Integer x1, Integer y1, Integer x2, Integer y2, String first_color, String offset_color, Double sim, Integer dir) throws COMException {
        return dm.invokeN("FindMultiColorE", new Object[]{x1, y1, x2, y2, first_color, offset_color, sim, dir}).toString();
    }


    /**
     * 根据指定的多点查找所有颜色坐标
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param first_color  颜色 格式为"RRGGBB-DRDGDB",比如"123456-000000"，这里的含义和按键自带Color插件的意义相同，只不过我的可以支持偏色，所有的偏移色坐标都相对于此颜色.注意，这里只支持RGB颜色.
     * @param offset_color 偏移颜色 可以支持任意多个点 格式和按键自带的Color插件意义相同，格式为"x1|y1|RRGGBB-DRDGDB,……xn|yn|RRGGBB-DRDGDB"，比如"1|3|aabbcc,-5|-3|123456-000000"等任意组合都可以，支持偏色，还可以支持反色模式，比如"1|3|-aabbcc,-5|-3|-123456-000000","-"表示除了指定颜色之外的颜色.
     * @param sim          相似度,取值范围0.1-1.0
     * @param dir          查找方向
     *                     0: 从左到右,从上到下
     *                     1: 从左到右,从下到上
     *                     2: 从右到左,从上到下
     *                     3: 从右到左, 从下到上
     *
     * @return String 返回所有颜色信息的坐标值,然后通过GetResultCount等接口来解析(由于内存限制,返回的坐标数量最多为1800个左右)，坐标是first_color所在的坐标
     *
     * @throws COMException
     * @example 示例
     * dm_ret = dm.FindMultiColorEx(0,0,2000,2000,"cc805b-020202","9|2|-00ff00,15|2|2dff1c-010101,6|11|a0d962,11|14|-ffffff",1.0,1)
     * count = dm.GetResultCount(dm_ret)
     */
    public String FindMultiColorEx(Integer x1, Integer y1, Integer x2, Integer y2, String first_color, String offset_color, Double sim, Integer dir) throws COMException {
        return dm.invokeN("FindMultiColorEx", new Object[]{x1, y1, x2, y2, first_color, offset_color, sim, dir}).toString();
    }


    /**
     * 查找指定区域内的图片,位图必须是24位色格式,支持透明色,当图像上下左右4个顶点的颜色一样时,则这个颜色将作为透明色处理.
     * 这个函数可以查找多个图片,只返回第一个找到的X Y坐标.
     *
     * @param x1          区域的左上X坐标
     * @param y1          区域的左上Y坐标
     * @param x2          区域的右下X坐标
     * @param y2          区域的右下Y坐标
     * @param pic_name    图片名,可以是多个图片,比如"test.bmp|test2.bmp|test3.bmp"
     * @param delta_color 颜色色偏比如"203040" 表示RGB的色偏分别是20 30 40 (这里是16进制表示)
     * @param sim         相似度,取值范围0.1-1.0
     * @param dir         查找方向
     *                    0: 从左到右,从上到下
     *                    1: 从左到右,从下到上
     *                    2: 从右到左,从上到下
     *                    3: 从右到左, 从下到上
     * @param intX        返回图片左上角的X坐标
     * @param intY        返回图片左上角的Y坐标
     *
     * @return Integer 返回找到的图片的序号,从0开始索引.如果没找到返回-1
     *
     * @throws COMException
     * @example dm_ret = dm.FindPic(0,0,2000,2000,"1.bmp|2.bmp|3.bmp","000000",0.9,0,intX,intY)
     */
    public Integer FindPic(Integer x1, Integer y1, Integer x2, Integer y2, String pic_name, String delta_color, Double sim, Integer dir, Variant.ByrefHolder intX, Variant.ByrefHolder intY) throws COMException {
        return Integer.parseInt(dm.invokeN("FindPic", new Object[]{x1, y1, x2, y2, pic_name, delta_color, sim, dir, intX, intY}).toString());
    }


    /**
     * 查找指定区域内的图片,位图必须是24位色格式,支持透明色,当图像上下左右4个顶点的颜色一样时,则这个颜色将作为透明色处理.
     * 这个函数可以查找多个图片,只返回第一个找到的X Y坐标.（易语言用不了FindPic可以用此接口来代替）
     *
     * @param x1          区域的左上X坐标
     * @param y1          区域的左上Y坐标
     * @param x2          区域的右下X坐标
     * @param y2          区域的右下Y坐标
     * @param pic_name    图片名,可以是多个图片,比如"test.bmp|test2.bmp|test3.bmp"
     * @param delta_color 颜色色偏比如"203040" 表示RGB的色偏分别是20 30 40 (这里是16进制表示)
     * @param sim         相似度,取值范围0.1-1.0
     * @param dir         查找方向
     *                    0: 从左到右,从上到下
     *                    1: 从左到右,从下到上
     *                    2: 从右到左,从上到下
     *                    3: 从右到左, 从下到上
     *
     * @return String 返回找到的图片序号(从0开始索引)以及X和Y坐标 形式如"index|x|y", 比如"3|100|200"
     *
     * @throws COMException
     * @example pos = dm.FindPicE(0,0,2000,2000,"1.bmp|2.bmp|3.bmp","000000",0.9,0)
     */
    public String FindPicE(Integer x1, Integer y1, Integer x2, Integer y2, String pic_name, String delta_color, Double sim, Integer dir) throws COMException {
        return dm.invokeN("FindPicE", new Object[]{x1, y1, x2, y2, pic_name, delta_color, sim, dir}).toString();
    }


    /**
     * 查找指定区域内的图片,位图必须是24位色格式,支持透明色,当图像上下左右4个顶点的颜色一样时,则这个颜色将作为透明色处理.
     * 这个函数可以查找多个图片,并且返回所有找到的图像的坐标.
     *
     * @param x1          区域的左上X坐标
     * @param y1          区域的左上Y坐标
     * @param x2          区域的右下X坐标
     * @param y2          区域的右下Y坐标
     * @param pic_name    图片名,可以是多个图片,比如"test.bmp|test2.bmp|test3.bmp"
     * @param delta_color 颜色色偏比如"203040" 表示RGB的色偏分别是20 30 40 (这里是16进制表示)
     * @param sim         相似度,取值范围0.1-1.0
     * @param dir         查找方向
     *                    0: 从左到右,从上到下
     *                    1: 从左到右,从下到上
     *                    2: 从右到左,从上到下
     *                    3: 从右到左, 从下到上
     *
     * @return String 返回的是所有找到的坐标格式如下:"id,x,y|id,x,y..|id,x,y" (图片左上角的坐标)
     * 比如"0,100,20|2,30,40" 表示找到了两个,第一个,对应的图片是图像序号为0的图片,坐标是(100,20),第二个是序号为2的图片,坐标(30,40)
     * (由于内存限制,返回的图片数量最多为1500个左右)
     *
     * @throws COMException
     * @example dm_ret = dm.FindPicEx(0,0,2000,2000,"test.bmp|test2.bmp|test3.bmp|test4.bmp|test5.bmp","020202",1.0,0)
     */
    public String FindPicEx(Integer x1, Integer y1, Integer x2, Integer y2, String pic_name, String delta_color, Double sim, Integer dir) throws COMException {
        return dm.invokeN("FindPicEx", new Object[]{x1, y1, x2, y2, pic_name, delta_color, sim, dir}).toString();
    }


    /**
     * 查找指定区域内的图片,位图必须是24位色格式,支持透明色,当图像上下左右4个顶点的颜色一样时,则这个颜色将作为透明色处理.
     * 这个函数可以查找多个图片,并且返回所有找到的图像的坐标. 此函数同FindPicEx.只是返回值不同.(vip)
     *
     * @param x1          区域的左上X坐标
     * @param y1          区域的左上Y坐标
     * @param x2          区域的右下X坐标
     * @param y2          区域的右下Y坐标
     * @param pic_name    图片名,可以是多个图片,比如"test.bmp|test2.bmp|test3.bmp"
     * @param delta_color 颜色色偏比如"203040" 表示RGB的色偏分别是20 30 40 (这里是16进制表示)
     * @param sim         相似度,取值范围0.1-1.0
     * @param dir         查找方向
     *                    0: 从左到右,从上到下
     *                    1: 从左到右,从下到上
     *                    2: 从右到左,从上到下
     *                    3: 从右到左, 从下到上
     *
     * @return String 返回的是所有找到的坐标格式如下:"file,x,y| file,x,y..| file,x,y" (图片左上角的坐标)
     * 比如"1.bmp,100,20|2.bmp,30,40" 表示找到了两个,第一个,对应的图片是1.bmp,坐标是(100,20),第二个是2.bmp,坐标(30,40)
     * (由于内存限制,返回的图片数量最多为1500个左右)
     *
     * @throws COMException
     * @example dm_ret = dm.FindPicExS(0,0,2000,2000,"test.bmp|test2.bmp|test3.bmp|test4.bmp|test5.bmp","020202",1.0,0)
     */
    public String FindPicExS(Integer x1, Integer y1, Integer x2, Integer y2, String pic_name, String delta_color, Double sim, Integer dir) throws COMException {
        return dm.invokeN("FindPicExS", new Object[]{x1, y1, x2, y2, pic_name, delta_color, sim, dir}).toString();
    }


    /**
     * 查找指定区域内的图片,位图必须是24位色格式,支持透明色,当图像上下左右4个顶点的颜色一样时,则这个颜色将作为透明色处理.
     * 这个函数可以查找多个图片,只返回第一个找到的X Y坐标. 这个函数要求图片是数据地址.(vip)
     *
     * @param x1          区域的左上X坐标
     * @param y1          区域的左上Y坐标
     * @param x2          区域的右下X坐标
     * @param y2          区域的右下Y坐标
     * @param pic_info    图片数据地址集合. 格式为"地址1,长度1|地址2,长度2.....|地址n,长度n". 可以用AppendPicAddr来组合.
     *                    地址表示24位位图资源在内存中的首地址，用十进制的数值表示
     *                    长度表示位图资源在内存中的长度，用十进制数值表示.
     * @param delta_color 颜色色偏比如"203040" 表示RGB的色偏分别是20 30 40 (这里是16进制表示)
     * @param sim         相似度,取值范围0.1-1.0
     * @param dir         查找方向
     *                    0: 从左到右,从上到下
     *                    1: 从左到右,从下到上
     *                    2: 从右到左,从上到下
     *                    3: 从右到左, 从下到上
     * @param intX        返回X坐标(坐标为first_color所在坐标)
     * @param intY        返回Y坐标(坐标为first_color所在坐标)
     *
     * @return Integer 返回找到的图片的序号,从0开始索引.如果没找到返回-1
     *
     * @throws COMException
     * @example 示例
     * pic_info = ""
     * pic_info = dm.AppendPicAddr(pic_info,12034,643)
     * pic_info = dm.AppendPicAddr(pic_info,328435,8935)
     * pic_info = dm.AppendPicAddr(pic_info,809234,789)
     * dm_ret = dm.FindPicMem(0,0,2000,2000, pic_info,"000000",0.9,0,intX,intY)
     */
    public Integer FindPicMem(Integer x1, Integer y1, Integer x2, Integer y2, String pic_info, String delta_color, Double sim, Integer dir, Variant.ByrefHolder intX, Variant.ByrefHolder intY) throws COMException {
        return Integer.parseInt(dm.invokeN("FindPicMem", new Object[]{x1, y1, x2, y2, pic_info, delta_color, sim, dir, intX, intY}).toString());
    }


    /**
     * 查找指定区域内的图片,位图必须是24位色格式,支持透明色,当图像上下左右4个顶点的颜色一样时,则这个颜色将作为透明色处理.
     * 这个函数可以查找多个图片,只返回第一个找到的X Y坐标. 这个函数要求图片是数据地址.(vip)
     * 易语言用不了FindPicMem可以用此接口来代替
     *
     * @param x1          区域的左上X坐标
     * @param y1          区域的左上Y坐标
     * @param x2          区域的右下X坐标
     * @param y2          区域的右下Y坐标
     * @param pic_info    图片数据地址集合. 格式为"地址1,长度1|地址2,长度2.....|地址n,长度n". 可以用AppendPicAddr来组合.
     *                    地址表示24位位图资源在内存中的首地址，用十进制的数值表示
     *                    长度表示位图资源在内存中的长度，用十进制数值表示.
     * @param delta_color 颜色色偏比如"203040" 表示RGB的色偏分别是20 30 40 (这里是16进制表示)
     * @param sim         相似度,取值范围0.1-1.0
     * @param dir         查找方向
     *                    0: 从左到右,从上到下
     *                    1: 从左到右,从下到上
     *                    2: 从右到左,从上到下
     *                    3: 从右到左, 从下到上
     *
     * @return String 返回找到的图片序号(从0开始索引)以及X和Y坐标 形式如"index|x|y", 比如"3|100|200"
     *
     * @throws COMException
     * @example 示例
     * pic_info = ""
     * pic_info = dm.AppendPicAddr(pic_info,12034,643)
     * pic_info = dm.AppendPicAddr(pic_info,328435,8935)
     * pic_info = dm.AppendPicAddr(pic_info,809234,789)
     * pos = dm.FindPicE(0,0,2000,2000, pic_info,"000000",0.9,0)
     */
    public String FindPicMemE(Integer x1, Integer y1, Integer x2, Integer y2, String pic_info, String delta_color, Double sim, Integer dir) throws COMException {
        return dm.invokeN("FindPicMemE", new Object[]{x1, y1, x2, y2, pic_info, delta_color, sim, dir}).toString();
    }


    /**
     * 查找指定区域内的图片,位图必须是24位色格式,支持透明色,当图像上下左右4个顶点的颜色一样时,则这个颜色将作为透明色处理.
     * 这个函数可以查找多个图片,只返回第一个找到的X Y坐标. 这个函数要求图片是数据地址.(vip)
     *
     * @param x1          区域的左上X坐标
     * @param y1          区域的左上Y坐标
     * @param x2          区域的右下X坐标
     * @param y2          区域的右下Y坐标
     * @param pic_info    图片数据地址集合. 格式为"地址1,长度1|地址2,长度2.....|地址n,长度n". 可以用AppendPicAddr来组合.
     *                    地址表示24位位图资源在内存中的首地址，用十进制的数值表示
     *                    长度表示位图资源在内存中的长度，用十进制数值表示.
     * @param delta_color 颜色色偏比如"203040" 表示RGB的色偏分别是20 30 40 (这里是16进制表示)
     * @param sim         相似度,取值范围0.1-1.0
     * @param dir         查找方向
     *                    0: 从左到右,从上到下
     *                    1: 从左到右,从下到上
     *                    2: 从右到左,从上到下
     *                    3: 从右到左, 从下到上
     *
     * @return String 返回的是所有找到的坐标格式如下:"id,x,y|id,x,y..|id,x,y" (图片左上角的坐标)
     * 比如"0,100,20|2,30,40" 表示找到了两个,第一个,对应的图片是图像序号为0的图片,坐标是(100,20),第二个是序号为2的图片,坐标(30,40)
     * (由于内存限制,返回的图片数量最多为1500个左右)
     *
     * @throws COMException
     * @example 示例
     * pic_info = ""
     * pic_info = dm.AppendPicAddr(pic_info,12034,643)
     * pic_info = dm.AppendPicAddr(pic_info,328435,8935)
     * pic_info = dm.AppendPicAddr(pic_info,809234,789)
     * dm_ret = dm.FindPicMemEx(0,0,2000,2000, pic_info,"020202",1.0,0)
     */
    public String FindPicMemEx(Integer x1, Integer y1, Integer x2, Integer y2, String pic_info, String delta_color, Double sim, Integer dir) throws COMException {
        return dm.invokeN("FindPicMemEx", new Object[]{x1, y1, x2, y2, pic_info, delta_color, sim, dir}).toString();
    }


    /**
     * 查找指定区域内的图片,位图必须是24位色格式,支持透明色,当图像上下左右4个顶点的颜色一样时,则这个颜色将作为透明色处理.
     * 这个函数可以查找多个图片,只返回第一个找到的X Y坐标. 此函数同FindPic.只是返回值不同.(vip)
     *
     * @param x1          区域的左上X坐标
     * @param y1          区域的左上Y坐标
     * @param x2          区域的右下X坐标
     * @param y2          区域的右下Y坐标
     * @param pic_name    图片名,可以是多个图片,比如"test.bmp|test2.bmp|test3.bmp"
     * @param delta_color 颜色色偏比如"203040" 表示RGB的色偏分别是20 30 40 (这里是16进制表示)
     * @param sim         相似度,取值范围0.1-1.0
     * @param dir         查找方向
     *                    0: 从左到右,从上到下
     *                    1: 从左到右,从下到上
     *                    2: 从右到左,从上到下
     *                    3: 从右到左, 从下到上
     * @param intX        返回图片左上角的X坐标
     * @param intY        返回图片左上角的Y坐标
     *
     * @return String 返回找到的图片的文件名. 没找到返回长度为0的字符串.
     *
     * @throws COMException
     * @example dm_ret = dm.FindPicS(0,0,2000,2000,"1.bmp|2.bmp|3.bmp","000000",0.9,0,intX,intY)
     */
    public String FindPicS(Integer x1, Integer y1, Integer x2, Integer y2, String pic_name, String delta_color, Double sim, Integer dir, Variant.ByrefHolder intX, Variant.ByrefHolder intY) throws COMException {
        return dm.invokeN("FindPicS", new Object[]{x1, y1, x2, y2, pic_name, delta_color, sim, dir, intX, intY}).toString();
    }


    /**
     * 查找指定的形状. 形状的描述同按键的抓抓. 具体可以参考按键的抓抓.
     * 和按键的语法不同，需要用大漠综合工具的颜色转换.(vip)
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param offset_color 坐标偏移描述 可以支持任意多个点 格式和按键自带的Color插件意义相同
     *                     格式为"x1|y1|e1,……xn|yn|en"
     *                     比如"1|3|1,-5|-3|0"等任意组合都可以
     * @param sim          相似度,取值范围0.1-1.0
     * @param dir          查找方向
     *                     0: 从左到右,从上到下
     *                     1: 从左到右,从下到上
     *                     2: 从右到左,从上到下
     *                     3: 从右到左, 从下到上
     * @param intX         返回X坐标(坐标为形状(0,0)所在坐标)
     * @param intY         返回Y坐标(坐标为形状(0,0)所在坐标)
     *
     * @return Integer 0:没找到 1:找到
     *
     * @throws COMException
     * @example 示例
     * dm_ret = dm.FindShape(0,0,2000,2000, "1|1|0,1|6|1,0|10|1,9|10|1,7|6|1,7|8|0,8|9|0,2|2|1,3|1|1",1.0,0,x,y)
     * dm.MoveTo(x,y)
     */
    public Integer FindShape(Integer x1, Integer y1, Integer x2, Integer y2, String offset_color, Double sim, Integer dir, Variant.ByrefHolder intX, Variant.ByrefHolder intY) throws COMException {
        return Integer.parseInt(dm.invokeN("FindShape", new Object[]{x1, y1, x2, y2, offset_color, sim, dir, intX, intY}).toString());
    }


    /**
     * 查找指定的形状. 形状的描述同按键的抓抓. 具体可以参考按键的抓抓.
     * 和按键的语法不同，需要用大漠综合工具的颜色转换.(vip)
     * 易语言用不了FindShape可以用此接口来代替
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param offset_color 坐标偏移描述 可以支持任意多个点 格式和按键自带的Color插件意义相同
     *                     格式为"x1|y1|e1,……xn|yn|en"
     *                     比如"1|3|1,-5|-3|0"等任意组合都可以
     * @param sim          相似度,取值范围0.1-1.0
     * @param dir          查找方向
     *                     0: 从左到右,从上到下
     *                     1: 从左到右,从下到上
     *                     2: 从右到左,从上到下
     *                     3: 从右到左, 从下到上
     *
     * @return String 返回X和Y坐标 形式如"x|y", 比如"100|200"
     *
     * @throws COMException
     * @example 示例
     * dm_ret = dm.FindShape(0,0,2000,2000, "1|1|0,1|6|1,0|10|1,9|10|1,7|6|1,7|8|0,8|9|0,2|2|1,3|1|1",1.0,0,x,y)
     * dm.MoveTo(x,y)
     */
    public String FindShapeE(Integer x1, Integer y1, Integer x2, Integer y2, String offset_color, Double sim, Integer dir) throws COMException {
        return dm.invokeN("FindShapeE", new Object[]{x1, y1, x2, y2, offset_color, sim, dir}).toString();
    }


    /**
     * 查找指定的形状. 形状的描述同按键的抓抓. 具体可以参考按键的抓抓.
     * 和按键的语法不同，需要用大漠综合工具的颜色转换.(vip)
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param offset_color 坐标偏移描述 可以支持任意多个点 格式和按键自带的Color插件意义相同
     *                     格式为"x1|y1|e1,……xn|yn|en"
     *                     比如"1|3|1,-5|-3|0"等任意组合都可以
     * @param sim          相似度,取值范围0.1-1.0
     * @param dir          查找方向
     *                     0: 从左到右,从上到下
     *                     1: 从左到右,从下到上
     *                     2: 从右到左,从上到下
     *                     3: 从右到左, 从下到上
     *
     * @return String 返回所有形状的坐标值,然后通过GetResultCount等接口来解析(由于内存限制,返回的坐标数量最多为1800个左右)
     *
     * @throws COMException
     * @example 示例
     * dm_ret = dm.FindShapeEx(0,0,2000,2000,"1|1|0,1|6|1,0|10|1,9|10|1,7|6|1,7|8|0,8|9|0,2|2|1,3|1|1",1.0,1)
     * count = dm.GetResultCount(dm_ret)
     */
    public String FindShapeEx(Integer x1, Integer y1, Integer x2, Integer y2, String offset_color, Double sim, Integer dir) throws COMException {
        return dm.invokeN("FindShapeEx", new Object[]{x1, y1, x2, y2, offset_color, sim, dir}).toString();
    }


    /**
     * 释放指定的图片,此函数不必要调用,除非你想节省内存.
     *
     * @param pic_name 文件名比如"1.bmp|2.bmp|3.bmp" 等,可以使用通配符,比如
     *                 "*.bmp" 这个对应了所有的bmp文件
     *                 "a?c*.bmp" 这个代表了所有第一个字母是a 第三个字母是c 第二个字母任意的所有bmp文件
     *                 "abc???.bmp|1.bmp|aa??.bmp" 可以这样任意组合.
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example 示例
     * dm_ret = dm.SetPath("c:\test")
     * all_pic = "1.bmp|2.bmp|3.bmp"
     * dm_ret = dm.LoadPic(all_pic)
     * dm_ret = dm.FreePic(all_pic)
     */
    public Integer FreePic(String pic_name) throws COMException {
        return Integer.parseInt(dm.invokeN("FreePic", new Object[]{pic_name}).toString());
    }


    /**
     * 获取范围(x1,y1,x2,y2)颜色的均值,返回格式"H.S.V"
     *
     * @param x1 左上角X
     * @param y1 左上角Y
     * @param x2 右下角X
     * @param y2 右下角Y
     *
     * @return String 颜色字符串
     *
     * @throws COMException
     * @example color = dm.GetAveHSV(30,30,100,100)
     */
    public String GetAveHSV(Integer x1, Integer y1, Integer x2, Integer y2) throws COMException {
        return dm.invokeN("GetAveHSV", new Object[]{x1, y1, x2, y2}).toString();
    }


    /**
     * 获取范围(x1,y1,x2,y2)颜色的均值,返回格式"RRGGBB"
     *
     * @param x1 左上角X
     * @param y1 左上角Y
     * @param x2 右下角X
     * @param y2 右下角Y
     *
     * @return String 颜色字符串
     *
     * @throws COMException
     * @example color = dm.GetAveRGB(30,30,100,100)
     */
    public String GetAveRGB(Integer x1, Integer y1, Integer x2, Integer y2) throws COMException {
        return dm.invokeN("GetAveRGB", new Object[]{x1, y1, x2, y2}).toString();
    }


    /**
     * 获取(x,y)的颜色,颜色返回格式"RRGGBB",注意,和按键的颜色格式相反
     *
     * @param x X坐标
     * @param y Y坐标
     *
     * @return String 颜色字符串(注意这里都是小写字符，和工具相匹配)
     *
     * @throws COMException
     * @example color = dm.GetColor(30,30)
     */
    public String GetColor(Integer x, Integer y) throws COMException {
        return dm.invokeN("GetColor", new Object[]{x, y}).toString();
    }


    /**
     * 获取(x,y)的颜色,颜色返回格式"BBGGRR"
     *
     * @param x X坐标
     * @param y Y坐标
     *
     * @return String 颜色字符串(注意这里都是小写字符，和工具相匹配)
     *
     * @throws COMException
     * @example color = dm.GetColorBGR(30,30)
     */
    public String GetColorBGR(Integer x, Integer y) throws COMException {
        return dm.invokeN("GetColorBGR", new Object[]{x, y}).toString();
    }


    /**
     * 获取(x,y)的HSV颜色,颜色返回格式"H.S.V"
     *
     * @param x X坐标
     * @param y Y坐标
     *
     * @return String 颜色字符串
     *
     * @throws COMException
     * @example color = dm.GetColorHSV(30,30)
     */
    public String GetColorHSV(Integer x, Integer y) throws COMException {
        return dm.invokeN("GetColorHSV", new Object[]{x, y}).toString();
    }


    /**
     * 获取指定区域的颜色数量,颜色格式"RRGGBB-DRDGDB",注意,和按键的颜色格式相反
     *
     * @param x1    区域的左上X坐标
     * @param y1    区域的左上X坐标
     * @param x2    区域的右下X坐标
     * @param y2    区域的右下Y坐标
     * @param color 区域的右下Y坐标
     * @param sim   相似度,取值范围0.1-1.0
     *
     * @return Integer 相似度,取值范围0.1-1.0
     *
     * @throws COMException
     * @example result = dm.GetColorNum(0,0,2000,2000,"123456-000000|aabbcc-030303|ddeeff-202020",1.0)
     */
    public Integer GetColorNum(Integer x1, Integer y1, Integer x2, Integer y2, String color, Double sim) throws COMException {
        return Integer.parseInt(dm.invokeN("GetColorNum", new Object[]{x1, y1, x2, y2, color, sim}).toString());
    }


    /**
     * 获取指定图片的尺寸，如果指定的图片已经被加入缓存，则从缓存中获取信息.
     * 此接口也会把此图片加入缓存.
     *
     * @param pic_name 文件名比如"1.bmp"
     *
     * @return String 形式如 "w,h" 比如"30,20"
     *
     * @throws COMException
     * @example 示例
     * dm_ret = dm.SetPath("c:\test")
     * pic_size = dm.GetPicSize("1.bmp")
     * pic_size = split(pic_size,",")
     * w = int(pic_size(0))
     * h = int(pic_size(1))
     */
    public String GetPicSize(String pic_name) throws COMException {
        return dm.invokeN("GetPicSize", new Object[]{pic_name}).toString();
    }


    /**
     * 获取指定区域的图像,用二进制数据的方式返回,（不适合按键使用）方便二次开发.
     *
     * @param x1 区域的左上X坐标
     * @param y1 区域的左上X坐标
     * @param x2 区域的右下X坐标
     * @param y2 区域的右下Y坐标
     *
     * @return String 返回的是指定区域的二进制颜色数据地址,每个颜色是4个字节,表示方式为(00RRGGBB)
     *
     * @throws COMException
     */
    public String GetScreenData(Integer x1, Integer y1, Integer x2, Integer y2) throws COMException {
        return dm.invokeN("GetScreenData", new Object[]{x1, y1, x2, y2}).toString();
    }


    /**
     * 获取指定区域的图像,用24位位图的数据格式返回,（不适合按键使用）方便二次开发.(vip)
     *
     * @param x1   区域的左上X坐标
     * @param y1   区域的左上X坐标
     * @param x2   区域的右下X坐标
     * @param y2   区域的右下Y坐标
     * @param data 返回图片的数据指针
     * @param size 返回图片的数据长度
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     */
    public Integer GetScreenDataBmp(Integer x1, Integer y1, Integer x2, Integer y2, Variant.ByrefHolder data, Variant.ByrefHolder size) throws COMException {
        return Integer.parseInt(dm.invokeN("GetScreenDataBmp", new Object[]{x1, y1, x2, y2, data, size}).toString());
    }


    /**
     * 转换图片格式为24位BMP格式.
     *
     * @param pic_name 要转换的图片名
     * @param bmp_name 要保存的BMP图片名
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm.ImageToBmp(" 1.png ", " 1.bmp ")
     */
    public Integer ImageToBmp(String pic_name, String bmp_name) throws COMException {
        return Integer.parseInt(dm.invokeN("ImageToBmp", new Object[]{pic_name, bmp_name}).toString());
    }


    /**
     * 判断指定的区域，在指定的时间内(秒),图像数据是否一直不变.(卡屏).
     *
     * @param x1 区域的左上X坐标
     * @param y1 区域的左上X坐标
     * @param x2 区域的右下X坐标
     * @param y2 区域的右下Y坐标
     * @param t  需要等待的时间,单位是秒
     *
     * @return Integer 0 : 没有卡屏，图像数据在变化. 1 : 卡屏. 图像数据在指定的时间内一直没有变化.
     *
     * @throws COMException
     * @example dm.IsDisplayDead(0, 0, 100, 100, 5)
     */
    public Integer IsDisplayDead(Integer x1, Integer y1, Integer x2, Integer y2, Integer t) throws COMException {
        return Integer.parseInt(dm.invokeN("IsDisplayDead", new Object[]{x1, y1, x2, y2, t}).toString());
    }


    /**
     * 预先加载指定的图片,这样在操作任何和图片相关的函数时,将省去了加载图片的时间。调用此函数后,没必要一定要调用FreePic,插件自己会自动释放.
     * 另外,此函数不是必须调用的,所有和图形相关的函数只要调用过一次，图片会自动加入缓存. 如果想对一个已经加入缓存的图片进行修改，那么必须先用FreePic释放此图片在缓存中占用的内存，然后重新调用图片相关接口，就可以重新加载此图片.
     *
     * @param pic_name 文件名比如"1.bmp|2.bmp|3.bmp" 等,可以使用通配符,比如
     *                 "*.bmp" 这个对应了所有的bmp文件
     *                 "a?c*.bmp" 这个代表了所有第一个字母是a 第三个字母是c 第二个字母任意的所有bmp文件
     *                 "abc???.bmp|1.bmp|aa??.bmp" 可以这样任意组合.
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example 示例
     * all_pic = "abc???.bmp|1.bmp|aa??.bmp"
     * dm_ret = dm.LoadPic(all_pic)
     */
    public Integer LoadPic(String pic_name) throws COMException {
        return Integer.parseInt(dm.invokeN("LoadPic", new Object[]{pic_name}).toString());
    }


    /**
     * 根据通配符获取文件集合. 方便用于FindPic和FindPicEx
     *
     * @param pic_name 文件名比如"1.bmp|2.bmp|3.bmp" 等,可以使用通配符,比如
     *                 "*.bmp" 这个对应了所有的bmp文件
     *                 "a?c*.bmp" 这个代表了所有第一个字母是a 第三个字母是c 第二个字母任意的所有bmp文件
     *                 "abc???.bmp|1.bmp|aa??.bmp" 可以这样任意组合.
     *
     * @return String 返回的是通配符对应的文件集合，每个图片以|分割
     *
     * @throws COMException
     * @example 示例
     * all_pic = "abc*.bmp"
     * pic_name = dm.MatchPicName(all_pic)
     */
    public String MatchPicName(String pic_name) throws COMException {
        return dm.invokeN("MatchPicName", new Object[]{pic_name}).toString();
    }


    /**
     * 把RGB的颜色格式转换为BGR(按键格式)
     *
     * @param rgb_color rgb格式的颜色字符串
     *
     * @return String BGR格式的字符串
     *
     * @throws COMException
     * @example bgr_color = dm.RGB2BGR(rgb_color)
     */
    public String RGB2BGR(String rgb_color) throws COMException {
        return dm.invokeN("RGB2BGR", new Object[]{rgb_color}).toString();
    }


    /**
     * 设置图片密码，如果图片本身没有加密，那么此设置不影响不加密的图片，一样正常使用.
     *
     * @param pwd 图片密码
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm_ret = dm.SetPicPwd("123")
     */
    public Integer SetPicPwd(String pwd) throws COMException {
        return Integer.parseInt(dm.invokeN("SetPicPwd", new Object[]{pwd}).toString());
    }


}
