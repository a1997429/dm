package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-07 18:40
 * @classname: DmAlgorithm
 * @description: 算法
 */
public class DmAlgorithm {

    private final static Logger logger = LoggerFactory.getLogger(DmAlgorithm.class);

    private DmSoft dm;

    public DmAlgorithm(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 根据部分Ex接口的返回值，排除指定范围区域内的坐标.
     *
     * @param all_pos 坐标描述串。  一般是FindStrEx,FindStrFastEx,FindStrWithFontEx, FindColorEx, FindMultiColorEx,和FindPicEx的返回值.
     * @param type    取值为0或者1
     *                如果all_pos的内容是由FindPicEx,FindStrEx,FindStrFastEx,FindStrWithFontEx返回，那么取值为0
     *                如果all_pos的内容是由FindColorEx, FindMultiColorEx返回，那么取值为1
     * @param x1      左上角横坐标
     * @param y1      左上角纵坐标
     * @param x2      右下角横坐标
     * @param y2      右下角纵坐标
     *
     * @return String 经过筛选以后的返回值，格式和type指定的一致.
     *
     * @throws COMException
     * @example 示例
     * ret = dm.FindColorEx(0,0,2000,2000,"aaaaaa-000000",1.0,0)
     * ret = dm.ExcludePos(ret,1,100,100,300,400)
     * <p>
     * ret = dm.FindPicEx(0,0,2000,2000,"a.bmp","000000",1.0,0)
     * ret = dm.ExcludePos(ret,0,100,100,300,400)
     */
    public String ExcludePos(String all_pos, Integer type, Integer x1, Integer y1, Integer x2, Integer y2) throws COMException {
        return dm.invokeN("ExcludePos", new Object[]{all_pos, type, x1, y1, x2, y2}).toString();
    }


    /**
     * 根据部分Ex接口的返回值，然后在所有坐标里找出距离指定坐标最近的那个坐标.
     *
     * @param all_pos 坐标描述串。  一般是FindStrEx,FindStrFastEx,FindStrWithFontEx, FindColorEx, FindMultiColorEx,和FindPicEx的返回值.
     * @param type    取值为0或者1
     *                如果all_pos的内容是由FindPicEx,FindStrEx,FindStrFastEx,FindStrWithFontEx返回，那么取值为0
     *                如果all_pos的内容是由FindColorEx, FindMultiColorEx返回，那么取值为1
     * @param x       横坐标
     * @param y       纵坐标
     *
     * @return String 返回的格式和type有关，如果type为0，那么返回的格式是"id,x,y"，如果type为1,那么返回的格式是"x,y".
     *
     * @throws COMException
     * @example 示例
     * ret = dm.FindColorEx(0,0,2000,2000,"aaaaaa-000000",1.0,0)
     * ret = dm.FindNearestPos(ret,1,100,100)
     * <p>
     * ret = dm.FindColorEx(0,0,2000,2000,"aaaaaa-000000",1.0,0)
     * ret = dm.FindNearestPos(ret,1,100,100)
     */
    public String FindNearestPos(String all_pos, Integer type, Integer x, Integer y) throws COMException {
        return dm.invokeN("FindNearestPos", new Object[]{all_pos, type, x, y}).toString();
    }


    /**
     * 根据部分Ex接口的返回值，然后对所有坐标根据对指定坐标的距离进行从小到大的排序.
     *
     * @param all_pos 坐标描述串。  一般是FindStrEx,FindStrFastEx,FindStrWithFontEx, FindColorEx, FindMultiColorEx,和FindPicEx的返回值.
     * @param type    取值为0或者1
     *                如果all_pos的内容是由FindPicEx,FindStrEx,FindStrFastEx,FindStrWithFontEx返回，那么取值为0
     *                如果all_pos的内容是由FindColorEx, FindMultiColorEx返回，那么取值为1
     * @param x       横坐标
     * @param y       纵坐标
     *
     * @return String 返回的格式和type指定的格式一致.
     *
     * @throws COMException
     * @example 示例
     * ret = dm.FindColorEx(0,0,2000,2000,"aaaaaa-000000",1.0,0)
     * ret = dm.SortPosDistance(ret,1,100,100)
     * <p>
     * ret = dm.FindPicEx(0,0,2000,2000,"a.bmp","000000",1.0,0)
     * ret = dm.SortPosDistance(ret,0,100,100)
     */
    public String SortPosDistance(String all_pos, Integer type, Integer x, Integer y) throws COMException {
        return dm.invokeN("SortPosDistance", new Object[]{all_pos, type, x, y}).toString();
    }


}
