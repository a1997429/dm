package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-09 11:34:16
 * @classname: DmAsmPlus
 * @description: 汇编
 * @version: 7.2107
 */
public class DmAsmPlus extends DmAsm {

    private final static Logger logger = LoggerFactory.getLogger(DmAsmPlus.class);

    private DmSoft dm;

    public DmAsmPlus(DmSoft dm) {
        super(dm);
        this.dm = dm;
    }


    /**
     * 执行用AsmAdd加到缓冲中的指令.  这个接口同AsmCall,但是由于插件内部在每次AsmCall时,都会有对目标进程分配内存的操作,这样会不够效率.
     * 所以增加这个接口，可以让调用者指定分配好的内存,并在此内存上执行call的操作.
     *
     * @param hwnd      窗口句柄
     * @param mode      模式，取值如下
     *                  0 : 在本进程中进行执行，这时hwnd无效. 注: 此模式会创建线程.
     *                  1 : 对hwnd指定的进程内执行,注入模式为创建远程线程
     *                  2 ：必须在对目标窗口进行注入绑定后,才可以用此模式(直接在目标进程创建线程).此模式下的call的执行是排队的,如果同时有多个call在此窗口执行,那么必须排队.所以执行效率不如模式1. 同时此模式受目标窗口刷新速度的影响,目标窗口刷新太慢，也会影响此模式的速度. 注: 此模式会创建线程.
     *                  3 ：同模式2,但是此模式不会创建线程,而直接在hwnd所在线程执行.
     *                  4 ：同模式0, 但是此模式不会创建线程,直接在当前调用AsmCall的线程内执行.
     *                  5 : 对hwnd指定的进程内执行,注入模式为APC. 此模式必须开启memory盾。任意一个memory盾都可以.
     *                  6 : 直接hwnd所在线程执行.
     * @param base_addr 16进制格式. 比如"45A00000",此参数指定的地址必须要求有可读可写可执行属性. 并且内存大小最少要200个字节. 模式6要求至少400个字节. 如果Call的内容较多,那么长度相应也要增加.   如果此参数为空,那么效果就和AsmCall一样.
     *
     * @return Long 获取执行汇编代码以后的EAX的值(32位进程),或者RAX的值(64位进程).一般是函数的返回值. 如果要想知道函数是否执行成功，请查看GetLastError函数.
     * -200 : 执行中出现错误.
     * -201 : 使用模式5时，没有开启memory盾.
     *
     * @throws COMException
     * @example 示例
     * base_addr = dm.VirtualAllocEx(hwnd,0,200,0)
     * dm.AsmClear()
     * dm.AsmAdd("mov eax,1")
     * dm.AsmAdd("push 0123456")
     * dm.AsmAdd("call 0343434")
     * dm.AsmCallEx(hwnd,1,hex(base_addr))
     */
    public Long AsmCallEx(Long hwnd, Integer mode, String base_addr) throws COMException {
        return Long.parseLong(dm.invokeN("AsmCallEx", new Object[]{hwnd, mode, base_addr}).toString());
    }


    /**
     * 此接口对AsmCall和AsmCallEx中的模式5和6中内置的一些延时参数进行设置.
     *
     * @param time_out 具体含义看以下说明.(默认值10000) 单位毫秒
     * @param param    具体含义看以下说明. (默认值100) 单位毫秒
     *
     * @return Integer 0:失败，1:成功
     *
     * @throws COMException
     * @example dm.AsmSetTimeout(10000, 1000)
     */
    public Integer AsmSetTimeout(Integer time_out, Integer param) throws COMException {
        return Integer.parseInt(dm.invokeN("AsmSetTimeout", new Object[]{time_out, param}).toString());
    }


    /**
     * 把汇编缓冲区的指令转换为机器码 并用16进制字符串的形式输出
     *
     * @param base_addr 用AsmAdd添加到缓冲区的第一条指令所在的地址
     * @param is_64bit  表示缓冲区的指令是32位还是64位. 32位表示为0,64位表示为1
     *
     * @return String 机器码，比如 "aa bb cc"这样的形式
     *
     * @throws COMException
     * @example code = dm.Assemble(&H405940,1)
     */
    public String Assemble(Long base_addr, Integer is_64bit) throws COMException {
        return dm.invokeN("Assemble", new Object[]{base_addr, is_64bit}).toString();
    }


    /**
     * 把指定的机器码转换为汇编语言输出
     *
     * @param asm_code  机器码，形式如 "aa bb cc"这样的16进制表示的字符串(空格无所谓)
     * @param base_addr 指令所在的地址
     * @param is_64bit  表示asm_code表示的指令是32位还是64位. 32位表示为0,64位表示为1
     *
     * @return String MASM汇编语言字符串.如果有多条指令，则每条指令以字符"|"连接.
     *
     * @throws COMException
     * @example dm_ret = dm.DisAssemble("81 05 E0 5A 47 00 01 00 00 00",&H435fde,0)
     */
    public String DisAssemble(String asm_code, Long base_addr, Integer is_64bit) throws COMException {
        return dm.invokeN("DisAssemble", new Object[]{asm_code, base_addr, is_64bit}).toString();
    }

}
