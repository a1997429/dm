package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.jawin.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-10 19:59
 * @classname: DmPictureColorPlus
 * @description: 图色
 * @version: 7.2107
 */
public class DmPictureColorPlus extends DmPictureColor {

    private final static Logger logger = LoggerFactory.getLogger(DmPictureColorPlus.class);

    private DmSoft dm;

    public DmPictureColorPlus(DmSoft dm) {
        super(dm);
        this.dm = dm;
    }


    /**
     * 当执行FindPicXXX系列接口时,是否在条件满足下(查找的图片大于等于4),开启多线程查找。默认打开.
     *
     * @param enable 0 关闭
     *               1 打开
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * dm.EnableFindPicMultithread(0)
     * dm.FindPicXXX
     * dm.EnableFindPicMultithread(1)
     */
    public Integer DmPictureColorPlus(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("DmPictureColorPlus", new Object[]{enable}).toString());
    }


    /**
     * 查找指定区域内的颜色块,颜色格式"RRGGBB-DRDGDB",注意,和按键的颜色格式相反
     *
     * @param x1     区域的左上X坐标
     * @param y1     区域的左上Y坐标
     * @param x2     区域的右下X坐标
     * @param y2     区域的右下Y坐标
     * @param color  颜色 格式为"RRGGBB-DRDGDB",比如"123456-000000|aabbcc-202020".注意，这里只支持RGB颜色.
     * @param sim    相似度,取值范围0.1-1.0
     * @param count  在宽度为width,高度为height的颜色块中，符合color颜色的最小数量.(注意,这个颜色数量可以在综合工具的二值化区域中看到)
     * @param width  颜色块的宽度
     * @param height 颜色块的宽度
     * @param intX   返回X坐标(指向颜色块的左上角)
     * @param intY   返回Y坐标(指向颜色块的左上角)
     *
     * @return Integer 0:没找到 1:找到
     *
     * @throws COMException
     * @example dm_ret = dm.FindColorBlock(0,0,2000,2000,"123456-000000|aabbcc-030303|ddeeff-202020",1.0,350,100,200,intX,intY)
     */
    public Integer FindColorBlock(Integer x1, Integer y1, Integer x2, Integer y2, String color, Double sim, Integer count, Integer width, Integer height, Variant.ByrefHolder intX, Variant.ByrefHolder intY) throws COMException {
        return Integer.parseInt(dm.invokeN("FindColorBlock", new Object[]{x1, y1, x2, y2, color, sim, count, width, height, intX, intY}).toString());
    }


    /**
     * 查找指定区域内的所有颜色块,颜色格式"RRGGBB-DRDGDB",注意,和按键的颜色格式相反
     *
     * @param x1     区域的左上X坐标
     * @param y1     区域的左上Y坐标
     * @param x2     区域的右下X坐标
     * @param y2     区域的右下Y坐标
     * @param color  颜色 格式为"RRGGBB-DRDGDB",比如"123456-000000|aabbcc-202020".注意，这里只支持RGB颜色.
     * @param sim    相似度,取值范围0.1-1.0
     * @param count  在宽度为width,高度为height的颜色块中，符合color颜色的最小数量.(注意,这个颜色数量可以在综合工具的二值化区域中看到)
     * @param width  颜色块的宽度
     * @param height 颜色块的宽度
     *
     * @return String 返回所有颜色块信息的坐标值,然后通过GetResultCount等接口来解析 (由于内存限制,返回的颜色数量最多为1800个左右)
     *
     * @throws COMException
     * @example dm_ret = dm.FindColorBlockEx(0,0,2000,2000,"123456-000000|aabbcc-030303|ddeeff-202020",1.0,350,100,200)
     */
    public String FindColorBlockEx(Integer x1, Integer y1, Integer x2, Integer y2, String color, Double sim, Integer count, Integer width, Integer height) throws COMException {
        return dm.invokeN("FindColorBlock", new Object[]{x1, y1, x2, y2, color, sim, count, width, height}).toString();
    }


    /**
     * 预先加载指定的图片,这样在操作任何和图片相关的函数时,将省去了加载图片的时间。调用此函数后,没必要一定要调用FreePic,插件自己会自动释放.
     * 另外,此函数不是必须调用的,所有和图形相关的函数只要调用过一次，图片会自动加入缓存.
     * 如果想对一个已经加入缓存的图片进行修改，那么必须先用FreePic释放此图片在缓存中占用
     * 的内存，然后重新调用图片相关接口，就可以重新加载此图片. （当图色缓存机制打开时,具体参考EnablePicCache）
     * 此函数同LoadPic，只不过LoadPic是从文件中加载图片,而LoadPicByte从给定的内存中加载.
     *
     * @param addr     BMP图像首地址.(完整的BMP图像，不是经过解析的. 和BMP文件里的内容一致)
     * @param size     BMP图像大小.(和BMP文件大小一致)
     * @param pic_name 文件名,指定这个地址对应的图片名. 用于找图时使用.
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm.LoadPicByte(123456, 300, " 1.bmp ")
     */
    public Integer LoadPicByte(Long addr, Integer size, String pic_name) throws COMException {
        return Integer.parseInt(dm.invokeN("LoadPicByte", new Object[]{addr, size, pic_name}).toString());
    }


    /**
     * 设置图色,以及文字识别时,需要排除的区域.(支持所有图色接口,以及文字相关接口,但对单点取色,或者单点颜色比较的接口不支持)
     *
     * @param mode 模式,取值如下:
     *             0: 添加排除区域
     *             1: 设置排除区域的颜色,默认颜色是FF00FF(此接口的原理是把排除区域设置成此颜色,这样就可以让这块区域实效)
     *             2: 请空排除区域
     * @param info 根据mode的取值来决定
     *             当mode为0时,此参数指添加的区域,可以多个区域,用"|"相连. 格式为"x1,y1,x2,y2|....."
     *             当mode为1时,此参数为排除区域的颜色,"RRGGBB"
     *             当mode为2时,此参数无效
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example 示例
     * // 先清空区域
     * dm.SetExcludeRegion(2,"")
     * // 添加区域
     * dm.SetExcludeRegion(0,"30,30,100,300|300,400,500,600")
     * dm.SetExcludeRegion(0,"100,100,200,200")
     * 至于颜色如果有需要也可以设置比如
     * dm.SetExcludeRegion(1,"FF11FF")
     */
    public Integer SetExcludeRegion(Integer mode, String info) throws COMException {
        return Integer.parseInt(dm.invokeN("SetExcludeRegion", new Object[]{mode, info}).toString());
    }


}
