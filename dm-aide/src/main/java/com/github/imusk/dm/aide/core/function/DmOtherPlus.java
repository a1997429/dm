package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-10 23:04
 * @classname: DmOtherPlus
 * @description: 杂项
 * @version: 7.2107
 */
public class DmOtherPlus extends DmOther {

    private final static Logger logger = LoggerFactory.getLogger(DmOtherPlus.class);

    private DmSoft dm;

    public DmOtherPlus(DmSoft dm) {
        super(dm);
        this.dm = dm;
    }


    /**
     * 检测是否可以进入临界区,如果可以返回1,否则返回0. 此函数如果返回1，则调用对象就会占用此互斥信号量,直到此对象调用LeaveCri,否则不会释放.注意:如果调用对象在释放时，会自动把本对象占用的互斥信号量释放.
     *
     * @return Integer 0:不可以. 1:已经进入临界区.
     *
     * @throws COMException
     */
    public Integer EnterCri() throws COMException {
        return Integer.parseInt(dm.invoke("EnterCri").toString());
    }


    /**
     * 执行指定的CMD指令,并返回cmd的输出结果.
     *
     * @param cmd         需要执行的CMD指令. 比如"dir"
     * @param current_dir 执行此cmd命令时,所在目录. 如果为空，表示使用当前目录. 比如""或者"c:"
     * @param time_out    超时设置,单位是毫秒. 0表示一直等待. 大于0表示等待指定的时间后强制结束,防止卡死.
     *
     * @return String cmd指令的执行结果.  返回空字符串表示执行失败.
     *
     * @throws COMException
     * @example 示例
     * // 查看adb的版本信息.
     * adb_version = dm.ExecuteCmd("adb.exe version","d:\dnplayer2",0)
     * adb_devices = dm.ExecuteCmd("adb.exe devices","d:\dnplayer2",0)
     * // 对127.0.0.1:5555这个device来查看下安装的应用
     * adb_device_1_apps = dm.ExecuteCmd("adb.exe -s 127.0.0.1:5555 shell pm list packages","d:\dnplayer2",0)
     * // 安装apk
     * dm.ExecuteCmd("adb.exe -s 127.0.0.1:5555 install -r d:\xxx.apk","d:\dnplayer2",0)
     * // 卸载某apk
     * dm.ExecuteCmd("adb.exe -s 127.0.0.1:5555 uninstall com.qihoo360.mobilesafe","d:\dnplayer2",0)
     */
    public String ExecuteCmd(String cmd, String current_dir, Integer time_out) throws COMException {
        return dm.invokeN("ExecuteCmd", new Object[]{cmd, current_dir, time_out}).toString();
    }


    /**
     * 初始化临界区,必须在脚本开头调用一次.这个函数是强制把插件内的互斥信号量归0,无论调用对象是否拥有此信号量.
     *
     * @return Integer 0:失败. 1:成功.
     *
     * @throws COMException
     */
    public Integer InitCri() throws COMException {
        return Integer.parseInt(dm.invoke("InitCri").toString());
    }


    /**
     * 和EnterCri对应,离开临界区。此函数是释放调用对象占用的互斥信号量. 注意，只有调用对象占有了互斥信号量，此函数才会有作用. 否则没有任何作用. 如果调用对象在释放时，会自动把本对象占用的互斥信号量释放.
     *
     * @return Integer 0:失败. 1:成功.
     *
     * @throws COMException
     */
    public Integer LeaveCri() throws COMException {
        return Integer.parseInt(dm.invoke("LeaveCri").toString());
    }


    /**
     * 强制降低对象的引用计数。此接口为高级接口，一般使用在高级语言，比如E vc等.
     *
     * @return Integer 0:失败. 1:成功.
     *
     * @throws COMException
     */
    public Integer ReleaseRef() throws COMException {
        return Integer.parseInt(dm.invoke("ReleaseRef").toString());
    }


    /**
     * 设置当前对象的退出线程标记，之后除了调用此接口的线程之外，调用此对象的任何接口的线程会被强制退出.
     * 此接口为高级接口，一般用在高级语言,比如e vc等.
     *
     * @param enable 1为开启标记,0为关闭标记
     *
     * @return Integer 0:失败. 1:成功.
     *
     * @throws COMException
     */
    public Integer SetExitThread(Integer enable) throws COMException {
        return Integer.parseInt(dm.invokeN("SetExitThread", new Object[]{enable}).toString());
    }


}
