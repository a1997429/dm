package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-09 11:18:51
 * @classname: DmGuardPlus
 * @description: 防护盾
 * @version: 7.2107
 */
public class DmGuardPlus extends DmGuard {

    private final static Logger logger = LoggerFactory.getLogger(DmGuardPlus.class);

    private DmSoft dm;

    public DmGuardPlus(DmSoft dm) {
        super(dm);
        this.dm = dm;
    }


    /**
     * DmGuard的加强接口,用于获取一些额外信息. 具体看下面参数介绍
     *
     * @param cmd    盾类型. 这里取值为"gr"(以后可能会有扩充). 这里要注意的是,如果要获取指定的盾类型信息,必须先成功用DmGuard开启此盾.比如这里的"gr"必须dm.DmGuard 1,"gr"开启成功才可以
     * @param subcmd 针对具体的盾类型，需要获取的具体信息.
     *               如果cmd为"gr"时,那么subcmd取值如下定义:
     *               "enum" : 枚举指定进程的所有句柄.
     *               "get"  : 获取指定进程的指定句柄信息.(类型,名字,权限值)
     *               "set"  : 设置指定进程的指定句柄的权限值.
     *               "close": 关闭指定进程的指定句柄.
     * @param param  参数信息,这里具体的含义取决于cmd和subcmd.
     *               如果cmd为"gr"时,subcmd取值为如下时，具体的参数含义:
     *               "enum" : param为"pid", pid为进程pid,10进制形式.
     *               "get"  : param为"pid handle",pid为进程pid,10进制形式,handle为句柄值,10进制形式.
     *               "set"  : param为"pid handle access",pid为进程pid,10进制形式,handle为句柄值,10进制形式,access为权限值,10进制形式.
     *               "close": param为"pid handle", pid为进程pid,10进制形式,handle为句柄值,10进制形式.
     *
     * @return String 根据不同的cmd和subcmd,返回值不同.
     * 如果cmd为"gr"时,subcmd取值为如下时，具体的返回值:
     * "enum" : "handle1|handle2|.....|handlen",每个handle都是10进制长整数. 如果失败返回空字符串
     * "get"  : "type|name|access". type表示句柄的类型，比如"Event","File"等之类的. name表示句柄的名字,有些句柄的名字可能是空的. access10进制整数,表示此句柄的权限值. 如果失败返回空字符串
     * "set"  : 成功返回"ok",否则为空字符串.
     * "close": 成功返回"ok",否则为空字符串.
     *
     * @throws COMException
     * @example 示例
     * // 枚举进程的所有句柄,并获取此句柄的信息,并打印出来
     * dm_ret = dm.DmGuard(1,"gr")
     * dm_ret = dm.DmGuardParams("gr","enum","1024")
     * // 关闭指定进程的句柄
     * dm_ret = dm.DmGuard(1,"gr")
     * dm_ret = dm.DmGuardParams("gr","close","1024 240")
     */
    public String DmGuardParams(String cmd, String subcmd, String param) throws COMException {
        return dm.invokeN("DmGuardParams", new Object[]{cmd, subcmd, param}).toString();
    }


    /**
     * 卸载插件相关的所有驱动. 仅对64位系统的驱动生效.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @exampel dm.UnLoadDriver()
     * @note 注 : 此接口一般不建议使用. 除非你真的知道自己在干什么.
     */
    public Integer UnLoadDriver() throws COMException {
        return Integer.parseInt(dm.invoke("UnLoadDriver").toString());
    }


}
