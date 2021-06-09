package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-09 12:37:19
 * @classname: DmRamPlus
 * @description: 内存
 * @version: 7.2107
 */
public class DmRamPlus extends DmRam {

    private final static Logger logger = LoggerFactory.getLogger(DmRamPlus.class);

    private DmSoft dm;

    public DmRamPlus(DmSoft dm) {
        super(dm);
        this.dm = dm;
    }


    /**
     * 释放指定进程的不常用内存.
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     */
    public Integer FreeProcessMemory(Long hwnd) throws COMException {
        return Integer.parseInt(dm.invokeN("FreeProcessMemory", new Object[]{hwnd}).toString());
    }


    /**
     * 获取指定窗口所在进程的启动命令行
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     *
     * @return String 读取到的启动命令行
     *
     * @throws COMException
     */
    public String GetCommandLine(Long hwnd) throws COMException {
        return dm.invokeN("GetCommandLine", new Object[]{hwnd}).toString();
    }


    /**
     * 根据指定的窗口句柄，来获取对应窗口句柄进程下的指定模块的大小
     *
     * @param hwnd   窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param module 模块名
     *
     * @return Long 模块的大小
     *
     * @throws COMException
     * @example module_size = dm.GetModuleSize(hwnd,"gdi32.dll")
     */
    public Long GetModuleSize(Long hwnd, String module) throws COMException {
        return Long.parseLong(dm.invokeN("GetModuleSize", new Object[]{hwnd, module}).toString());
    }


    /**
     * 根据指定的目标模块地址,获取目标窗口(进程)内的导出函数地址.
     *
     * @param hwnd      窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param base_addr 目标模块地址,比如user32.dll的地址,可以通过GetModuleBaseAddr来获取.
     * @param fun_name  需要获取的导出函数名.  比如"SetWindowTextA".
     *
     * @return Long 获取的地址. 如果失败返回0
     *
     * @throws COMException
     * @example 此例子用来在目标进程内执行SetWindowTextA来更改窗口标题.
     * hwnd = dm.GetMousePointWindow()
     * user32_base = dm.GetModuleBaseAddr(hwnd,"user32.dll")
     * SetWindowTextA_addr = dm.GetRemoteApiAddress(hwnd,user32_base,"SetWindowTextA")
     */
    public Long GetRemoteApiAddress(Long hwnd, Long base_addr, String fun_name) throws COMException {
        return Long.parseLong(dm.invokeN("GetRemoteApiAddress", new Object[]{hwnd, base_addr, fun_name}).toString());
    }


    /**
     * 强制转换64位整数为32位. (这个函数是给按键精灵设计的,由于按键精灵不支持64位自动化变量,某些返回64位的整数的接口会出错)
     *
     * @param value 需要转换的64位整数
     *
     * @return Long 返回的32位整数
     *
     * @throws COMException
     * @example base_addr = dm.Int64ToInt32(dm.GetMoudleBaseAddr(hwnd,"ntdll.dll"))
     */
    public Long Int64ToInt32(Long value) throws COMException {
        return Long.parseLong(dm.invokeN("Int64ToInt32", new Object[]{value}).toString());
    }


    /**
     * 根据指定pid打开进程，并返回进程句柄.
     *
     * @param pid 进程pid
     *
     * @return Long 进程句柄, 可用于进程相关操作(读写操作等),记得操作完成以后，自己调用CloseHandle关闭句柄.
     *
     * @throws COMException
     * @example 示例
     * hwnd = dm.GetMousePointWindow()
     * pid = dm.GetWindowProcessId(hwnd)
     * handle = dm.OpenProcess(pid)
     */
    public Long OpenProcess(Long pid) throws COMException {
        return Long.parseLong(dm.invokeN("OpenProcess", new Object[]{pid}).toString());
    }


