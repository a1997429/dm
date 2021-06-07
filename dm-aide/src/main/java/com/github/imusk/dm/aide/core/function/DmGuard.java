package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-07 09:08:54
 * @classname: DmGuard
 * @description: 防护盾
 */
public class DmGuard {

    private final static Logger logger = LoggerFactory.getLogger(DmFaq.class);

    private DmSoft dm;

    public DmGuard(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 针对部分检测措施的保护盾.(vip)
     *
     * @param enable 0表示关闭保护盾
     *               1表示打开保护盾.
     * @param type   参数具体内容可以是以下任意一个.
     *               "np" : 这个是防止NP检测.
     *               "np2" : 在NP下保护后台可以正常运行. (此模式需要加载驱动,目前仅支持32位系统)
     *               "memory" : 这个保护内存系列接口和汇编接口可以正常运行.(不用带dx.public.memory的情况下)(此模式需要加载驱动,目前仅支持32位系统)
     *               "phide [pid]" : 保护指定进程不被非法访问. pid为可选参数.如果不指定pid，默认保护当前进程.(此模式需要加载驱动,目前仅支持32位系统)
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm.DmGuard(1, " np ")
     * dm.DmGuard(1,"phide")
     * dm.DmGuard(1,"phide 1045")
     */
    public Integer DmGuard(Integer enable, String type) throws COMException {
        return Integer.parseInt(dm.invokeN("DmGuard", new Object[]{enable, type}).toString());
    }


}
