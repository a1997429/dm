package com.github.imusk.dm.aide.dm;

import com.github.imusk.dm.aide.core.DmReg;
import com.github.imusk.dm.aide.core.DmSoft;
import com.github.imusk.dm.aide.core.function.DmBasic;
import com.github.imusk.dm.aide.core.function.DmWindow;
import com.github.imusk.dm.aide.utils.ResourcesUtil;
import org.jawin.DispatchPtr;
import org.jawin.FuncPtr;
import org.jawin.ReturnFlags;
import org.jawin.Variant;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-04 10:47:10
 * @classname: DmTest
 * @description: 大漠插件
 */
public class DmTest {

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
        dmWindow.GetClientSize(notepadHandle, w,h);
        System.out.println("窗口 W = " + w.getRef() + "，H = " + h.getRef());

        System.out.println("顶层活动窗口句柄：" + dmWindow.GetForegroundFocus());
        System.out.println("获取给定坐标的窗口句柄：" + dmWindow.GetPointWindow(100,100));
        System.out.println("获取窗口的标题：" + dmWindow.GetWindowTitle(notepadHandle));
        System.out.println("设置窗口的标题：" + dmWindow.SetWindowText(notepadHandle, "记事本"));
    }


}
