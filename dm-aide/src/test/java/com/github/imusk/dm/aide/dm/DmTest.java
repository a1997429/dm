package com.github.imusk.dm.aide.dm;

import com.github.imusk.dm.aide.core.DmReg;
import com.github.imusk.dm.aide.core.DmSoft;
import com.github.imusk.dm.aide.core.function.*;
import com.github.imusk.dm.aide.utils.ResourcesUtil;
import org.jawin.DispatchPtr;
import org.jawin.FuncPtr;
import org.jawin.ReturnFlags;
import org.jawin.Variant;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-04 10:47:10
 * @classname: DmTest
 * @description: 大漠插件
 */
public class DmTest {

    private final static Logger logger = LoggerFactory.getLogger(DmTest.class);

    @Before
    public void loadDll() {
        ResourcesUtil.setLibPath();
    }

    @Test
    public void getVer() {

        try {
            // 大漠插件
            String dmPath = ClassLoader.class.getResource("/lib/dm.dll").getPath();
            File dmFile = new File(dmPath);

            // 大漠免注册Dll
            String dmRegPath = ClassLoader.class.getResource("/lib/DmReg.dll").getPath();
            File dmRegFile = new File(dmRegPath);

            // 调用 DmReg.dll 免注册大漠插件
            FuncPtr dmRegFuncPtr = new FuncPtr(dmRegFile.getPath(), "SetDllPathW");
            int dmRegResult = dmRegFuncPtr.invoke_I(dmFile.getPath(), ReturnFlags.CHECK_NONE);
            System.out.println("注册大漠插件：" + dmRegResult);

            // 注册大漠插件后关闭DmReg.dll
            dmRegFuncPtr.close();

            // 实例 dm.dll 对象
            DispatchPtr dmDispatchPtr = new DispatchPtr("dm.dmsoft");
            Object ver = dmDispatchPtr.invoke("Ver");
            System.out.println("大漠版本号：" + ver);

            Object getBasePath = dmDispatchPtr.invoke("GetBasePath");
            System.out.println("获取注册在系统中的dm.dll路径：" + getBasePath);

            // 查找窗口
            Object[] params = new Object[]{"Notepad", "无标题 - 记事本"};
            Long findWindowHandle = Long.parseLong(dmDispatchPtr.invokeN("FindWindow", params).toString());
            System.out.println("记事本窗口句柄：" + findWindowHandle);

            // 绑定后台窗口(高级用户)：BindWindowEx
            params = new Object[]{findWindowHandle, "normal", "normal", "normal", "dx.public.disable.window.position", 0};
            Long bindWindowExResult = Long.parseLong(dmDispatchPtr.invokeN("BindWindowEx", params).toString());
            System.out.println("绑定窗口结果：" + bindWindowExResult);

            params = new Object[]{findWindowHandle};
            Long isBindResult = Long.parseLong(dmDispatchPtr.invokeN("IsBind", params).toString());
            System.out.println("是否已绑定窗口结果：" + isBindResult);

            params = new Object[]{};
            Long unBindWindowExResult = Long.parseLong(dmDispatchPtr.invokeN("UnBindWindow", params).toString());
            System.out.println("解绑窗口结果：" + unBindWindowExResult);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void dmSoft() throws Exception {

        // 大漠插件
        String dmPath = ClassLoader.class.getResource("/lib/dm.dll").getPath();
        File dmFile = new File(dmPath);

        // 大漠免注册Dll
        String dmRegPath = ClassLoader.class.getResource("/lib/DmReg.dll").getPath();
        File dmRegFile = new File(dmRegPath);

        // DmReg.setLibPath(null);
        DmReg.register(dmRegFile.getPath(), dmFile.getPath());
        DmReg.close();

        DmSoft dm = DmSoft.getInstance();

        System.out.println("大漠插件GuidToken：" + dm.getGuidToken());
        System.out.println("版本号：" + dm.execute("Ver").toString());


        // 查找窗口
        Object[] params = new Object[]{"Notepad", "无标题 - 记事本"};
        params = new Object[]{"", "无标题 - 记事本"};
        Long notepadHandle = Long.parseLong(dm.execute("FindWindow", params).toString());
        System.out.println("记事本窗口句柄：" + notepadHandle);

        Integer result = null;


        System.out.println(" ------------- 基础设置 ------------- ");

        DmBasic dmBasic = new DmBasic(dm);
        System.out.println("dm.dll释放路径：" + dmBasic.GetBasePath());
        System.out.println("大漠创建数量：" + dmBasic.GetDmCount());
        System.out.println("当前大漠对象的ID值：" + dmBasic.GetID());
        System.out.println("获取插件命令的最后错误：" + dmBasic.GetLastError());
        System.out.println("全局路径：" + dmBasic.GetPath());
        System.out.println("版本号：" + dmBasic.Ver());

        result = dmBasic.GetLastError();
        System.out.println("最后的错误：" + result);

        // 设置不弹出错误信息
//        dmBasic.SetShowErrorMsg(0L);


        System.out.println(" ------------- Foobar ------------- ");

//        DmFoobar dmFoobar = new DmFoobar(dm);
//        result = dmFoobar.CreateFoobarRect(notepadHandle, 10, 10, 50, 50);
//        System.out.println("创建自定义窗口句柄：" + result);


        System.out.println(" ------------- 窗口 ------------- ");

        DmWindow dmWindow = new DmWindow(dm);

//        Variant.ByrefHolder x = new Variant.ByrefHolder(0);
//        Variant.ByrefHolder y = new Variant.ByrefHolder(0);
//        result = dmWindow.ClientToScreen(notepadHandle, x, y);
//        System.out.println("把窗口坐标转换为屏幕坐标：X = " + x + "，Y = " + y);

        String handles = dmWindow.EnumWindow(0L, "记事本", "", 1 + 4 + 8 + 16);
        System.out.println("查找结果：" + handles);

        Variant.ByrefHolder x1 = new Variant.ByrefHolder(0);
        Variant.ByrefHolder y1 = new Variant.ByrefHolder(0);
        Variant.ByrefHolder x2 = new Variant.ByrefHolder(0);
        Variant.ByrefHolder y2 = new Variant.ByrefHolder(0);
        dmWindow.GetClientRect(notepadHandle, x1, y1, x2, y2);

        System.out.println("窗口位于坐标位置x1：" + x1.getRef());
        System.out.println("窗口位于坐标位置y1：" + x2.getRef());
        System.out.println("窗口位于坐标位置x2：" + y1.getRef());
        System.out.println("窗口位于坐标位置y2：" + y2.getRef());
        System.out.println("X1 = " + Integer.parseInt(x1.getRef().toString()));

        Variant.ByrefHolder w = new Variant.ByrefHolder(0);
        Variant.ByrefHolder h = new Variant.ByrefHolder(0);
        dmWindow.GetClientSize(notepadHandle, w, h);
        System.out.println("窗口 W = " + w.getRef() + "，H = " + h.getRef());

        System.out.println("顶层活动窗口句柄：" + dmWindow.GetForegroundFocus());
        System.out.println("获取给定坐标的窗口句柄：" + dmWindow.GetPointWindow(100, 100));
        System.out.println("获取窗口的标题：" + dmWindow.GetWindowTitle(notepadHandle));
        // System.out.println("设置窗口的标题：" + dmWindow.SetWindowText(notepadHandle, "记事本"));


        System.out.println(" ------------- 后台设置 ------------- ");
        DmBackground dmBackground = new DmBackground(dm);

        result = dmBackground.BindWindow(notepadHandle, "normal", "windows", "windows", 0);
        // result = dmBackground.BindWindowEx(notepadHandle, "normal", "normal", "normal", "dx.public.active.api|dx.public.active.message", 0);
        System.out.println("绑定记事本窗口结果：" + result);

        result = dmBackground.IsBind(notepadHandle);
        System.out.println("判断是否已绑定：" + result);


        dmBackground.UnBindWindow();


    }

    @Test
    public void dmKeyboardMouse() throws Exception {

        // 大漠插件
        String dmPath = ClassLoader.class.getResource("/lib/dm.dll").getPath();
        File dmFile = new File(dmPath);

        // 大漠免注册Dll
        String dmRegPath = ClassLoader.class.getResource("/lib/DmReg.dll").getPath();
        File dmRegFile = new File(dmRegPath);

        // DmReg.setLibPath(null);
        DmReg.register(dmRegFile.getPath(), dmFile.getPath());
        DmReg.close();

        DmSoft dm = DmSoft.getInstance();

        DmWindow dmWindow = new DmWindow(dm);

        Long notepadHandle = dmWindow.FindWindow("", "记事本");

        logger.info("记事本窗口句柄：{}", notepadHandle);

        DmMouse dmMouse = new DmMouse(dm);

        Variant.ByrefHolder x = new Variant.ByrefHolder(0);
        Variant.ByrefHolder y = new Variant.ByrefHolder(0);

        dmMouse.GetCursorPos(x, y);
        logger.info("当前鼠标位置：X = {} , Y = {}", x.getRef(), y.getRef());

        String cursorShape = dmMouse.GetCursorShape();
        logger.info("鼠标特征码：{}", cursorShape);

        String cursorSpot = dmMouse.GetCursorSpot();
        logger.info("鼠标热点位置：{}", cursorSpot);

        logger.info("鼠标滚轮向上滚：{}", dmMouse.WheelUp());

        // logger.info("鼠标右键点击：{}", dmMouse.RightClick());

        DmKeyboard dmKeyboard = new DmKeyboard(dm);

        logger.info("键盘输入虚拟键码：{}", dmKeyboard.KeyPress(13));
        logger.info("键盘输入虚拟键码：{}", dmKeyboard.KeyPressChar("enter"));
        logger.info("键盘输入字符：{}", dmKeyboard.KeyPressStr("Hello Dm", 20));

    }

    @Test
    public void dmRAM() throws Exception {

        // 大漠插件
        String dmPath = ClassLoader.class.getResource("/lib/dm.dll").getPath();
        File dmFile = new File(dmPath);

        // 大漠免注册Dll
        String dmRegPath = ClassLoader.class.getResource("/lib/DmReg.dll").getPath();
        File dmRegFile = new File(dmRegPath);

        // DmReg.setLibPath(null);
        DmReg.register(dmRegFile.getPath(), dmFile.getPath());
        DmReg.close();

        DmSoft dm = DmSoft.getInstance();

        DmWindow dmWindow = new DmWindow(dm);

        Long notepadHandle = dmWindow.FindWindow("", "记事本");

        logger.info("记事本窗口句柄：{}", notepadHandle);

        DmRam dmRam = new DmRam(dm);

        String doubleToData = dmRam.DoubleToData(1.24);
        logger.info("双精度的二进制数据：{}", doubleToData);

        String findString = dmRam.FindString(notepadHandle, "00000000-FFFFFFFF", "数据",1);
        logger.info("查找结果：{}", findString);

        String findStringEx = dmRam.FindStringEx(notepadHandle, "00000000-FFFFFFFF", "数据", 0, 2, 1, 1);
        logger.info("查找结果：{}", findStringEx);

        String stringToData = dmRam.StringToData("数据", 1);
        String findData = dmRam.FindData(notepadHandle, "00000000-7fffffff", stringToData);
        logger.info("查找结果：{}", findData);


    }


}
