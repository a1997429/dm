package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-07 13:57:39
 * @classname: DmRam
 * @description: 内存
 */
public class DmRam {

    private final static Logger logger = LoggerFactory.getLogger(DmRam.class);

    private DmSoft dm;

    public DmRam(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 把双精度浮点数转换成二进制形式.
     *
     * @param value 需要转化的双精度浮点数
     *
     * @return String 字符串形式表达的二进制数据. 可以用于WriteData FindData FindDataEx等接口.
     *
     * @throws COMException
     * @example double_data =  dm.DoubleToData(1.24)
     * dm_ret = dm.FindData(hwnd,"00000000-7fffffff",double_data)
     */
    public String DoubleToData(Double value) throws COMException {
        return dm.invokeN("DoubleToData", new Object[]{value}).toString();
    }


    /**
     * 搜索指定的二进制数据,默认步长是1.如果要定制步长，请用FindDataEx
     *
     * @param hwnd       指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr_range 指定搜索的地址集合，字符串类型，这个地方可以是上次FindXXX的返回地址集合,可以进行二次搜索.(类似CE的再次扫描)
     *                   如果要进行地址范围搜索，那么这个值为的形如如下(类似于CE的新搜索)
     *                   "00400000-7FFFFFFF" "80000000-BFFFFFFF" "00000000-FFFFFFFF" 等.
     * @param data       要搜索的二进制数据 以字符串的形式描述比如"00 01 23 45 67 86 ab ce f1"等.
     *                   这里也可以支持模糊查找,用??来代替单个字节. 比如"00 01 ?? ?? 67 86 ?? ce f1"等. 模糊查找是收费功能.(vip)
     *
     * @return String 返回搜索到的地址集合，地址格式如: "addr1|addr2|addr3…|addrn"
     * 例如："400050|423435|453430"
     *
     * @throws COMException
     * @example result = dm.FindData(hwnd,"00000000-FFFFFFFF","00 01 23 45 67 86 ab ce f1")
     */
    public String FindData(Long hwnd, String addr_range, String data) throws COMException {
        return dm.invokeN("FindData", new Object[]{hwnd, addr_range, data}).toString();
    }


    /**
     * 搜索指定的二进制数据.（vip）
     *
     * @param hwnd         指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr_range   指定搜索的地址集合，字符串类型，这个地方可以是上次FindXXX的返回地址集合,可以进行二次搜索.(类似CE的再次扫描)
     *                     如果要进行地址范围搜索，那么这个值为的形如如下(类似于CE的新搜索)
     *                     "00400000-7FFFFFFF" "80000000-BFFFFFFF" "00000000-FFFFFFFF" 等.
     * @param data         要搜索的二进制数据 以字符串的形式描述比如"00 01 23 45 67 86 ab ce f1"等.
     *                     这里也可以支持模糊查找,用??来代替单个字节. 比如"00 01 ?? ?? 67 86 ?? ce f1"等. 模糊查找是收费功能.(vip)
     * @param step         搜索步长.
     * @param multi_thread 表示是否开启多线程查找.  0不开启，1开启.
     *                     开启多线程查找速度较快，但会耗费较多CPU资源. 不开启速度较慢，但节省CPU.
     * @param mode         1 表示开启快速扫描(略过只读内存)
     *                     0 表示所有内存类型全部扫描.
     *
     * @return String 返回搜索到的地址集合，地址格式如: "addr1|addr2|addr3…|addrn"
     * 例如："400050|423435|453430"
     *
     * @throws COMException
     * @example result = dm.FindDataEx(hwnd,"00000000-FFFFFFFF","00 01 23 45 67 86 ab ce f1",4,1,0)
     */
    public String FindDataEx(Long hwnd, String addr_range, String data, Integer step, Integer multi_thread, Integer mode) throws COMException {
        return dm.invokeN("FindDataEx", new Object[]{hwnd, addr_range, data, step, multi_thread, mode}).toString();
    }


    /**
     * 搜索指定的双精度浮点数,默认步长是1.如果要定制步长，请用FindDoubleEx
     *
     * @param hwnd             指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr_range       指定搜索的地址集合，字符串类型，这个地方可以是上次FindXXX的返回地址集合,可以进行二次搜索.(类似CE的再次扫描)
     *                         如果要进行地址范围搜索，那么这个值为的形如如下(类似于CE的新搜索)
     *                         "00400000-7FFFFFFF" "80000000-BFFFFFFF" "00000000-FFFFFFFF" 等.
     * @param double_value_min 搜索的双精度数值最小值
     * @param double_value_max 搜索的双精度数值最大值
     *                         最终搜索的数值大与等于double_value_min,并且小于等于double_value_max
     *
     * @return String 返回搜索到的地址集合，地址格式如: "addr1|addr2|addr3…|addrn"
     * 例如："400050|423435|453430"
     *
     * @throws COMException
     * @example result = dm.FindDouble(hwnd,"00000000-FFFFFFFF",0.5,1.0)
     */
    public String FindDouble(Long hwnd, String addr_range, Double double_value_min, Double double_value_max) throws COMException {
        return dm.invokeN("FindDouble", new Object[]{hwnd, addr_range, double_value_min, double_value_max}).toString();
    }


    /**
     * 搜索指定的双精度浮点数.(vip)
     *
     * @param hwnd             指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr_range       指定搜索的地址集合，字符串类型，这个地方可以是上次FindXXX的返回地址集合,可以进行二次搜索.(类似CE的再次扫描)
     *                         如果要进行地址范围搜索，那么这个值为的形如如下(类似于CE的新搜索)
     *                         "00400000-7FFFFFFF" "80000000-BFFFFFFF" "00000000-FFFFFFFF" 等.
     * @param double_value_min 搜索的双精度数值最小值
     * @param double_value_max 搜索的双精度数值最大值
     *                         最终搜索的数值大与等于double_value_min,并且小于等于double_value_max
     * @param step             搜索步长.
     * @param multi_thread     表示是否开启多线程查找.  0不开启，1开启.
     *                         开启多线程查找速度较快，但会耗费较多CPU资源. 不开启速度较慢，但节省CPU.
     * @param mode             1 表示开启快速扫描(略过只读内存)
     *                         0 表示所有内存类型全部扫描.
     *
     * @return String 返回搜索到的地址集合，地址格式如: "addr1|addr2|addr3…|addrn"
     * 例如："400050|423435|453430"
     *
     * @throws COMException
     * @example result = dm.FindDoubleEx(hwnd,"00000000-FFFFFFFF",0.5,1.0,8,1)
     */
    public String FindDoubleEx(Long hwnd, String addr_range, Double double_value_min, Double double_value_max, Integer step, Integer multi_thread, Integer mode) throws COMException {
        return dm.invokeN("FindDoubleEx", new Object[]{hwnd, addr_range, double_value_min, double_value_max, step, multi_thread, mode}).toString();
    }


    /**
     * 搜索指定的单精度浮点数,默认步长是1.如果要定制步长，请用FindFloatEx
     *
     * @param hwnd            指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr_range      指定搜索的地址集合，字符串类型，这个地方可以是上次FindXXX的返回地址集合,可以进行二次搜索.(类似CE的再次扫描)
     *                        如果要进行地址范围搜索，那么这个值为的形如如下(类似于CE的新搜索)
     *                        "00400000-7FFFFFFF" "80000000-BFFFFFFF" "00000000-FFFFFFFF" 等.
     * @param float_value_min 搜索的单精度数值最小值
     * @param float_value_max 搜索的单精度数值最大值
     *                        最终搜索的数值大与等于float_value_min,并且小于等于float_value_max
     *
     * @return String 返回搜索到的地址集合，地址格式如: "addr1|addr2|addr3…|addrn"
     * 例如："400050|423435|453430"
     *
     * @throws COMException
     * @example result = dm.FindFloat(hwnd,"00000000-FFFFFFFF",0.5,1.0)
     */
    public String FindFloat(Long hwnd, String addr_range, Float float_value_min, Float float_value_max) throws COMException {
        return dm.invokeN("FindFloat", new Object[]{hwnd, addr_range, float_value_min, float_value_max}).toString();
    }


    /**
     * 搜索指定的单精度浮点数.(vip)
     *
     * @param hwnd            指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr_range      指定搜索的地址集合，字符串类型，这个地方可以是上次FindXXX的返回地址集合,可以进行二次搜索.(类似CE的再次扫描)
     *                        如果要进行地址范围搜索，那么这个值为的形如如下(类似于CE的新搜索)
     *                        "00400000-7FFFFFFF" "80000000-BFFFFFFF" "00000000-FFFFFFFF" 等.
     * @param float_value_min 搜索的单精度数值最小值
     * @param float_value_max 搜索的单精度数值最大值
     *                        最终搜索的数值大与等于float_value_min,并且小于等于float_value_max
     * @param step            搜索步长.
     * @param multi_thread    表示是否开启多线程查找.  0不开启，1开启.
     *                        开启多线程查找速度较快，但会耗费较多CPU资源. 不开启速度较慢，但节省CPU.
     * @param mode            1 表示开启快速扫描(略过只读内存)
     *                        0 表示所有内存类型全部扫描.
     *
     * @return String 返回搜索到的地址集合，地址格式如: "addr1|addr2|addr3…|addrn"
     * 例如："400050|423435|453430"
     *
     * @throws COMException
     * @example result = dm.FindFloatEx(hwnd,"00000000-FFFFFFFF",0.5,1.0,4,1)
     */
    public String FindFloatEx(Long hwnd, String addr_range, Float float_value_min, Float float_value_max, Integer step, Integer multi_thread, Integer mode) throws COMException {
        return dm.invokeN("FindFloatEx", new Object[]{hwnd, addr_range, float_value_min, float_value_max, step, multi_thread, mode}).toString();
    }


    /**
     * 搜索指定的整数,默认步长是1.如果要定制步长，请用FindIntEx
     *
     * @param hwnd          指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr_range    指定搜索的地址集合，字符串类型，这个地方可以是上次FindXXX的返回地址集合,可以进行二次搜索.(类似CE的再次扫描)
     *                      如果要进行地址范围搜索，那么这个值为的形如如下(类似于CE的新搜索)
     *                      "00400000-7FFFFFFF" "80000000-BFFFFFFF" "00000000-FFFFFFFF" 等.
     * @param int_value_min 搜索的整数数值最小值
     * @param int_value_max 搜索的整数数值最大值
     *                      最终搜索的数值大与等于int_value_min,并且小于等于int_value_max
     * @param type          搜索的整数类型,取值如下
     *                      0 : 32位
     *                      1 : 16 位
     *                      2 : 8位
     *
     * @return String 返回搜索到的地址集合，地址格式如: "addr1|addr2|addr3…|addrn"
     * 例如："400050|423435|453430"
     *
     * @throws COMException
     * @example result = dm.FindInt(hwnd,"00000000-FFFFFFFF",300,300,0)
     */
    public String FindInt(Long hwnd, String addr_range, Integer int_value_min, Integer int_value_max, Integer type) throws COMException {
        return dm.invokeN("FindInt", new Object[]{hwnd, addr_range, int_value_min, int_value_max, type}).toString();
    }


    /**
     * 搜索指定的整数.(vip)
     *
     * @param hwnd          指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr_range    指定搜索的地址集合，字符串类型，这个地方可以是上次FindXXX的返回地址集合,可以进行二次搜索.(类似CE的再次扫描)
     *                      如果要进行地址范围搜索，那么这个值为的形如如下(类似于CE的新搜索)
     *                      "00400000-7FFFFFFF" "80000000-BFFFFFFF" "00000000-FFFFFFFF" 等.
     * @param int_value_min 搜索的整数数值最小值
     * @param int_value_max 搜索的整数数值最大值
     *                      最终搜索的数值大与等于int_value_min,并且小于等于int_value_max
     * @param type          搜索的整数类型,取值如下
     *                      0 : 32位
     *                      1 : 16 位
     *                      2 : 8位
     * @param step          搜索步长.
     * @param multi_thread  表示是否开启多线程查找.  0不开启，1开启.
     *                      开启多线程查找速度较快，但会耗费较多CPU资源. 不开启速度较慢，但节省CPU.
     * @param mode          1 表示开启快速扫描(略过只读内存)
     *                      0 表示所有内存类型全部扫描.
     *
     * @return String 返回搜索到的地址集合，地址格式如: "addr1|addr2|addr3…|addrn"
     * 例如："400050|423435|453430"
     *
     * @throws COMException
     * @example result = dm.FindIntEx(hwnd,"00000000-FFFFFFFF",300,300,0,2,1,0)
     */
    public String FindIntEx(Long hwnd, String addr_range, Integer int_value_min, Integer int_value_max, Integer type, Integer step, Integer multi_thread, Integer mode) throws COMException {
        return dm.invokeN("FindIntEx", new Object[]{hwnd, addr_range, int_value_min, int_value_max, type, step, multi_thread, mode}).toString();
    }


    /**
     * 搜索指定的字符串,默认步长是1.如果要定制步长，请用FindStringEx
     *
     * @param hwnd         指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr_range   指定搜索的地址集合，字符串类型，这个地方可以是上次FindXXX的返回地址集合,可以进行二次搜索.(类似CE的再次扫描)
     *                     如果要进行地址范围搜索，那么这个值为的形如如下(类似于CE的新搜索)
     *                     "00400000-7FFFFFFF" "80000000-BFFFFFFF" "00000000-FFFFFFFF" 等.
     * @param string_value 搜索的字符串
     * @param type         搜索的字符串类型,取值如下
     *                     0 : Ascii字符串
     *                     1 : Unicode字符串
     *
     * @return String 返回搜索到的地址集合，地址格式如: "addr1|addr2|addr3…|addrn"
     * 例如："400050|423435|453430"
     *
     * @throws COMException
     * @example result = dm.FindString(hwnd,"00000000-FFFFFFFF","哈哈哈哈",0)
     */
    public String FindString(Long hwnd, String addr_range, String string_value, Integer type) throws COMException {
        return dm.invokeN("FindString", new Object[]{hwnd, addr_range, string_value, type}).toString();
    }


    /**
     * 搜索指定的字符串.(vip)
     *
     * @param hwnd         指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr_range   指定搜索的地址集合，字符串类型，这个地方可以是上次FindXXX的返回地址集合,可以进行二次搜索.(类似CE的再次扫描)
     *                     如果要进行地址范围搜索，那么这个值为的形如如下(类似于CE的新搜索)
     *                     "00400000-7FFFFFFF" "80000000-BFFFFFFF" "00000000-FFFFFFFF" 等.
     * @param string_value 搜索的字符串
     * @param type         搜索的字符串类型,取值如下
     *                     0 : Ascii字符串
     *                     1 : Unicode字符串
     * @param step         搜索步长.
     * @param multi_thread 表示是否开启多线程查找.  0不开启，1开启.
     *                     开启多线程查找速度较快，但会耗费较多CPU资源. 不开启速度较慢，但节省CPU.
     * @param mode         1 表示开启快速扫描(略过只读内存)
     *                     0 表示所有内存类型全部扫描.
     *
     * @return String 返回搜索到的地址集合，地址格式如: "addr1|addr2|addr3…|addrn"
     * 例如："400050|423435|453430"
     *
     * @throws COMException
     * @example result = dm.FindStringEx(hwnd,"00000000-FFFFFFFF","哈哈哈哈",0,2,1,1)
     */
    public String FindStringEx(Long hwnd, String addr_range, String string_value, Integer type, Integer step, Integer multi_thread, Integer mode) throws COMException {
        return dm.invokeN("FindStringEx", new Object[]{hwnd, addr_range, string_value, type, step, multi_thread, mode}).toString();
    }


    /**
     * 把单精度浮点数转换成二进制形式.
     *
     * @param value 需要转化的单精度浮点数
     *
     * @return String 字符串形式表达的二进制数据. 可以用于WriteData FindData FindDataEx等接口.
     *
     * @throws COMException
     * @example float_data =  dm.FloatToData(1.24)
     * dm_ret = dm.FindData(hwnd,"00000000-7fffffff",float_data)
     */
    public String FloatToData(Float value) throws COMException {
        return dm.invokeN("FloatToData", new Object[]{value}).toString();
    }


    /**
     * 根据指定的窗口句柄，来获取对应窗口句柄进程下的指定模块的基址
     *
     * @param hwnd   指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param module 模块名
     *
     * @return String 模块的基址
     *
     * @throws COMException
     * @example base_addr = dm.GetModuleBaseAddr(hwnd,"gdi32.dll")
     */
    public Long GetModuleBaseAddr(Long hwnd, String module) throws COMException {
        return Long.parseLong(dm.invokeN("GetModuleBaseAddr", new Object[]{hwnd, module}).toString());
    }


    /**
     * 把整数转换成二进制形式.
     *
     * @param value 需要转化的整型数
     * @param type  0: 4字节整形数 (一般都选这个)
     *              1: 2字节整形数
     *              2: 1字节整形数
     *
     * @return String 字符串形式表达的二进制数据. 可以用于WriteData FindData FindDataEx等接口.
     *
     * @throws COMException
     * @example int_data =  dm.IntToData(&H12345678,0)
     * dm_ret = dm.FindData(hwnd,"00000000-7fffffff",int_data)
     */
    public String IntToData(Integer value, Integer type) throws COMException {
        return dm.invokeN("IntToData", new Object[]{value, type}).toString();
    }


    /**
     * 读取指定地址的二进制数据
     *
     * @param hwnd 指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 用字符串来描述地址，类似于CE的地址描述，数值必须是16进制,里面可以用[ ] + -这些符号来描述一个地址。+表示地址加，-表示地址减，模块名必须用<>符号来圈起来，例如：
     *             1. "4DA678" 最简单的方式，用绝对数值来表示地址
     *             2. "<360SE.exe>+DA678" 相对简单的方式，只是这里用模块名来决定模块基址，后面的是偏移
     *             3. "[4DA678]+3A" 用绝对数值加偏移，相当于一级指针
     *             4. "[<360SE.exe>+DA678]+3A" 用模块定基址的方式，也是一级指针
     *             5. "[[[<360SE.exe>+DA678]+3A]+5B]+8" 这个是一个三级指针
     *             总之熟悉CE的人 应该对这个地址描述都很熟悉,我就不多举例了
     * @param len  二进制数据的长度
     *
     * @return String 读取到的数值,以16进制表示的字符串 每个字节以空格相隔 比如"12 34 56 78 ab cd ef"
     *
     * @throws COMException
     * @example value = dm.ReadData(hwnd,"4DA678",10)
     */
    public String ReadData(Long hwnd, String addr, Integer len) throws COMException {
        return dm.invokeN("ReadData", new Object[]{hwnd, addr, len}).toString();
    }


    /**
     * 读取指定地址的双精度浮点数
     *
     * @param hwnd 指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 用字符串来描述地址，类似于CE的地址描述，数值必须是16进制,里面可以用[ ] + -这些符号来描述一个地址。+表示地址加，-表示地址减，模块名必须用<>符号来圈起来，例如：
     *             1. "4DA678" 最简单的方式，用绝对数值来表示地址
     *             2. "<360SE.exe>+DA678" 相对简单的方式，只是这里用模块名来决定模块基址，后面的是偏移
     *             3. "[4DA678]+3A" 用绝对数值加偏移，相当于一级指针
     *             4. "[<360SE.exe>+DA678]+3A" 用模块定基址的方式，也是一级指针
     *             5. "[[[<360SE.exe>+DA678]+3A]+5B]+8" 这个是一个三级指针
     *             总之熟悉CE的人 应该对这个地址描述都很熟悉,我就不多举例了
     *
     * @return Double 读取到的数值,注意这里无法判断读取是否成功
     *
     * @throws COMException
     * @example value = dm.ReadDouble(hwnd,"4DA678")
     */
    public Double ReadDouble(Long hwnd, String addr) throws COMException {
        return Double.parseDouble(dm.invokeN("ReadDouble", new Object[]{hwnd, addr}).toString());
    }


    /**
     * 读取指定地址的单精度浮点数
     *
     * @param hwnd 指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 用字符串来描述地址，类似于CE的地址描述，数值必须是16进制,里面可以用[ ] + -这些符号来描述一个地址。+表示地址加，-表示地址减，模块名必须用<>符号来圈起来，例如：
     *             1. "4DA678" 最简单的方式，用绝对数值来表示地址
     *             2. "<360SE.exe>+DA678" 相对简单的方式，只是这里用模块名来决定模块基址，后面的是偏移
     *             3. "[4DA678]+3A" 用绝对数值加偏移，相当于一级指针
     *             4. "[<360SE.exe>+DA678]+3A" 用模块定基址的方式，也是一级指针
     *             5. "[[[<360SE.exe>+DA678]+3A]+5B]+8" 这个是一个三级指针
     *             总之熟悉CE的人 应该对这个地址描述都很熟悉,我就不多举例了
     *
     * @return Float 读取到的数值,注意这里无法判断读取是否成功
     *
     * @throws COMException
     * @example value = dm.ReadFloat(hwnd,"4DA678")
     */
    public Float ReadFloat(Long hwnd, String addr) throws COMException {
        return Float.parseFloat(dm.invokeN("ReadFloat", new Object[]{hwnd, addr}).toString());
    }


    /**
     * 读取指定地址的整数数值，类型可以是8位，16位 或者 32位
     *
     * @param hwnd 指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 用字符串来描述地址，类似于CE的地址描述，数值必须是16进制,里面可以用[ ] + -这些符号来描述一个地址。+表示地址加，-表示地址减，模块名必须用<>符号来圈起来，例如：
     *             1. "4DA678" 最简单的方式，用绝对数值来表示地址
     *             2. "<360SE.exe>+DA678" 相对简单的方式，只是这里用模块名来决定模块基址，后面的是偏移
     *             3. "[4DA678]+3A" 用绝对数值加偏移，相当于一级指针
     *             4. "[<360SE.exe>+DA678]+3A" 用模块定基址的方式，也是一级指针
     *             5. "[[[<360SE.exe>+DA678]+3A]+5B]+8" 这个是一个三级指针
     *             总之熟悉CE的人 应该对这个地址描述都很熟悉,我就不多举例了
     * @param type 整数类型,取值如下
     *             0 : 32位
     *             1 : 16 位
     *             2 : 8位
     *
     * @return Integer 读取到的数值,注意这里无法判断读取是否成功
     *
     * @throws COMException
     * @example value = dm.ReadInt(hwnd,"4DA678",0)
     */
    public Integer ReadInt(Long hwnd, String addr, Integer type) throws COMException {
        return Integer.parseInt(dm.invokeN("ReadInt", new Object[]{hwnd, addr, type}).toString());
    }


    /**
     * 读取指定地址的字符串，可以是GBK字符串或者是Unicode字符串.(必须事先知道内存区的字符串编码方式)
     *
     * @param hwnd 指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 用字符串来描述地址，类似于CE的地址描述，数值必须是16进制,里面可以用[ ] + -这些符号来描述一个地址。+表示地址加，-表示地址减，模块名必须用<>符号来圈起来，例如：
     *             1. "4DA678" 最简单的方式，用绝对数值来表示地址
     *             2. "<360SE.exe>+DA678" 相对简单的方式，只是这里用模块名来决定模块基址，后面的是偏移
     *             3. "[4DA678]+3A" 用绝对数值加偏移，相当于一级指针
     *             4. "[<360SE.exe>+DA678]+3A" 用模块定基址的方式，也是一级指针
     *             5. "[[[<360SE.exe>+DA678]+3A]+5B]+8" 这个是一个三级指针
     *             总之熟悉CE的人 应该对这个地址描述都很熟悉,我就不多举例了
     * @param type 字符串类型,取值如下
     *             0 : GBK字符串
     *             1 : Unicode字符串
     * @param len  需要读取的字节数目.
     *
     * @return String 读取到的字符串,注意这里无法判断读取是否成功
     *
     * @throws COMException
     * @example value = dm.ReadString(hwnd,"4DA678",0,10)
     */
    public String ReadString(Long hwnd, String addr, Integer type, Integer len) throws COMException {
        return dm.invokeN("ReadString", new Object[]{hwnd, addr, type, len}).toString();
    }


    /**
     * 设置是否把所有内存查找接口的结果保存入指定文件.(vip)
     *
     * @param file 设置要保存的搜索结果文件名. 如果为空字符串表示取消此功能.
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example 开启
     * dm.SetPath("d:\test")
     * dm.SetMemoryFindResultToFile("result.dat")
     * 取消
     * dm.SetMemoryFindResultToFile("")
     */
    public Integer SetMemoryFindResultToFile(String file) throws COMException {
        return Integer.parseInt(dm.invokeN("SetMemoryFindResultToFile", new Object[]{file}).toString());
    }


    /**
     * 设置是否把所有内存接口函数中的窗口句柄当作进程ID,以支持直接以进程ID来使用内存接口.(vip)
     *
     * @param en 0 : 关闭
     *           1 : 开启
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm.SetMemoryHwndAsProcessId(1)
     */
    public Integer SetMemoryHwndAsProcessId(Integer en) throws COMException {
        return Integer.parseInt(dm.invokeN("SetMemoryHwndAsProcessId", new Object[]{en}).toString());
    }


    /**
     * 把字符串转换成二进制形式.
     *
     * @param value 需要转化的字符串
     * @param type  0: 返回Ascii表达的字符串
     *              1: 返回Unicode表达的字符串
     *
     * @return String 字符串形式表达的二进制数据. 可以用于WriteData FindData FindDataEx等接口.
     *
     * @throws COMException
     * @example string_data =  dm.StringToData("12345678",1)
     * dm_ret = dm.FindData(hwnd,"00000000-7fffffff",string_data)
     */
    public String StringToData(String value, Integer type) throws COMException {
        return dm.invokeN("StringToData", new Object[]{value, type}).toString();
    }


    /**
     * 对指定地址写入二进制数据
     *
     * @param hwnd 指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 用字符串来描述地址，类似于CE的地址描述，数值必须是16进制,里面可以用[ ] + -这些符号来描述一个地址。+表示地址加，-表示地址减，模块名必须用<>符号来圈起来，例如：
     *             1. "4DA678" 最简单的方式，用绝对数值来表示地址
     *             2. "<360SE.exe>+DA678" 相对简单的方式，只是这里用模块名来决定模块基址，后面的是偏移
     *             3. "[4DA678]+3A" 用绝对数值加偏移，相当于一级指针
     *             4. "[<360SE.exe>+DA678]+3A" 用模块定基址的方式，也是一级指针
     *             5. "[[[<360SE.exe>+DA678]+3A]+5B]+8" 这个是一个三级指针
     *             总之熟悉CE的人 应该对这个地址描述都很熟悉,我就不多举例了
     * @param data 二进制数据，以字符串形式描述，比如"12 34 56 78 90 ab cd"
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.WriteData(hwnd,"4DA678","12 34 56 78 90 ab cd")
     */
    public Integer WriteData(Long hwnd, String addr, String data) throws COMException {
        return Integer.parseInt(dm.invokeN("WriteData", new Object[]{hwnd, addr, data}).toString());
    }


    /**
     * 对指定地址写入双精度浮点数
     *
     * @param hwnd 指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 用字符串来描述地址，类似于CE的地址描述，数值必须是16进制,里面可以用[ ] + -这些符号来描述一个地址。+表示地址加，-表示地址减，模块名必须用<>符号来圈起来，例如：
     *             1. "4DA678" 最简单的方式，用绝对数值来表示地址
     *             2. "<360SE.exe>+DA678" 相对简单的方式，只是这里用模块名来决定模块基址，后面的是偏移
     *             3. "[4DA678]+3A" 用绝对数值加偏移，相当于一级指针
     *             4. "[<360SE.exe>+DA678]+3A" 用模块定基址的方式，也是一级指针
     *             5. "[[[<360SE.exe>+DA678]+3A]+5B]+8" 这个是一个三级指针
     *             总之熟悉CE的人 应该对这个地址描述都很熟悉,我就不多举例了
     * @param v    双精度浮点数
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.WriteDouble(hwnd,"4DA678",2.34)
     */
    public Integer WriteDouble(Long hwnd, String addr, Double v) throws COMException {
        return Integer.parseInt(dm.invokeN("WriteDouble", new Object[]{hwnd, addr, v}).toString());
    }


    /**
     * 对指定地址写入单精度浮点数
     *
     * @param hwnd 指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 用字符串来描述地址，类似于CE的地址描述，数值必须是16进制,里面可以用[ ] + -这些符号来描述一个地址。+表示地址加，-表示地址减，模块名必须用<>符号来圈起来，例如：
     *             1. "4DA678" 最简单的方式，用绝对数值来表示地址
     *             2. "<360SE.exe>+DA678" 相对简单的方式，只是这里用模块名来决定模块基址，后面的是偏移
     *             3. "[4DA678]+3A" 用绝对数值加偏移，相当于一级指针
     *             4. "[<360SE.exe>+DA678]+3A" 用模块定基址的方式，也是一级指针
     *             5. "[[[<360SE.exe>+DA678]+3A]+5B]+8" 这个是一个三级指针
     *             总之熟悉CE的人 应该对这个地址描述都很熟悉,我就不多举例了
     * @param v    单精度浮点数
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.WriteFloat(hwnd,"4DA678",2.34)
     */
    public Integer WriteFloat(Long hwnd, String addr, Float v) throws COMException {
        return Integer.parseInt(dm.invokeN("WriteFloat", new Object[]{hwnd, addr, v}).toString());
    }


    /**
     * 对指定地址写入整数数值，类型可以是8位，16位 或者 32位
     *
     * @param hwnd 指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 用字符串来描述地址，类似于CE的地址描述，数值必须是16进制,里面可以用[ ] + -这些符号来描述一个地址。+表示地址加，-表示地址减，模块名必须用<>符号来圈起来，例如：
     *             1. "4DA678" 最简单的方式，用绝对数值来表示地址
     *             2. "<360SE.exe>+DA678" 相对简单的方式，只是这里用模块名来决定模块基址，后面的是偏移
     *             3. "[4DA678]+3A" 用绝对数值加偏移，相当于一级指针
     *             4. "[<360SE.exe>+DA678]+3A" 用模块定基址的方式，也是一级指针
     *             5. "[[[<360SE.exe>+DA678]+3A]+5B]+8" 这个是一个三级指针
     *             总之熟悉CE的人 应该对这个地址描述都很熟悉,我就不多举例了
     * @param type 整数类型,取值如下
     *             0 : 32位
     *             1 : 16 位
     *             2 : 8位
     * @param v    整形数值
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.WriteInt(hwnd,"4DA678",0,100)
     */
    public Integer WriteInt(Long hwnd, String addr, Integer type, Integer v) throws COMException {
        return Integer.parseInt(dm.invokeN("WriteInt", new Object[]{hwnd, addr, type, v}).toString());
    }


    /**
     * 对指定地址写入字符串，可以是Ascii字符串或者是Unicode字符串
     *
     * @param hwnd 指定搜索的窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 用字符串来描述地址，类似于CE的地址描述，数值必须是16进制,里面可以用[ ] + -这些符号来描述一个地址。+表示地址加，-表示地址减，模块名必须用<>符号来圈起来，例如：
     *             1. "4DA678" 最简单的方式，用绝对数值来表示地址
     *             2. "<360SE.exe>+DA678" 相对简单的方式，只是这里用模块名来决定模块基址，后面的是偏移
     *             3. "[4DA678]+3A" 用绝对数值加偏移，相当于一级指针
     *             4. "[<360SE.exe>+DA678]+3A" 用模块定基址的方式，也是一级指针
     *             5. "[[[<360SE.exe>+DA678]+3A]+5B]+8" 这个是一个三级指针
     *             总之熟悉CE的人 应该对这个地址描述都很熟悉,我就不多举例了
     * @param type 字符串类型,取值如下
     *             0 : Ascii字符串
     *             1 : Unicode字符串
     * @param v    字符串
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example dm_ret = dm.WriteString(hwnd,"4DA678",0,"我是来测试的")
     */
    public Integer WriteString(Long hwnd, String addr, Integer type, String v) throws COMException {
        return Integer.parseInt(dm.invokeN("WriteString", new Object[]{hwnd, addr, type, v}).toString());
    }


}
