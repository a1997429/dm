package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-07 10:30:02
 * @classname: DmAsm
 * @description: 汇编
 */
public class DmAsm {

    private final static Logger logger = LoggerFactory.getLogger(DmAsm.class);

    private DmSoft dm;

    public DmAsm(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 添加指定的MASM汇编指令
     *
     * @param asm_ins MASM汇编指令,大小写均可以  比如 "mov eax,1"
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example 示例
     * dm.AsmAdd("push 0100")
     * dm.AsmAdd("push 060304d")
     * dm.AsmAdd("call 0678fed")
     */
    public Integer AsmAdd(String asm_ins) throws COMException {
        return Integer.parseInt(dm.invokeN("AsmAdd", new Object[]{asm_ins}).toString());
    }


    /**
     * 执行用AsmAdd加到缓冲中的指令. 多线程下是收费功能, 免费版本多线程会异常.(vip)
     *
     * @param hwnd 窗口句柄
     * @param mode 0 : 在本进程中进行执行，这时hwnd无效
     *             1 : 对hwnd指定的进程内执行,注入模式为创建远程线程
     *
     * @return Long 获取执行汇编代码以后的EAX的值.一般是函数的返回值.
     *
     * @throws COMException
     * @example 示例
     * dm.AsmClear()
     * dm.AsmAdd("mov eax,1")
     * dm.AsmAdd("push 0123456")
     * dm.AsmAdd("call 0343434")
     * dm.AsmCall(hwnd,1)
     */
    public Long AsmCall(Long hwnd, Integer mode) throws COMException {
        return Long.parseLong(dm.invokeN("AsmCall", new Object[]{hwnd, mode}).toString());
    }


    /**
     * 清除汇编指令缓冲区 用AsmAdd添加到缓冲的指令全部清除
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm.AsmClear()
     */
    public Integer AsmClear() throws COMException {
        return Integer.parseInt(dm.invoke("AsmClear").toString());
    }


    /**
     * 把汇编缓冲区的指令转换为机器码 并用16进制字符串的形式输出
     *
     * @param base_addr 用AsmAdd添加到缓冲区的第一条指令所在的地址
     *
     * @return String 机器码，比如 "aa bb cc"这样的形式
     *
     * @throws COMException
     * @example code = dm.AsmCode(&H405940)
     */
    public String AsmCode(String base_addr) throws COMException {
        return dm.invokeN("AsmCode", new Object[]{base_addr}).toString();
    }


    /**
     * 把指定的机器码转换为汇编语言输出
     *
     * @param asm_code  机器码，形式如 "aa bb cc"这样的16进制表示的字符串(空格无所谓)
     * @param base_addr 指令所在的地址
     * @param is_upper  表示转换的汇编语言是否以大写输出
     *
     * @return String MASM汇编语言字符串
     *
     * @throws COMException
     * @example dm_ret = dm.Assemble("81 05 E0 5A 47 00 01 00 00 00",&H435fde,0)
     */
    public String Assemble(String asm_code, Integer base_addr, Integer is_upper) throws COMException {
        return dm.invokeN("Assemble", new Object[]{asm_code, base_addr, is_upper}).toString();
    }


}
