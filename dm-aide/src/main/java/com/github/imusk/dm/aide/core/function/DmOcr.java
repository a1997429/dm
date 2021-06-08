package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.jawin.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-08 19:28
 * @classname: DmOcr
 * @description: 文字识别
 */
public class DmOcr {

    private final static Logger logger = LoggerFactory.getLogger(DmOcr.class);

    private DmSoft dm;

    public DmOcr(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 给指定的字库中添加一条字库信息.
     *
     * @param index     字库的序号,取值为0-9,目前最多支持10个字库
     * @param dict_info 字库描述串，具体参考大漠综合工具中的字符定义
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm_ret = dm.AddDict(0,"081101BF8020089FD10A21443F85038$记$0.0$11")
     */
    public Integer AddDict(Integer index, String dict_info) throws COMException {
        return Integer.parseInt(dm.invokeN("AddDict", new Object[]{index, dict_info}).toString());
    }


    /**
     * 清空指定的字库.
     *
     * @param index 字库的序号,取值为0-9,目前最多支持10个字库
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm_ret = dm.ClearDict(0)
     */
    public Integer ClearDict(Integer index) throws COMException {
        return Integer.parseInt(dm.invokeN("ClearDict", new Object[]{index}).toString());
    }


    /**
     * 根据指定的范围,以及指定的颜色描述，提取点阵信息，类似于大漠工具里的单独提取.
     *
     * @param x1    左上角X坐标
     * @param y1    左上角Y坐标
     * @param x2    右下角X坐标
     * @param y2    右下角Y坐标
     * @param color 颜色格式串.注意，RGB和HSV格式都支持.
     * @param word  待定义的文字,不能为空，且不能为关键符号"$"
     *
     * @return String 识别到的点阵信息，可用于AddDict， 如果失败，返回空
     *
     * @throws COMException
     * @example info = dm.FetchWord(200,200,250,220,"abcdef-101010|ffffff-101010","张三")
     */
    public String FetchWord(Integer x1, Integer y1, Integer x2, Integer y2, String color, String word) throws COMException {
        return dm.invokeN("FetchWord", new Object[]{x1, y1, x2, y2, color, word}).toString();
    }


    /**
     * 在屏幕范围(x1,y1,x2,y2)内,查找string(可以是任意个字符串的组合),并返回符合color_format的坐标位置,相似度sim同Ocr接口描述.
     * (多色,差色查找类似于Ocr接口,不再重述)
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param string       待查找的字符串,可以是字符串组合，比如"长安|洛阳|大雁塔",中间用"|"来分割字符串
     * @param color_format 颜色格式串, 可以包含换行分隔符,语法是","后加分割字符串. 具体可以查看下面的示例 .注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     * @param intX         返回X坐标没找到返回-1
     * @param intY         返回Y坐标没找到返回-1
     *
     * @return Integer 返回字符串的索引 没找到返回-1, 比如"长安|洛阳",若找到长安，则返回0
     *
     * @throws COMException
     * @example 示例
     * dm_ret = dm.FindStr(0,0,2000,2000,"长安","9f2e3f-000000",1.0,intX,intY)
     * dm.MoveTo(intX, intY)
     */
    public Integer FindStr(Integer x1, Integer y1, Integer x2, Integer y2, String string, String color_format, Double sim, Variant.ByrefHolder intX, Variant.ByrefHolder intY) throws COMException {
        return Integer.parseInt(dm.invokeN("FindStr", new Object[]{x1, y1, x2, y2, string, color_format, sim, intX, intY}).toString());
    }


    /**
     * 在屏幕范围(x1,y1,x2,y2)内,查找string(可以是任意个字符串的组合),并返回符合color_format的坐标位置,相似度sim同Ocr接口描述.
     * (多色,差色查找类似于Ocr接口,不再重述)
     * 易语言用不了FindStr可以用此接口来代替
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param string       待查找的字符串,可以是字符串组合，比如"长安|洛阳|大雁塔",中间用"|"来分割字符串
     * @param color_format 颜色格式串, 可以包含换行分隔符,语法是","后加分割字符串. 具体可以查看下面的示例 .注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     *
     * @return String 返回字符串序号以及X和Y坐标,形式如"id|x|y", 比如"0|100|200",没找到时，id和X以及Y均为-1，"-1|-1|-1"
     *
     * @throws COMException
     * @example 示例
     * pos = dm.FindStrE(0,0,2000,2000,"长安","9f2e3f-000000",1.0)
     * dm.MoveTo(pos(1), pos(2))
     */
    public String FindStrE(Integer x1, Integer y1, Integer x2, Integer y2, String string, String color_format, Double sim) throws COMException {
        return dm.invokeN("FindStrE", new Object[]{x1, y1, x2, y2, string, color_format, sim}).toString();
    }


    /**
     * 在屏幕范围(x1,y1,x2,y2)内,查找string(可以是任意字符串的组合),并返回符合color_format的所有坐标位置,相似度sim同Ocr接口描述.
     * (多色,差色查找类似于Ocr接口,不再重述)
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param string       待查找的字符串,可以是字符串组合，比如"长安|洛阳|大雁塔",中间用"|"来分割字符串
     * @param color_format 颜色格式串, 可以包含换行分隔符,语法是","后加分割字符串. 具体可以查看下面的示例 .注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     *
     * @return String 返回所有找到的坐标集合,格式如下:
     * "id,x0,y0|id,x1,y1|......|id,xn,yn"
     * 比如"0,100,20|2,30,40" 表示找到了两个,第一个,对应的是序号为0的字符串,坐标是(100,20),第二个是序号为2的字符串,坐标(30,40)
     *
     * @throws COMException
     * @example pos = dm.FindStrEx(0,0,2000,2000,"长安","9f2e3f-000000",1.0)
     */
    public String FindStrEx(Integer x1, Integer y1, Integer x2, Integer y2, String string, String color_format, Double sim) throws COMException {
        return dm.invokeN("FindStrEx", new Object[]{x1, y1, x2, y2, string, color_format, sim}).toString();
    }


    /**
     * 在屏幕范围(x1,y1,x2,y2)内,查找string(可以是任意字符串的组合),并返回符合color_format的所有坐标位置,相似度sim同Ocr接口描述.
     * (多色,差色查找类似于Ocr接口,不再重述) 此函数同FindStrEx,只是返回值不同.(vip)
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param string       待查找的字符串,可以是字符串组合，比如"长安|洛阳|大雁塔",中间用"|"来分割字符串
     * @param color_format 颜色格式串, 可以包含换行分隔符,语法是","后加分割字符串. 具体可以查看下面的示例 .注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     *
     * @return String 返回所有找到的坐标集合,格式如下:
     * "str,x0,y0| str,x1,y1|......| str,xn,yn"
     * 比如"长安,100,20|大雁塔,30,40" 表示找到了两个,第一个是长安 ,坐标是(100,20),第二个是大雁塔,坐标(30,40)
     *
     * @throws COMException
     * @example dm_ret = dm.FindStrExS(0,0,2000,2000,"长安|洛阳","9f2e3f-000000",1.0)
     */
    public String FindStrExS(Integer x1, Integer y1, Integer x2, Integer y2, String string, String color_format, Double sim) throws COMException {
        return dm.invokeN("FindStrExS", new Object[]{x1, y1, x2, y2, string, color_format, sim}).toString();
    }


    /**
     * 同FindStr。
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param string       待查找的字符串,可以是字符串组合，比如"长安|洛阳|大雁塔",中间用"|"来分割字符串
     * @param color_format 颜色格式串, 可以包含换行分隔符,语法是","后加分割字符串. 具体可以查看下面的示例 .注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     * @param intX         返回X坐标没找到返回-1
     * @param intY         返回Y坐标没找到返回-1
     *
     * @return Integer 返回字符串的索引 没找到返回-1, 比如"长安|洛阳",若找到长安，则返回0
     *
     * @throws COMException
     * @example dm_ret = dm.FindStrFast(0,0,2000,2000,"长安","9f2e3f-000000",1.0,intX,intY)
     */
    public Integer FindStrFast(Integer x1, Integer y1, Integer x2, Integer y2, String string, String color_format, Double sim, Variant.ByrefHolder intX, Variant.ByrefHolder intY) throws COMException {
        return Integer.parseInt(dm.invokeN("FindStrFast", new Object[]{x1, y1, x2, y2, string, color_format, sim, intX, intY}).toString());
    }


    /**
     * 同FindStr。
     * 易语言用不了FindStrFast可以用此接口来代替
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param string       待查找的字符串,可以是字符串组合，比如"长安|洛阳|大雁塔",中间用"|"来分割字符串
     * @param color_format 颜色格式串, 可以包含换行分隔符,语法是","后加分割字符串. 具体可以查看下面的示例 .注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     *
     * @return String 返回字符串序号以及X和Y坐标,形式如"id|x|y", 比如"0|100|200",没找到时，id和X以及Y均为-1，"-1|-1|-1"
     *
     * @throws COMException
     * @example dm_ret = dm.FindStrFastE(0,0,2000,2000,"长安","9f2e3f-000000",1.0)
     */
    public String FindStrFastE(Integer x1, Integer y1, Integer x2, Integer y2, String string, String color_format, Double sim) throws COMException {
        return dm.invokeN("FindStrFastE", new Object[]{x1, y1, x2, y2, string, color_format, sim}).toString();
    }


    /**
     * 同FindStr。
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param string       待查找的字符串,可以是字符串组合，比如"长安|洛阳|大雁塔",中间用"|"来分割字符串
     * @param color_format 颜色格式串, 可以包含换行分隔符,语法是","后加分割字符串. 具体可以查看下面的示例 .注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     *
     * @return String 返回所有找到的坐标集合,格式如下:
     * "id,x0,y0|id,x1,y1|......|id,xn,yn"
     * 比如"0,100,20|2,30,40" 表示找到了两个,第一个,对应的是序号为0的字符串,坐标是(100,20),第二个是序号为2的字符串,坐标(30,40)
     *
     * @throws COMException
     * @example dm_ret = dm.FindStrFastEx(0,0,2000,2000,"长安|洛阳","9f2e3f-000000",0.9)
     */
    public String FindStrFastEx(Integer x1, Integer y1, Integer x2, Integer y2, String string, String color_format, Double sim) throws COMException {
        return dm.invokeN("FindStrFastEx", new Object[]{x1, y1, x2, y2, string, color_format, sim}).toString();
    }


    /**
     * 同FindStrExS. (vip)
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param string       待查找的字符串,可以是字符串组合，比如"长安|洛阳|大雁塔",中间用"|"来分割字符串
     * @param color_format 颜色格式串, 可以包含换行分隔符,语法是","后加分割字符串. 具体可以查看下面的示例 .注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     *
     * @return String 返回所有找到的坐标集合,格式如下:
     * "str,x0,y0| str,x1,y1|......| str,xn,yn"
     * 比如"长安,100,20|大雁塔,30,40" 表示找到了两个,第一个是长安 ,坐标是(100,20),第二个是大雁塔,坐标(30,40)
     *
     * @throws COMException
     * @example dm_ret = dm.FindStrFastExS(0,0,2000,2000,"长安|洛阳","9f2e3f-000000",0.9)
     */
    public String FindStrFastExS(Integer x1, Integer y1, Integer x2, Integer y2, String string, String color_format, Double sim) throws COMException {
        return dm.invokeN("FindStrFastExS", new Object[]{x1, y1, x2, y2, string, color_format, sim}).toString();
    }


    /**
     * 同FindStrS
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param string       待查找的字符串,可以是字符串组合，比如"长安|洛阳|大雁塔",中间用"|"来分割字符串
     * @param color_format 颜色格式串, 可以包含换行分隔符,语法是","后加分割字符串. 具体可以查看下面的示例 .注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     * @param intX         返回X坐标没找到返回-1
     * @param intY         返回Y坐标没找到返回-1
     *
     * @return String 返回找到的字符串. 没找到的话返回长度为0的字符串.
     *
     * @throws COMException
     * @example dm_ret = dm.FindStrFastS(0,0,2000,2000,"长安","9f2e3f-000000",1.0,intX,intY)
     */
    public String FindStrFastS(Integer x1, Integer y1, Integer x2, Integer y2, String string, String color_format, Double sim, Variant.ByrefHolder intX, Variant.ByrefHolder intY) throws COMException {
        return dm.invokeN("FindStrFastS", new Object[]{x1, y1, x2, y2, string, color_format, sim, intX, intY}).toString();
    }


    /**
     * 在屏幕范围(x1,y1,x2,y2)内,查找string(可以是任意个字符串的组合),并返回符合color_format的坐标位置,相似度sim同Ocr接口描述.
     * (多色,差色查找类似于Ocr接口,不再重述).此函数同FindStr,只是返回值不同.
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param string       待查找的字符串,可以是字符串组合，比如"长安|洛阳|大雁塔",中间用"|"来分割字符串
     * @param color_format 颜色格式串, 可以包含换行分隔符,语法是","后加分割字符串. 具体可以查看下面的示例 .注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     * @param intX         返回X坐标没找到返回-1
     * @param intY         返回Y坐标没找到返回-1
     *
     * @return String 返回找到的字符串. 没找到的话返回长度为0的字符串.
     *
     * @throws COMException
     * @example dm_ret = dm.FindStrS(0,0,2000,2000,"长安","9f2e3f-000000",1.0,intX,intY)
     */
    public String FindStrS(Integer x1, Integer y1, Integer x2, Integer y2, String string, String color_format, Double sim, Variant.ByrefHolder intX, Variant.ByrefHolder intY) throws COMException {
        return dm.invokeN("FindStrS", new Object[]{x1, y1, x2, y2, string, color_format, sim, intX, intY}).toString();
    }


    /**
     * 同FindStr，但是不使用SetDict设置的字库，而利用系统自带的字库，速度比FindStr稍慢.
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param string       待查找的字符串,可以是字符串组合，比如"长安|洛阳|大雁塔",中间用"|"来分割字符串
     * @param color_format 颜色格式串, 可以包含换行分隔符,语法是","后加分割字符串. 具体可以查看下面的示例 .注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     * @param font_name    系统字体名,比如"宋体"
     * @param font_size    系统字体尺寸，这个尺寸一定要以大漠综合工具获取的为准.如果获取尺寸看视频教程.
     * @param flag         字体类别 取值可以是以下值的组合,比如1+2+4+8,2+4. 0表示正常字体.
     *                     1 : 粗体
     *                     2 : 斜体
     *                     4 : 下划线
     *                     8 : 删除线
     * @param intX         返回X坐标没找到返回-1
     * @param intY         返回Y坐标没找到返回-1
     *
     * @return Integer 返回字符串的索引 没找到返回-1, 比如"长安|洛阳",若找到长安，则返回0
     *
     * @throws COMException
     * @example dm_ret = dm.FindStrWithFont(0,0,2000,2000,"长安","9f2e3f-000000",1.0,"宋体",9,0,intX,intY)
     */
    public Integer FindStrWithFont(Integer x1, Integer y1, Integer x2, Integer y2, String string, String color_format, Double sim, String font_name, Integer font_size, Integer flag, Variant.ByrefHolder intX, Variant.ByrefHolder intY) throws COMException {
        return Integer.parseInt(dm.invokeN("FindStrWithFont", new Object[]{x1, y1, x2, y2, string, color_format, sim, font_name, font_size, flag, intX, intY}).toString());
    }


    /**
     * 同FindStrE，但是不使用SetDict设置的字库，而利用系统自带的字库，速度比FindStr稍慢.
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param string       待查找的字符串,可以是字符串组合，比如"长安|洛阳|大雁塔",中间用"|"来分割字符串
     * @param color_format 颜色格式串, 可以包含换行分隔符,语法是","后加分割字符串. 具体可以查看下面的示例 .注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     * @param font_name    系统字体名,比如"宋体"
     * @param font_size    系统字体尺寸，这个尺寸一定要以大漠综合工具获取的为准.如果获取尺寸看视频教程.
     * @param flag         字体类别 取值可以是以下值的组合,比如1+2+4+8,2+4. 0表示正常字体.
     *                     1 : 粗体
     *                     2 : 斜体
     *                     4 : 下划线
     *                     8 : 删除线
     *
     * @return String 返回字符串序号以及X和Y坐标,形式如"id|x|y", 比如"0|100|200",没找到时，id和X以及Y均为-1，"-1|-1|-1"
     *
     * @throws COMException
     * @example dm_ret = dm.FindStrWithFontE(0,0,2000,2000,"长安","9f2e3f-000000",1.0,"宋体",9,0)
     */
    public String FindStrWithFontE(Integer x1, Integer y1, Integer x2, Integer y2, String string, String color_format, Double sim, String font_name, Integer font_size, Integer flag) throws COMException {
        return dm.invokeN("FindStrWithFontE", new Object[]{x1, y1, x2, y2, string, color_format, sim, font_name, font_size, flag}).toString();
    }


    /**
     * 同FindStrEx，但是不使用SetDict设置的字库，而利用系统自带的字库，速度比FindStrEx稍慢
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param string       待查找的字符串,可以是字符串组合，比如"长安|洛阳|大雁塔",中间用"|"来分割字符串
     * @param color_format 颜色格式串, 可以包含换行分隔符,语法是","后加分割字符串. 具体可以查看下面的示例 .注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     * @param font_name    系统字体名,比如"宋体"
     * @param font_size    系统字体尺寸，这个尺寸一定要以大漠综合工具获取的为准.如果获取尺寸看视频教程.
     * @param flag         字体类别 取值可以是以下值的组合,比如1+2+4+8,2+4. 0表示正常字体.
     *                     1 : 粗体
     *                     2 : 斜体
     *                     4 : 下划线
     *                     8 : 删除线
     *
     * @return String 返回所有找到的坐标集合,格式如下:
     * "id,x0,y0|id,x1,y1|......|id,xn,yn"
     * 比如"0,100,20|2,30,40" 表示找到了两个,第一个,对应的是序号为0的字符串,坐标是(100,20),第二个是序号为2的字符串,坐标(30,40)
     *
     * @throws COMException
     * @example dm_ret = dm.FindStrWithFontEx(0,0,2000,2000,"长安|洛阳","9f2e3f-000000",1.0,"宋体",9,1+2)
     */
    public String FindStrWithFontEx(Integer x1, Integer y1, Integer x2, Integer y2, String string, String color_format, Double sim, String font_name, Integer font_size, Integer flag) throws COMException {
        return dm.invokeN("FindStrWithFontEx", new Object[]{x1, y1, x2, y2, string, color_format, sim, font_name, font_size, flag}).toString();
    }


    /**
     * 获取指定字库中指定条目的字库信息.(vip)
     *
     * @param index      字库序号(0-9)
     * @param font_index 字库条目序号(从0开始计数,数值不得超过指定字库的字库上限,具体参考GetDictCount)
     *
     * @return String 返回字库条目信息. 失败返回空串.
     *
     * @throws COMException
     * @example s = dm.GetDict(0,1245)
     */
    public String GetDict(Integer index, Integer font_index) throws COMException {
        return dm.invokeN("GetDict", new Object[]{index, font_index}).toString();
    }


    /**
     * 获取指定的字库中的字符数量.
     *
     * @param index 字库序号(0-9)
     *
     * @return Integer 字库数量
     *
     * @throws COMException
     * @example count = dm.GetDictCount(0)
     */
    public Integer GetDictCount(Integer index) throws COMException {
        return Integer.parseInt(dm.invokeN("GetDictCount", new Object[]{index}).toString());
    }


    /**
     * 根据指定的文字，以及指定的系统字库信息，获取字库描述信息.
     *
     * @param str       需要获取的字符串
     * @param font_name 系统字体名,比如"宋体"
     * @param font_size 系统字体尺寸，这个尺寸一定要以大漠综合工具获取的为准.如何获取尺寸看视频教程.
     * @param flag      字体类别 取值可以是以下值的组合,比如1+2+4+8,2+4. 0表示正常字体.
     *                  1 : 粗体
     *                  2 : 斜体
     *                  4 : 下划线
     *                  8 : 删除线
     *
     * @return String 返回字库信息,每个字符的字库信息用"|"来分割
     *
     * @throws COMException
     * @example font_desc = dm.GetDictInfo("回收站","宋体",9,0)
     */
    public String GetDictInfo(String str, String font_name, Integer font_size, Integer flag) throws COMException {
        return dm.invokeN("GetDictInfo", new Object[]{str, font_name, font_size, flag}).toString();
    }


    /**
     * 获取当前使用的字库序号(0-9)
     *
     * @return Integer 字库序号(0-9)
     *
     * @throws COMException
     * @example index = dm.GetNowDict()
     */
    public Integer GetNowDict() throws COMException {
        return Integer.parseInt(dm.invoke("GetNowDict").toString());
    }


    /**
     * 对插件部分接口的返回值进行解析,并返回ret中的坐标个数
     *
     * @param ret 部分接口的返回串
     *
     * @return Integer 返回ret中的坐标个数
     *
     * @throws COMException
     * @example 示例
     * s = dm.FindColorEx(0,0,2000,2000,"123456-000000|abcdef-202020",1.0,0)
     * count = dm.GetResultCount(s)
     */
    public Integer GetResultCount(String ret) throws COMException {
        return Integer.parseInt(dm.invokeN("GetResultCount", new Object[]{ret}).toString());
    }


    /**
     * 对插件部分接口的返回值进行解析,并根据指定的第index个坐标,返回具体的值
     *
     * @param ret   部分接口的返回串
     * @param index 第几个坐标
     * @param intX  返回X坐标
     * @param intY  返回Y坐标
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example 示例
     * s = dm.FindColorEx(0,0,2000,2000,"123456-000000|abcdef-202020",1.0,0)
     * count = dm.GetResultCount(s)
     */
    public Integer GetResultPos(String ret, Integer index, Variant.ByrefHolder intX, Variant.ByrefHolder intY) throws COMException {
        return Integer.parseInt(dm.invokeN("GetResultPos", new Object[]{ret, index, intX, intY}).toString());
    }


    /**
     * 在使用GetWords进行词组识别以后,可以用此接口进行识别词组数量的计算.
     *
     * @param str GetWords接口调用以后的返回值
     *
     * @return Integer 返回词组数量
     *
     * @throws COMException
     * @example 示例
     * s = dm.GetWords(0,0,2000,2000,"000000-000000",1.0)
     * count = dm.GetWordResultCount(s)
     */
    public Integer GetWordResultCount(String str) throws COMException {
        return Integer.parseInt(dm.invokeN("GetWordResultCount", new Object[]{str}).toString());
    }


    /**
     * 在使用GetWords进行词组识别以后,可以用此接口进行识别各个词组的坐标
     *
     * @param str   GetWords的返回值
     * @param index 表示第几个词组
     * @param intX  返回X坐标
     * @param intY  返回Y坐标
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example 示例
     * s = dm.GetWords(0,0,2000,2000,"000000-000000",1.0)
     * count = dm.GetWordResultCount(s)
     * dm_ret = dm.GetWordResultPos(s,index,intX,intY)
     */
    public Integer GetWordResultPos(String str, Integer index, Variant.ByrefHolder intX, Variant.ByrefHolder intY) throws COMException {
        return Integer.parseInt(dm.invokeN("GetWordResultPos", new Object[]{str, index, intX, intY}).toString());
    }


    /**
     * 在使用GetWords进行词组识别以后,可以用此接口进行识别各个词组的内容
     *
     * @param str   GetWords的返回值
     * @param index 表示第几个词组
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example 示例
     * s = dm.GetWords(0,0,2000,2000,"000000-000000",1.0)
     * count = dm.GetWordResultCount(s)
     * word = dm.GetWordResultStr(s,index)
     */
    public Integer GetWordResultStr(String str, Integer index) throws COMException {
        return Integer.parseInt(dm.invokeN("GetWordResultStr", new Object[]{str, index}).toString());
    }


    /**
     * 根据指定的范围,以及设定好的词组识别参数(一般不用更改,除非你真的理解了)
     * 识别这个范围内所有满足条件的词组. 比较适合用在未知文字的情况下,进行不定识别.
     *
     * @param x1    左上角X坐标
     * @param y1    左上角Y坐标
     * @param x2    右下角X坐标
     * @param y2    右下角Y坐标
     * @param color 颜色格式串.注意，RGB和HSV格式都支持.
     * @param sim   相似度 0.1-1.0
     *
     * @return String 识别到的格式串,要用到专用函数来解析
     *
     * @throws COMException
     * @example 示例
     * s = dm.GetWords(0,0,2000,2000,"000000-000000",1.0)
     * count = dm.GetWordResultCount(s)
     * dm_ret = dm.GetWordResultPos(s,index,intX,intY)
     * word = dm.GetWordResultStr(s,index)
     */
    public String GetWords(Integer x1, Integer y1, Integer x2, Integer y2, String color, Double sim) throws COMException {
        return dm.invokeN("GetWords", new Object[]{x1, y1, x2, y2, color, sim}).toString();
    }


    /**
     * 根据指定的范围,以及设定好的词组识别参数(一般不用更改,除非你真的理解了)
     * 识别这个范围内所有满足条件的词组. 这个识别函数不会用到字库。只是识别大概形状的位置
     *
     * @param x1    左上角X坐标
     * @param y1    左上角Y坐标
     * @param x2    右下角X坐标
     * @param y2    右下角Y坐标
     * @param color 颜色格式串.注意，RGB和HSV格式都支持.
     *
     * @return String 识别到的格式串,要用到专用函数来解析
     *
     * @throws COMException
     * @example 示例
     * s = dm.GetWordsNoDict(0,0,2000,2000,"000000-000000")
     * count = dm.GetResultCount(s)
     * dm_ret = dm.GetResultPos(s,index,intX,intY)
     */
    public String GetWordsNoDict(Integer x1, Integer y1, Integer x2, Integer y2, String color) throws COMException {
        return dm.invokeN("GetWordsNoDict", new Object[]{x1, y1, x2, y2, color}).toString();
    }


    /**
     * 识别屏幕范围(x1,y1,x2,y2)内符合color_format的字符串,并且相似度为sim,sim取值范围(0.1-1.0),
     * 这个值越大越精确,越大速度越快,越小速度越慢,请斟酌使用!
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param color_format 颜色格式串. 可以包含换行分隔符,语法是","后加分割字符串. 具体可以查看下面的示例.注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     *
     * @return String 返回识别到的字符串
     *
     * @throws COMException
     * @example 示例
     * //RGB单色识别
     * s = dm.Ocr(0,0,2000,2000,"9f2e3f-000000",1.0)
     * //RGB单色差色识别
     * s = dm.Ocr(0,0,2000,2000,"9f2e3f-030303",1.0)
     * //RGB多色识别(最多支持10种,每种颜色用"|"分割)
     * s = dm.Ocr(0,0,2000,2000,"9f2e3f-030303|2d3f2f-000000|3f9e4d-100000",1.0)
     * //HSV多色识别(最多支持10种,每种颜色用"|"分割)
     * s = dm.Ocr(0,0,2000,2000,"20.30.40-0.0.0|30.40.50-0.0.0",1.0)
     * //识别后,每行字符串用指定字符分割，比如用"|"字符分割
     * s = dm.Ocr(0,0,2000,2000,"9f2e3f-000000,|",1.0)
     * //比如用回车换行分割
     * s = dm.Ocr(0,0,2000,2000,"9f2e3f-000000,"+vbcrlf,1.0)
     * //比如要识别背景色为白色,文字颜色未知的字形
     * s = dm.Ocr(0,0,2000,2000,"b@ffffff-000000",1.0)
     */
    public String Ocr(Integer x1, Integer y1, Integer x2, Integer y2, String color_format, Double sim) throws COMException {
        return dm.invokeN("Ocr", new Object[]{x1, y1, x2, y2, color_format, sim}).toString();
    }


    /**
     * 识别屏幕范围(x1,y1,x2,y2)内符合color_format的字符串,并且相似度为sim,sim取值范围(0.1-1.0),
     * 这个值越大越精确,越大速度越快,越小速度越慢,请斟酌使用!
     * 这个函数可以返回识别到的字符串，以及每个字符的坐标.
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param color_format 颜色格式串.注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     *
     * @return String 返回识别到的字符串 格式如  "识别到的信息|x0,y0|…|xn,yn"
     *
     * @throws COMException
     * @example ss = dm.OcrEx(0,0,2000,2000,"ffffff|000000",1.0)
     */
    public String OcrEx(Integer x1, Integer y1, Integer x2, Integer y2, String color_format, Double sim) throws COMException {
        return dm.invokeN("OcrEx", new Object[]{x1, y1, x2, y2, color_format, sim}).toString();
    }


    /**
     * 识别屏幕范围(x1,y1,x2,y2)内符合color_format的字符串,并且相似度为sim,sim取值范围(0.1-1.0),
     * 这个值越大越精确,越大速度越快,越小速度越慢,请斟酌使用!
     * 这个函数可以返回识别到的字符串，以及每个字符的坐标.
     *
     * @param x1           区域的左上X坐标
     * @param y1           区域的左上Y坐标
     * @param x2           区域的右下X坐标
     * @param y2           区域的右下Y坐标
     * @param pic_name     图片文件名
     * @param color_format 颜色格式串.注意，RGB和HSV格式都支持.
     * @param sim          相似度,取值范围0.1-1.0
     *
     * @return String 返回识别到的字符串
     *
     * @throws COMException
     * @example s = dm.OcrInFile(0,0,2000,2000,"test.bmp","000000-000000",1.0)
     */
    public String OcrInFile(Integer x1, Integer y1, Integer x2, Integer y2, String pic_name, String color_format, Double sim) throws COMException {
        return dm.invokeN("OcrInFile", new Object[]{x1, y1, x2, y2, pic_name, color_format, sim}).toString();
    }


    /**
     * 保存指定的字库到指定的文件中.
     *
     * @param index 字库索引序号 取值为0-9对应10个字库
     * @param file  文件名
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example 示例
     * dm.SetPath("c:\test_game")
     * dm.AddDict(0,"FFF00A7D49292524A7D402805FFC$回$0.0.54$11")
     * dm.AddDict(0,"3F0020087FF08270B9A108268708808$收$0.0.43$11")
     * dm.AddDict(0,"2055C98617420807C097F222447C800$站$0.0.44$11")
     * dm.SaveDict(0,"test.txt")
     */
    public Integer SaveDict(Integer index, String file) throws COMException {
        return Integer.parseInt(dm.invokeN("SaveDict", new Object[]{index, file}).toString());
    }


    /**
     * 高级用户使用,在不使用字库进行词组识别前,可设定文字的列距,默认列距是1
     *
     * @param col_gap 文字列距
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm_ret = dm.SetColGapNoDict(3)
     */
    public Integer SetColGapNoDict(Integer col_gap) throws COMException {
        return Integer.parseInt(dm.invokeN("SetColGapNoDict", new Object[]{col_gap}).toString());
    }


    /**
     * 高级用户使用,在不使用字库进行词组识别前,可设定文字的列距,默认列距是1
     *
     * @param index 字库的序号,取值为0-9,目前最多支持10个字库
     * @param file  字库文件名
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm_ret = dm.SetDict(0,"test.txt")
     */
    public Integer SetDict(Integer index, String file) throws COMException {
        return Integer.parseInt(dm.invokeN("SetDict", new Object[]{index, file}).toString());
    }


    /**
     * 从内存中设置字库.(vip)
     *
     * @param index 字库的序号,取值为0-9,目前最多支持10个字库
     * @param addr  数据地址
     * @param size  字库长度
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm_ret = dm.SetDict(0,"test.txt")
     */
    public Integer SetDictMem(Integer index, Integer addr, Integer size) throws COMException {
        return Integer.parseInt(dm.invokeN("SetDictMem", new Object[]{index, addr, size}).toString());
    }


    /**
     * 设置字库的密码,在SetDict前调用,目前的设计是,所有字库通用一个密码.
     *
     * @param pwd 字库密码
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm_ret = dm.SetDictPwd("1234")
     */
    public Integer SetDictPwd(String pwd) throws COMException {
        return Integer.parseInt(dm.invokeN("SetDictPwd", new Object[]{pwd}).toString());
    }


    /**
     * 高级用户使用,在使用文字识别功能前，设定是否开启精准识别.
     *
     * @param exact_ocr 0 表示关闭精准识别
     *                  1 开启精准识别
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example 示例
     * // 开启精准识别
     * dm_ret = dm.SetExactOcr(1)
     */
    public Integer SetExactOcr(Integer exact_ocr) throws COMException {
        return Integer.parseInt(dm.invokeN("SetExactOcr", new Object[]{exact_ocr}).toString());
    }


    /**
     * 高级用户使用,在识别前,如果待识别区域有多行文字,可以设定列间距,默认的列间距是0,
     * 如果根据情况设定,可以提高识别精度。一般不用设定。
     *
     * @param min_col_gap 最小列间距
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm_ret = dm.SetMinColGap(1)
     */
    public Integer SetMinColGap(Integer min_col_gap) throws COMException {
        return Integer.parseInt(dm.invokeN("SetMinColGap", new Object[]{min_col_gap}).toString());
    }


    /**
     * 高级用户使用,在识别前,如果待识别区域有多行文字,可以设定行间距,默认的行间距是1,
     * 如果根据情况设定,可以提高识别精度。一般不用设定。
     *
     * @param min_row_gap 最小行间距
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm_ret = dm.SetMinRowGap(2)
     */
    public Integer SetMinRowGap(Integer min_row_gap) throws COMException {
        return Integer.parseInt(dm.invokeN("SetMinRowGap", new Object[]{min_row_gap}).toString());
    }


    /**
     * 高级用户使用,在不使用字库进行词组识别前,可设定文字的行距,默认行距是1
     *
     * @param row_gap 文字行距
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm_ret = dm.SetRowGapNoDict(3)
     */
    public Integer SetRowGapNoDict(Integer row_gap) throws COMException {
        return Integer.parseInt(dm.invokeN("SetRowGapNoDict", new Object[]{row_gap}).toString());
    }


    /**
     * 高级用户使用,在识别词组前,可设定词组间的间隔,默认的词组间隔是5
     *
     * @param word_gap 单词间距
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm_ret = dm.SetWordGap(5)
     */
    public Integer SetWordGap(Integer word_gap) throws COMException {
        return Integer.parseInt(dm.invokeN("SetWordGap", new Object[]{word_gap}).toString());
    }


    /**
     * 高级用户使用,在不使用字库进行词组识别前,可设定词组间的间隔,默认的词组间隔是5
     *
     * @param word_gap 单词间距
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm_ret = dm.SetWordGapNoDict(1)
     */
    public Integer SetWordGapNoDict(Integer word_gap) throws COMException {
        return Integer.parseInt(dm.invokeN("SetWordGapNoDict", new Object[]{word_gap}).toString());
    }


    /**
     * 高级用户使用,在识别词组前,可设定文字的平均行高,默认的词组行高是10
     *
     * @param line_height 行高
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm_ret = dm.SetWordLineHeight(15)
     */
    public Integer SetWordLineHeight(Integer line_height) throws COMException {
        return Integer.parseInt(dm.invokeN("SetWordLineHeight", new Object[]{line_height}).toString());
    }


    /**
     * 高级用户使用,在不使用字库进行词组识别前,可设定文字的平均行高,默认的词组行高是10
     *
     * @param line_height 行高
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example dm_ret = dm.SetWordLineHeightNoDict(15)
     */
    public Integer SetWordLineHeightNoDict(Integer line_height) throws COMException {
        return Integer.parseInt(dm.invokeN("SetWordLineHeightNoDict", new Object[]{line_height}).toString());
    }


    /**
     * 表示使用哪个字库文件进行识别(index范围:0-9)
     * 设置之后，永久生效，除非再次设定
     *
     * @param index 字库编号(0-9)
     *
     * @return Integer 0:失败 1:成功
     *
     * @throws COMException
     * @example 示例
     * dm_ret = dm.UseDict(1)
     * ss = dm.Ocr(0,0,2000,2000,"FFFFFF-000000",1.0)
     * dm_ret = dm.UseDict(0)
     */
    public Integer UseDict(Integer index) throws COMException {
        return Integer.parseInt(dm.invokeN("UseDict", new Object[]{index}).toString());
    }


}
