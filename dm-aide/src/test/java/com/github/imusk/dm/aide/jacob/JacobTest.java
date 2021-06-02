package com.github.imusk.dm.aide.jacob;

import com.github.imusk.dm.aide.utils.ResourcesUtil;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-02 09:33:28
 * @classname: JacobTest
 * @description: JACOB 是个调用 COM 组件的插件
 * @download: https://sourceforge.net/projects/jacob-project/
 * @github: https://github.com/freemansoft/jacob-project
 * @remark: 引入 jacob 依赖后，还需要将 jacob-x.yz-x**.dll 拷贝到对应 jdk 安装目录 jdk/jre/bin 的下面
 * @refer1: 自己编译 dll 并通过 jacob 调用：https://www.yht7.com/news/28687
 * @weakness: 需要提前注册/拷贝 jacob 的 dll 到系统对应位置
 */
public class JacobTest {

    private final static Logger logger = LoggerFactory.getLogger(JacobTest.class);

    @Test
    public void dispatch() {

        ResourcesUtil.setLibPath();

//        ActiveXComponent excel = new ActiveXComponent("Excel.Application");
//        ActiveXComponent world = new ActiveXComponent("Word.Application");

        try {

//            System.out.println("当前Excel版本：" + excel.getProperty("Version"));
//            System.out.println("当前World版本：" + world.getProperty("Version"));

            // https://www.jb51.net/article/18957.htm
            ActiveXComponent internetExplorer = new ActiveXComponent("InternetExplorer.Application");
            boolean busy = internetExplorer.getProperty("Busy").getBoolean();
            System.out.println("IE 当前状态是否处于导航或下载状态：" + busy);
            System.out.println("IE 程序名：" + internetExplorer.getProperty("Name").getString());
            System.out.println("IE 当前是否可见：" + internetExplorer.getProperty("Visible").getBoolean());

//            ActiveXComponent kernel32 = new ActiveXComponent("kernel32");
//            int getSystemTime = Dispatch.call(kernel32, "GetSystemTime").getInt();
//            int getCurrentThreadId = Dispatch.call(kernel32, "GetCurrentThreadId").getInt();
//            System.out.println(getSystemTime);

//            ActiveXComponent myDll = new ActiveXComponent("MyDllExample");
//            int myAdd = Dispatch.call(myDll, "add", 1, 2).getInt();
//            System.out.println(myAdd);

//            // 大漠插件
//            ActiveXComponent dm = new ActiveXComponent("dm.dmsoft");
//            logger.info("大漠版本号：{}", dm.invoke("Ver").getString());
//
//            // 注册大漠，1-成功
//            int dmRegResult = Dispatch.call(dm, "Reg", "xiaoxin6140e2b75fd4044c253e4b3adc85cdb5e311", "xintuo").getInt();
//
//            // 查找[记事本]程序
//            int notepadHandle = Dispatch.call(dm, "FindWindow", "", "记事本").getInt();
//
//            // 绑定窗口
//            int res = Dispatch.call(dm, "BindWindowEx", notepadHandle, "normal", "normal", "normal", "dx.public.active.api|dx.public.active.message", 0).getInt();
//            res = Dispatch.call(dm, "GetBindWindow").getInt();
//            System.out.println(res);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            excel.invoke("Quit", new Variant[]{});
//            world.invoke("Quit", new Variant[]{});
        }


    }

    /**
     * 使用Jacob调用windows的Sapi实现文本转语音文件
     */
    @Test
    public void sapi() {
        ActiveXComponent ax = null;

        try {

            ax = new ActiveXComponent("Sapi.SpVoice");

            Dispatch spVoice = ax.getObject();

            ax = new ActiveXComponent("Sapi.SpFileStream");

            Dispatch spFileStream = ax.getObject();

            ax = new ActiveXComponent("Sapi.SpAudioFormat");

            Dispatch spAudioFormat = ax.getObject();

            //设置音频流格式
            Dispatch.put(spAudioFormat, "Type", new Variant(22));

            //设置文件输出流格式
            Dispatch.putRef(spFileStream, "Format", spAudioFormat);

            //调用输出 文件流打开方法，创建一个.wav文件
            Dispatch.call(spFileStream, "Open", new Variant("D:\\test.wav"), new Variant(3), new Variant(true));

            //设置声音对象的音频输出流为输出文件对象
            Dispatch.putRef(spVoice, "AudioOutputStream", spFileStream);

            //设置音量 0到100
            Dispatch.put(spVoice, "Volume", new Variant(100));

            //设置朗读速度
            Dispatch.put(spVoice, "Rate", new Variant(-2));

            //开始朗读
            Dispatch.call(spVoice, "Speak", new Variant("张三，李四"));

            //关闭输出文件
            Dispatch.call(spFileStream, "Close");

            Dispatch.putRef(spVoice, "AudioOutputStream", null);

            spAudioFormat.safeRelease();

            spFileStream.safeRelease();

            spVoice.safeRelease();

            ax.safeRelease();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


}