    /**
     * 读取指定地址的二进制数据
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 地址
     * @param len  二进制数据的长度
     *
     * @return String 读取到的数值,以16进制表示的字符串 每个字节以空格相隔 比如"12 34 56 78 ab cd ef"， 如果要想知道函数是否执行成功，请查看GetLastError函数.
     *
     * @throws COMException
     * @example value = dm.ReadDataAddr(hwnd,123456,10)
     */
    public String ReadDataAddr(Long hwnd, Long addr, Integer len) throws COMException {
        return dm.invokeN("ReadDataAddr", new Object[]{hwnd, addr, len}).toString();
    }


    /**
     * 读取指定地址的二进制数据,只不过返回的是内存地址,而不是字符串.适合高级用户.
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 地址
     * @param len  二进制数据的长度
     *
     * @return Integer 读取到的数据指针. 返回0表示读取失败. 如果要想知道函数是否执行成功，请查看GetLastError函数.
     *
     * @throws COMException
     * @example value = dm.ReadDataAddrToBin(hwnd,12341234 ,10)
     */
    public Long ReadDataAddrToBin(Long hwnd, Long addr, Integer len) throws COMException {
        return Long.parseLong(dm.invokeN("ReadDataAddrToBin", new Object[]{hwnd, addr, len}).toString());
    }


    /**
     * 读取指定地址的二进制数据,只不过返回的是内存地址,而不是字符串.适合高级用户.
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 用字符串来描述地址，类似于CE的地址描述，数值必须是16进制,里面可以用[ ] + -这些符号来描述一个地址。+表示地址加，-表示地址减
     *             模块名必须用<>符号来圈起来
     *             例如:
     *             1.  "4DA678" 最简单的方式，用绝对数值来表示地址
     *             2.  "<360SE.exe>+DA678" 相对简单的方式，只是这里用模块名来决定模块基址，后面的是偏移
     *             3.  "[4DA678]+3A" 用绝对数值加偏移，相当于一级指针
     *             4.  "[<360SE.exe>+DA678]+3A" 用模块定基址的方式，也是一级指针
     *             5.  "[[[<360SE.exe>+DA678]+3A]+5B]+8" 这个是一个三级指针
     *             总之熟悉CE的人 应该对这个地址描述都很熟悉,我就不多举例了
     * @param len  二进制数据的长度
     *
     * @return Integer 读取到的数据指针. 返回0表示读取失败. 如果要想知道函数是否执行成功，请查看GetLastError函数.
     *
     * @throws COMException
     * @example value = dm.ReadDataToBin(hwnd,"4DA678",10)
     */
    public Long ReadDataToBin(Long hwnd, String addr, Integer len) throws COMException {
        return Long.parseLong(dm.invokeN("ReadDataToBin", new Object[]{hwnd, addr, len}).toString());
    }


    /**
     * 读取指定地址的双精度浮点数
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 地址
     *
     * @return Double 读取到的数值
     *
     * @throws COMException
     * @example value = dm.ReadDoubleAddr(hwnd,123456)
     */
    public Double ReadDoubleAddr(Long hwnd, Long addr) throws COMException {
        return Double.parseDouble(dm.invokeN("ReadDoubleAddr", new Object[]{hwnd, addr}).toString());
    }


    /**
     * 读取指定地址的单精度浮点数
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 地址
     *
     * @return Float 读取到的数值
     *
     * @throws COMException
     * @example value = dm.ReadFloatAddr(hwnd,123456)
     */
    public Float ReadFloatAddr(Long hwnd, Long addr) throws COMException {
        return Float.parseFloat(dm.invokeN("ReadFloatAddr", new Object[]{hwnd, addr}).toString());
    }


