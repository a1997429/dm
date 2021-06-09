package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-09 12:21:53
 * @classname: DmBasicPlus
 * @description: 基础设置
 * @version: 7.2107
 */
public class DmBasicPlus extends DmBasic {

    private final static Logger logger = LoggerFactory.getLogger(DmBasicPlus.class);

    private DmSoft dm;

    public DmBasicPlus(DmSoft dm) {
        super(dm);
        this.dm = dm;
    }


    /**
     * 设置是否开启或者关闭插件内部的图片缓存机制. (默认是打开).
     *
     * @param enable 0 : 关闭
     *               1 : 打开
     *
     * @return Integer 0:失败，1:成功
     *
     * @throws COMException
     * @example dm.EnablePicCache(0)
     */
    public Integer EnablePicCache(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("EnablePicCache", new Object[]{enable}).toString());
    }


    /**
     * 设置是否对前台图色进行加速. (默认是关闭). (对于不绑定，或者绑定图色为normal生效)( 仅对WIN8以上系统有效)
     *
     * @param enable 0 : 关闭
     *               1 : 打开
     *
     * @return Integer 0:失败，1:成功
     *
     * @throws COMException
     * @example dm.SpeedNormalGraphic(0)
     */
    public Integer SpeedNormalGraphic(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("SpeedNormalGraphic", new Object[]{enable}).toString());
    }


}
