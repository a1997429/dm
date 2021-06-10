package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-10 22:15
 * @classname: DmOcrPlus
 * @description: 文字识别
 * @version: 7.2107
 */
public class DmOcrPlus extends DmOcr {

    private final static Logger logger = LoggerFactory.getLogger(DmOcrPlus.class);

    private DmSoft dm;

    public DmOcrPlus(DmSoft dm) {
        super(dm);
        this.dm = dm;
    }


    /**
     * 允许当前调用的对象使用全局字库。  如果你的程序中对象太多,并且每个对象都用到了同样的字库,可以考虑用全局字库,这样可以节省大量内存.
     *
     * @param enable 0 关闭
     *               1 打开
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * dm.EnableShareDict(1)
     * dm.SetDict(0,"xxx.txt")
     */
    public Integer EnableShareDict(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("EnableShareDict", new Object[]{enable}).toString());
    }


}