    /**
     * 读取指定地址的整数数值，类型可以是8位，16位 32位 或者64位
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 地址
     * @param type 整数类型,取值如下
     *             0 : 32位
     *             1 : 16 位
     *             2 : 8位
     *             3 : 64位
     *             4 : 32位无符号
     *             5 : 16位无符号
     *             6 : 8位无符号
     *
     * @return Long 读取到的数值
     *
     * @throws COMException
     * @example value = dm.ReadIntAddr(hwnd,123456, 0)
     */
    public Long ReadIntAddr(Long hwnd, Long addr, Integer type) throws COMException {
        return Long.parseLong(dm.invokeN("ReadIntAddr", new Object[]{hwnd, addr, type}).toString());
    }


    /**
     * 读取指定地址的字符串，可以是GBK字符串或者是Unicode字符串.(必须事先知道内存区的字符串编码方式)
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 地址
     * @param type 字符串类型,取值如下
     *             0 : GBK字符串
     *             1 : Unicode字符串
     *             2 : UTF8字符串
     * @param len  需要读取的字节数目.如果为0，则自动判定字符串长度.
     *
     * @return String 读取到的字符串
     *
     * @throws COMException
     * @example value = dm.ReadStringAddr(hwnd,123456 ,0,0)
     */
    public String ReadStringAddr(Long hwnd, Long addr, Integer type, Integer len) throws COMException {
        return dm.invokeN("ReadStringAddr", new Object[]{hwnd, addr, type, len}).toString();
    }


    /**
     * 这个接口是给E语言设计的. 因为E语言的BUG,导致无法对COM对象调用传入长整数参数(被强制截断成整数),特别设计此接口来兼容长整数的处理.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm_ret = dm.SetParam64ToPointer()
     */
    public Integer SetParam64ToPointer() throws COMException {
        return Integer.parseInt(dm.invoke("SetParam64ToPointer").toString());
    }


    /**
     * 根据指定的PID，强制结束进程.
     *
     * @param pid 进程ID.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm_ret = dm.SetParam64ToPointer()
     * @example 示例
     * hwnd = dm.GetMousePointWindow()
     * pid = dm.GetWindowProcessId(hwnd)
     * dm.TerminateProcess(pid)
     */
    public Integer TerminateProcess(Long pid) throws COMException {
        return Integer.parseInt(dm.invokeN("TerminateProcess", new Object[]{pid}).toString());
    }


    /**
     * 在指定的窗口所在进程分配一段内存.
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 预期的分配地址。 如果是0表示自动分配，否则就尝试在此地址上分配内存.
     * @param size 需要分配的内存大小.
     * @param type 需要分配的内存类型，取值如下:
     *             0 : 可读可写可执行
     *             1 : 可读可执行，不可写
     *             2 : 可读可写,不可执行
     *
     * @return Long 分配的内存地址，如果是0表示分配失败.
     *
     * @throws COMException
     * @example 示例
     * addr = dm.VirtualAllocEx(hwnd,0,50,0)
     * dm.WriteString(hwnd,cstr(hex(addr)),0,"哈哈")
     * dm.VirtualFreeEx(hwnd,addr)
     */
    public Long VirtualAllocEx(Long hwnd, Long addr, Integer size, Integer type) throws COMException {
        return Long.parseLong(dm.invokeN("VirtualAllocEx", new Object[]{hwnd, addr, size, type}).toString());
    }


    /**
     * 释放用VirtualAllocEx分配的内存.
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr VirtualAllocEx返回的地址
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * addr = dm.VirtualAllocEx(hwnd,0,50,0)
     * dm.WriteString(hwnd,cstr(hex(addr)),0,"哈哈")
     * dm.VirtualFreeEx(hwnd,addr)
     */
    public Integer VirtualFreeEx(Long hwnd, Long addr) throws COMException {
        return Integer.parseInt(dm.invokeN("VirtualFreeEx", new Object[]{hwnd, addr}).toString());
    }


