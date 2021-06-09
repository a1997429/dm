package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-09 11:13:46
 * @classname: DmFaqPlus
 * @description: 答题
 * @version: 7.2107
 */
public class DmFaqPlus extends DmFaq {

    private final static Logger logger = LoggerFactory.getLogger(DmFaqPlus.class);

    private DmSoft dm;

    public DmFaqPlus(DmSoft dm) {
        super(dm);
        this.dm = dm;
    }


    /**
     * 从给定的字符串(也可以算是文字类型的问题),获取此句柄. （此接口必须配合答题器v30以后的版本）
     *
     * @param str 文字类型的问题. 比如(桃园三结义指的是哪些人?)
     *
     * @return Long 文字句柄
     *
     * @throws COMException
     * @example handle = dm.FaqCaptureString("汉朝的帝都是哪里?")
     */
    public Long FaqCaptureString(String str) throws COMException {
        return Long.parseLong(dm.invokeN("FaqCaptureString", new Object[]{str}).toString());
    }


    /**
     * 用于判断当前对象是否有发送过答题(FaqPost)
     * @return Integer 0 : 没有 1 : 有发送过
     * @throws COMException
     */
    public Integer FaqIsPosted() throws COMException {
        return Integer.parseInt(dm.invoke("FaqIsPosted").toString());
    }


}
