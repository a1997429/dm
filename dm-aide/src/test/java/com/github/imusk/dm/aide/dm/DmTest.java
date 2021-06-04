package com.github.imusk.dm.aide.dm;

import com.github.imusk.dm.aide.utils.ResourcesUtil;
import org.jawin.DispatchPtr;
import org.jawin.FuncPtr;
import org.jawin.ReturnFlags;
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

}