    /**
     * 修改指定的窗口所在进程的地址的读写属性,修改为可读可写可执行.
     *
     * @param hwnd        窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr        VirtualAllocEx返回的地址
     * @param size        需要修改的地址大小.
     * @param type        修改的地址读写属性类型，取值如下:
     *                    0 : 可读可写可执行,此时old_protect参数无效
     *                    1 : 修改为old_protect指定的读写属性
     * @param old_protect 指定的读写属性
     *
     * @return Integer 0 : 失败 1 : 修改之前的读写属性
     *
     * @throws COMException
     * @example 示例
     * // 修改地址&H400000为可读可写可执行,并把修改之前的读写属性保存
     * old_protect = dm.VirtualProtectEx(hwnd,&H400000,5,0,0)
     * dm.AsmClear()
     * dm.AsmAdd("lea eax,[400000]")
     * dm.AsmAdd("mov dword ptr[eax],0")
     * dm.AsmCall(hwnd,1)
     * dm.VirtualProtectEx(hwnd,&H400000,5,1,old_protect)
     */
    public Integer VirtualProtectEx(Long hwnd, Long addr, Integer size, Integer type, Integer old_protect) throws COMException {
        return Integer.parseInt(dm.invokeN("VirtualProtectEx", new Object[]{hwnd, addr, size, type, old_protect}).toString());
    }


    /**
     * 获取指定窗口，指定地址的内存属性.
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 需要查询的地址
     * @param pmbi 这是一个地址,指向的内容是MEMORY_BASIC_INFORMATION32或者MEMORY_BASIC_INFORMATION64.
     *             取决于要查询的进程是32位还是64位. 这个地址可以为0,忽略这个参数.
     *             下面是这2个结构体在vc下的定义:
     *             <p>
     *             typedef struct _MEMORY_BASIC_INFORMATION32 {
     *             DWORD BaseAddress;
     *             DWORD AllocationBase;
     *             DWORD AllocationProtect;
     *             DWORD RegionSize;
     *             DWORD State;
     *             DWORD Protect;
     *             DWORD Type;
     *             } MEMORY_BASIC_INFORMATION32, *PMEMORY_BASIC_INFORMATION32;
     *             <p>
     *             typedef struct DECLSPEC_ALIGN(16) _MEMORY_BASIC_INFORMATION64 {
     *             ULONGLONG BaseAddress;
     *             ULONGLONG AllocationBase;
     *             DWORD     AllocationProtect;
     *             DWORD     __alignment1;
     *             ULONGLONG RegionSize;
     *             DWORD     State;
     *             DWORD     Protect;
     *             DWORD     Type;
     *             DWORD     __alignment2;
     *             } MEMORY_BASIC_INFORMATION64, *PMEMORY_BASIC_INFORMATION64;
     *
     * @return String 查询的结果以字符串形式.  内容是"BaseAddress,AllocationBase,AllocationProtect,RegionSize,State,Protect,Type" 数值都是10进制表达.
     *
     * @throws COMException
     * @example 这里我们给一个VC的例子. 其它语言都差不多.
     * MEMORY_BASIC_INFORMATION32 mbi = {0};
     * dm->VirtualQueryEx(hwnd,0x400000,&mbi);
     * if (mbi.BaseAddress)
     * {
     * // 做一些你需要的操作
     * }
     */
    public String VirtualQueryEx(Long hwnd, Long addr, Integer pmbi) throws COMException {
        return dm.invokeN("VirtualQueryEx", new Object[]{hwnd, addr, pmbi}).toString();
    }


    /**
     * 对指定地址写入二进制数据
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 地址
     * @param data 二进制数据，以字符串形式描述，比如"12 34 56 78 90 ab cd"
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm_ret = dm.WriteDataAddr(hwnd,123456 ,"12 34 56 78 90 ab cd")
     */
    public Integer WriteDataAddr(Long hwnd, Long addr, String data) throws COMException {
        return Integer.parseInt(dm.invokeN("WriteDataAddr", new Object[]{hwnd, addr, data}).toString());
    }


