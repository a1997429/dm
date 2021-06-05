package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-05 2:37
 * @classname: DmBasic
 * @description: 基础设置
 */
public class DmBasic {

    private final static Logger logger = LoggerFactory.getLogger(DmBasic.class);

    private DmSoft dm;

    public DmBasic(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 获取注册在系统中的dm.dll路径
     *
     * @return String 返回dm.dll所在路径
     *
     * @throws COMException
     */
    public String GetBasePath() throws COMException {
        return dm.invoke("GetBasePath").toString();
    }


    /**
     * 返回当前进程已经创建的dm对象个数(vip)
     *
     * @return Long 个数.
     *
     * @throws COMException
     */
    public Long GetDmCount() throws COMException {
        return Long.parseLong(dm.invoke("GetDmCount").toString());
    }


    /**
     * 返回当前大漠对象的ID值，这个值对于每个对象是唯一存在的。可以用来判定两个大漠对象是否一致.
     *
     * @return Long 当前对象的ID值.
     *
     * @throws COMException
     */
    public Long GetID() throws COMException {
        return Long.parseLong(dm.invoke("GetID").toString());
    }


    /**
     * 获取插件命令的最后错误
     *
     * @return Long 返回值表示错误值。
     * 0表示无错误.<br/>
     * -1 : 表示你使用了绑定里的收费功能，但是没注册，无法使用.<br/>
     * -2 : 使用模式0 2 4 6时出现，因为目标窗口有保护，或者目标窗口没有以管理员权限打开. 常见于win7以上系统.或者有安全软件拦截插件.解决办法: 关闭所有安全软件，并且关闭系统UAC,然后再重新尝试. 如果还不行就可以肯定是目标窗口有特殊保护.<br/>
     * -3 : 使用模式0 2 4 6时出现，可能目标窗口有保护，也可能是异常错误.<br/>
     * -4 : 使用模式1 3 5 7 101 103时出现，这是异常错误.<br/>
     * -5 : 使用模式1 3 5 7 101 103时出现, 这个错误的解决办法就是关闭目标窗口，重新打开再绑定即可. 也可能是运行脚本的进程没有管理员权限.<br/>
     * -6 -7 -9 : 使用模式1 3 5 7 101 103时出现,异常错误. 还有可能是安全软件的问题，比如360等。尝试卸载360.<br/>
     * -8 -10 : 使用模式1 3 5 7 101 103时出现, 目标进程可能有保护,也可能是插件版本过老，试试新的或许可以解决.<br/>
     * -11 : 使用模式1 3 5 7 101 103时出现, 目标进程有保护. 告诉我解决。<br/>
     * -12 : 使用模式1 3 5 7 101 103时出现, 目标进程有保护. 告诉我解决。<br/>
     * -13 : 使用模式1 3 5 7 101 103时出现, 目标进程有保护. 或者是因为上次的绑定没有解绑导致。 尝试在绑定前调用ForceUnBindWindow.<br/>
     * -14 : 使用模式0 1 4 5时出现, 有可能目标机器兼容性不太好. 可以尝试其他模式. 比如2 3 6 7<br/>
     * -16 : 可能使用了绑定模式 0 1 2 3 和 101，然后可能指定了一个子窗口.导致不支持.可以换模式4 5 6 7或者103来尝试. 另外也可以考虑使用父窗口或者顶级窗口.来避免这个错误。还有可能是目标窗口没有正常解绑 然后再次绑定的时候.<br/>
     * -17 : 模式1 3 5 7 101 103时出现. 这个是异常错误. 告诉我解决.<br/>
     * -18 : 句柄无效.<br/>
     * -19 : 使用模式0 1 2 3 101时出现,说明你的系统不支持这几个模式. 可以尝试其他模式.<br/>
     *
     * @throws COMException
     */
    public Integer GetLastError() throws COMException {
        return Integer.parseInt(dm.invoke("GetLastError").toString());
    }


    /**
     * 获取全局路径.(可用于调试)
     *
     * @return String 以字符串的形式返回当前设置的全局路径
     *
     * @throws COMException
     */
    public String GetPath() throws COMException {
        return dm.invoke("GetPath").toString();
    }


    /**
     * 调用此函数来注册，从而使用插件的高级功能.推荐使用此函数.
     *
     * @param reg_code 注册码. (从大漠插件后台获取)
     * @param ver_info 版本附加信息. 可以在后台详细信息查看. 可以任意填写. 可留空. 长度不能超过10. 并且只能包含数字和字母以及小数点. 这个版本信息不是插件版本.
     *
     * @return Long 注册结果
     * -1 : 无法连接网络,(可能防火墙拦截,如果可以正常访问大漠插件网站，那就可以肯定是被防火墙拦截)
     * -2 : 进程没有以管理员方式运行. (出现在win7 vista 2008.建议关闭uac)
     * 0 : 失败 (未知错误)
     * 1 : 成功
     * 2 : 余额不足
     * 3 : 绑定了本机器，但是账户余额不足50元.
     * 4 : 注册码错误
     * 5 : 你的机器或者IP在黑名单列表中或者不在白名单列表中.
     * -8 : 版本附加信息长度超过了10
     * -9 : 版本附加信息里包含了非法字母.
     * 空 : 这是不可能返回空的，如果出现空，那肯定是当前使用的版本不对,老的插件里没这个函数导致返回为空.最好参考文档中的标准写法,判断插件版本号.
     *
     * @throws COMException
     */
    public Long Reg(String reg_code, String ver_info) throws COMException {
        return Long.parseLong(dm.invokeN("Reg", new Object[]{reg_code, ver_info}).toString());
    }


    /**
     * 调用此函数来注册，从而使用插件的高级功能. 可以根据指定的IP列表来注册.
     *
     * @param reg_code 注册码. (从大漠插件后台获取)
     * @param ver_info 版本附加信息. 可以在后台详细信息查看. 可以任意填写. 可留空. 长度不能超过10. 并且只能包含数字和字母以及小数点. 这个版本信息不是插件版本.
     * @param ip       插件注册的ip地址.可以用|来组合,依次对ip中的地址进行注册，直到成功. ip地址列表在VIP群中获取.
     *
     * @return Long 注册结果
     * -1 : 无法连接网络,(可能防火墙拦截,如果可以正常访问大漠插件网站，那就可以肯定是被防火墙拦截)
     * -2 : 进程没有以管理员方式运行. (出现在win7 vista 2008.建议关闭uac)
     * 0 : 失败 (未知错误)
     * 1 : 成功
     * 2 : 余额不足
     * 3 : 绑定了本机器，但是账户余额不足50元.
     * 4 : 注册码错误
     * 5 : 你的机器或者IP在黑名单列表中或者不在白名单列表中.
     * -8 : 版本附加信息长度超过了10
     * -9 : 版本附加信息里包含了非法字母.
     * -10 : 非法的参数ip
     * 空 : 这是不可能返回空的，如果出现空，那肯定是当前使用的版本不对,老的插件里没这个函数导致返回为空.最好参考文档中的标准写法,判断插件版本号.
     *
     * @throws COMException
     */
    public Long RegEx(String reg_code, String ver_info, String ip) throws COMException {
        return Long.parseLong(dm.invokeN("RegEx", new Object[]{reg_code, ver_info, ip}).toString());
    }


    /**
     * 调用此函数来注册，从而使用插件的高级功能. 可以根据指定的IP列表来注册.新手不建议使用! 此函数同RegEx函数的不同在于,此函数用于注册的机器码是不带mac地址的.
     *
     * @param reg_code 注册码. (从大漠插件后台获取)
     * @param ver_info 版本附加信息. 可以在后台详细信息查看. 可以任意填写. 可留空. 长度不能超过10. 并且只能包含数字和字母以及小数点. 这个版本信息不是插件版本.
     * @param ip       插件注册的ip地址.可以用|来组合,依次对ip中的地址进行注册，直到成功. ip地址列表在VIP群中获取.
     *
     * @return Long 注册结果
     * -1 : 无法连接网络,(可能防火墙拦截,如果可以正常访问大漠插件网站，那就可以肯定是被防火墙拦截)
     * -2 : 进程没有以管理员方式运行. (出现在win7 vista 2008.建议关闭uac)
     * 0 : 失败 (未知错误)
     * 1 : 成功
     * 2 : 余额不足
     * 3 : 绑定了本机器，但是账户余额不足50元.
     * 4 : 注册码错误
     * 5 : 你的机器或者IP在黑名单列表中或者不在白名单列表中.
     * -8 : 版本附加信息长度超过了10
     * -9 : 版本附加信息里包含了非法字母.
     * -10 : 非法的参数ip
     * 空 : 这是不可能返回空的，如果出现空，那肯定是当前使用的版本不对,老的插件里没这个函数导致返回为空.最好参考文档中的标准写法,判断插件版本号.
     *
     * @throws COMException
     */
    public Long RegExNoMac(String reg_code, String ver_info, String ip) throws COMException {
        return Long.parseLong(dm.invokeN("RegExNoMac", new Object[]{reg_code, ver_info, ip}).toString());
    }


    /**
     * 调用此函数来注册，从而使用插件的高级功能.推荐使用此函数. 新手不建议使用! 此函数同Reg函数的不同在于,此函数用于注册的机器码是不带mac地址的.
     *
     * @param reg_code 注册码. (从大漠插件后台获取)
     * @param ver_info 版本附加信息. 可以在后台详细信息查看. 可以任意填写. 可留空. 长度不能超过10. 并且只能包含数字和字母以及小数点. 这个版本信息不是插件版本.
     *
     * @return Long 注册结果
     * -1 : 无法连接网络,(可能防火墙拦截,如果可以正常访问大漠插件网站，那就可以肯定是被防火墙拦截)
     * -2 : 进程没有以管理员方式运行. (出现在win7 vista 2008.建议关闭uac)
     * 0 : 失败 (未知错误)
     * 1 : 成功
     * 2 : 余额不足
     * 3 : 绑定了本机器，但是账户余额不足50元.
     * 4 : 注册码错误
     * 5 : 你的机器或者IP在黑名单列表中或者不在白名单列表中.
     * -8 : 版本附加信息长度超过了10
     * -9 : 版本附加信息里包含了非法字母.
     * -10 : 非法的参数ip
     * 空 : 这是不可能返回空的，如果出现空，那肯定是当前使用的版本不对,老的插件里没这个函数导致返回为空.最好参考文档中的标准写法,判断插件版本号.
     *
     * @throws COMException
     */
    public Long RegNoMac(String reg_code, String ver_info) throws COMException {
        return Long.parseLong(dm.invokeN("RegNoMac", new Object[]{reg_code, ver_info}).toString());
    }


    /**
     * 设定图色的获取方式，默认是显示器或者后台窗口(具体参考BindWindow)
     *
     * @param mode 图色输入模式取值有以下几种
     *             "screen" 这个是默认的模式，表示使用显示器或者后台窗口
     *             "pic:file" 指定输入模式为指定的图片,如果使用了这个模式，则所有和图色相关的函数 均视为对此图片进行处理，比如文字识别查找图片 颜色 等等一切图色函数. 需要注意的是，设定以后，此图片就已经加入了缓冲，如果更改了源图片内容，那么需要 释放此缓冲，重新设置.
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     */
    public Integer SetDisplayInput(String mode) throws COMException {
        return Integer.parseInt(dm.invokeN("SetDisplayInput", new Object[]{mode}).toString());
    }


    /**
     * 设置EnumWindow  EnumWindowByProcess  EnumWindowSuper的最长延时. 内部默认超时是5秒.(vip))
     *
     * @param delay 单位毫秒
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     */
    public Integer SetEnumWindowDelay(Integer delay) throws COMException {
        return Integer.parseInt(dm.invokeN("SetEnumWindowDelay", new Object[]{delay}).toString());
    }

    /**
     * 设置全局路径,设置了此路径后,所有接口调用中,相关的文件都相对于此路径. 比如图片,字库等.
     *
     * @param path 路径,可以是相对路径,也可以是绝对路径
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     */
    public Integer SetPath(String path) throws COMException {
        return Integer.parseInt(dm.invokeN("SetPath", new Object[]{path}).toString());
    }


    /**
     * 设置是否弹出错误信息,默认是打开.
     *
     * @param show 0表示不打开,1表示打开
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     */
    public Integer SetShowErrorMsg(Long show) throws COMException {
        return Integer.parseInt(dm.invokeN("SetShowErrorMsg", new Object[]{show}).toString());
    }


    /**
     * 返回当前插件版本号
     *
     * @return String 当前插件的版本描述字符串
     *
     * @throws COMException
     */
    public String Ver() throws COMException {
        return dm.invoke("Ver").toString();
    }


}