    /**
     * 对指定地址写入二进制数据,只不过直接从数据指针获取数据写入,不通过字符串. 适合高级用户.
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 地址
     * @param data 数据指针
     * @param len  数据长度
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm_ret = dm.WriteDataAddrFromBin(hwnd,2934793257239,1231234,10)
     */
    public Integer WriteDataAddrFromBin(Long hwnd, Long addr, Integer data, Integer len) throws COMException {
        return Integer.parseInt(dm.invokeN("WriteDataAddrFromBin", new Object[]{hwnd, addr, data, len}).toString());
    }


    /**
     * 对指定地址写入二进制数据,只不过直接从数据指针获取数据写入,不通过字符串. 适合高级用户.
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 用字符串来描述地址，类似于CE的地址描述，数值必须是16进制,里面可以用[ ] + -这些符号来描述一个地址。+表示地址加，-表示地址减
     *             模块名必须用<>符号来圈起来
     *             例如:
     *             1.         "4DA678" 最简单的方式，用绝对数值来表示地址
     *             2.         "<360SE.exe>+DA678" 相对简单的方式，只是这里用模块名来决定模块基址，后面的是偏移
     *             3.         "[4DA678]+3A" 用绝对数值加偏移，相当于一级指针
     *             4.         "[<360SE.exe>+DA678]+3A" 用模块定基址的方式，也是一级指针
     *             5.         "[[[<360SE.exe>+DA678]+3A]+5B]+8" 这个是一个三级指针
     *             总之熟悉CE的人 应该对这个地址描述都很熟悉,我就不多举例了
     * @param data 数据指针
     * @param len  数据长度
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm_ret = dm.WriteDataFromBin(hwnd,"4DA678",1231234,10)
     */
    public Integer WriteDataFromBin(Long hwnd, String addr, Integer data, Integer len) throws COMException {
        return Integer.parseInt(dm.invokeN("WriteDataFromBin", new Object[]{hwnd, addr, data, len}).toString());
    }


    /**
     * 对指定地址写入双精度浮点数
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 地址
     * @param v    双精度浮点数
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm_ret = dm.WriteDoubleAddr(hwnd,123456 ,2.34)
     */
    public Integer WriteDoubleAddr(Long hwnd, Long addr, Double v) throws COMException {
        return Integer.parseInt(dm.invokeN("WriteDoubleAddr", new Object[]{hwnd, addr, v}).toString());
    }


    /**
     * 对指定地址写入单精度浮点数
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 地址
     * @param v    单精度浮点数
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm_ret = dm.WriteFloatAddr(hwnd,123456 ,2.34)
     */
    public Integer WriteFloatAddr(Long hwnd, Long addr, Float v) throws COMException {
        return Integer.parseInt(dm.invokeN("WriteFloatAddr", new Object[]{hwnd, addr, v}).toString());
    }


    /**
     * 对指定地址写入整数数值，类型可以是8位，16位 32位 或者64位
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 地址
     * @param type 整数类型,取值如下
     *             0 : 32位
     *             1 : 16 位
     *             2 : 8位
     *             3 : 64位
     * @param v    整形数值
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm_ret = dm.WriteIntAddr(hwnd,123456,0,100)
     */
    public Integer WriteIntAddr(Long hwnd, Long addr, Integer type, Long v) throws COMException {
        return Integer.parseInt(dm.invokeN("WriteIntAddr", new Object[]{hwnd, addr, type, v}).toString());
    }


    /**
     * 对指定地址写入字符串，可以是Ascii字符串或者是Unicode字符串
     *
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 地址
     * @param type 字符串类型,取值如下
     *             0 : Ascii字符串
     *             1 : Unicode字符串
     *             2 : UTF8字符串
     * @param v    字符串
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example dm_ret = dm.WriteStringAddr(hwnd,123456 ,0,"我是来测试的")
     */
    public Integer WriteStringAddr(Long hwnd, Long addr, Integer type, String v) throws COMException {
        return Integer.parseInt(dm.invokeN("WriteStringAddr", new Object[]{hwnd, addr, type, v}).toString());
    }


}
