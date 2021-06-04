package com.github.imusk.dm.aide.jawin;

import com.github.imusk.dm.aide.utils.ResourcesUtil;
import org.jawin.COMException;
import org.jawin.DispatchPtr;
import org.jawin.FuncPtr;
import org.jawin.ReturnFlags;
import org.jawin.donated.win32.*;
import org.jawin.io.LittleEndianOutputStream;
import org.jawin.io.NakedByteStream;
import org.jawin.marshal.StructConverter;
import org.jawin.util.HexFormatter;
import org.jawin.win32.Ole32;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-02 14:49:10
 * @classname: JawinTest
 * @description: Jawin
 * @website: http://jawinproject.sourceforge.net/
 * @download: https://sourceforge.net/projects/jawinproject/
 * @refer1: https://blog.csdn.net/u011563755/article/details/48466607
 */
public class JawinTest {

    @Before
    public void setUp() {

        ResourcesUtil.setLibPath();

    }

    @Test
    public void MyDllExample() {

        try {

            String path = ClassLoader.class.getResource("/lib/MyDllExample.dll").getPath();
            File file = new File(path);

            FuncPtr funPoint = new FuncPtr(file.getPath(), "ReturnParam");

            //创建数据流
            NakedByteStream nbs = new NakedByteStream();
            LittleEndianOutputStream leos = new LittleEndianOutputStream(nbs);

            //在数据流中传入参数（[in]类型参数这里指int ReturnParam(int a,int& b,int& c)中的a）
            leos.writeInt(6);

            //IAA:I:L4n4n4字符串是jawin规定的一种伪指令具体书写格式按照文档 instruction_docs.html 中介绍
            //第一个I表示[in] long parameters 也就是ReturnParam的参数a，第二个和第三个AA表示[out] long* parameters也就是ReturnParam的参数b c，第一个:后表示返回值的类型，
            //第二个:后表示数据类型所占位数a是int型所以是long类型4字节，&b &c都是int型指针也占4字节，
            byte[] result = funPoint.invoke("IAA:I:L4n4n4", 12/*返回值所占字节数*/, nbs, null, ReturnFlags.CHECK_FALSE);

            //得到的返回值result中前四位表示的是返回值6+3
            System.out.println(StructConverter.bytesIntoInt(result, 0));
            //参数返回值6+1
            System.out.println(result[4]);
            //参数返回值6+2
            System.out.println(result[8]);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void MyDllExampleAdd() {

        try {
            String path = ClassLoader.class.getResource("/lib/MyDllExample.dll").getPath();
            File file = new File(path);

            // FuncPtr funPoint = new FuncPtr(file.getPath(), "Add");
            FuncPtr funPoint = new FuncPtr(file.getPath(), "Max");

            // 创建数据流
            NakedByteStream nbs = new NakedByteStream();
            LittleEndianOutputStream leos = new LittleEndianOutputStream(nbs);

            // 在数据流中传入参数（[in]类型参数这里指int ReturnParam(int a,int& b,int& c)中的a）
            leos.writeInt(666);
            // 在数据流中传入参数（[in]类型参数这里指int ReturnParam(int a,int& b,int& c)中的b）
            leos.writeInt(4444);

            // II:I:L4L4 字符串是jawin规定的一种伪指令具体书写格式按照文档 instruction_docs.html 中介绍
            // 第一个I 和 第二个I  表示[in] long parameters 也就是ReturnParam的参数a 和 参数b，
            // 第一个: 后表示返回值的类型，也是I（[in] long parameters）
            // 第二个: 后表示数据类型所占位数a是int型所以是long类型4字节，b也是int类型也占4字节，
            byte[] result = funPoint.invoke("II:I:L4L4", 12/*返回值所占字节数*/, nbs, null, ReturnFlags.CHECK_FALSE);

            //得到的返回值result中前四位表示的是返回值
            System.out.println(StructConverter.bytesIntoInt(result, 0));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void HelloDll() {

        FuncPtr msgBox = null;
        try {

            msgBox = new FuncPtr("USER32.DLL", "MessageBoxW");
            msgBox.invoke_I(0, "Hello From a DLL", "From Jawin", 0, ReturnFlags.CHECK_FALSE);

        } catch (COMException e) {
            e.printStackTrace();
        } finally {
            if (msgBox != null) {
                try {
                    msgBox.close();
                } catch (COMException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @Test
    public void HelloDllGeneric() {

        FuncPtr msgBox = null;
        try {
            msgBox = new FuncPtr("USER32.DLL", "MessageBoxW");

            NakedByteStream nbs = new NakedByteStream();
            LittleEndianOutputStream leos = new LittleEndianOutputStream(nbs);
            leos.writeInt(0);
            leos.writeStringUnicode("Generic Hello From a DLL");
            leos.writeStringUnicode("From Jawin");
            leos.writeInt(0);
            msgBox.invoke("IGGI:I:", 16, nbs, null, ReturnFlags.CHECK_FALSE);
        } catch (COMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (msgBox != null) {
                try {
                    msgBox.close();
                } catch (COMException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @Test
    public void HelloDllStub() {

        try {
            User32.MessageBoxW("Hello from a DLL stub", "Jawin");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void CreatePpt() {

        try {
            Ole32.CoInitialize();
            DispatchPtr app = new DispatchPtr("PowerPoint.Application");
            app.put("Visible", true);
            DispatchPtr preses = (DispatchPtr)app.get("Presentations");
            DispatchPtr pres = (DispatchPtr) preses.invoke("add", new Integer(-1));
            DispatchPtr slides = (DispatchPtr)pres.get("Slides");
            DispatchPtr slide = (DispatchPtr) slides.invoke("Add", new Integer(1), new Integer(2));
            DispatchPtr shapes = (DispatchPtr)slide.get("Shapes");
            DispatchPtr shape = (DispatchPtr) shapes.invoke("Item", new Integer(1));
            DispatchPtr frame = (DispatchPtr)shape.get("TextFrame");
            DispatchPtr range = (DispatchPtr)frame.get("TextRange");
            range.put("Text", "Use Jawin to call COM objects");
            Ole32.CoUninitialize();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void CreateWord() {

        try {
            Ole32.CoInitialize();
            DispatchPtr app = new DispatchPtr("Word.Application");
            app.put("Visible", true);
            Ole32.CoUninitialize();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void ExcelProp() {

        try {

            Ole32.CoInitialize();

            DispatchPtr app = new DispatchPtr("Excel.Application");
            app.put("Visible", -1);
            DispatchPtr books = (DispatchPtr)app.get("Workbooks");
            DispatchPtr book =
                    (DispatchPtr) books.invoke(
                            "Open",
                             "D:\\demo.xls");
            DispatchPtr sheets = (DispatchPtr)book.get("Worksheets");
            DispatchPtr sheet = (DispatchPtr)sheets.get("Item", new Integer(1));

            DispatchPtr x =
                    (DispatchPtr) sheet.getN("Range", new Object[] { "A2", "C3" });
            x.invoke("Select");

            DispatchPtr range = (DispatchPtr)sheet.get("Range", "A1:A3");

            System.out.println("Count=" + range.get("Count"));

            DispatchPtr range2 = (DispatchPtr)range.get("Item", new Integer(2));
            System.out.println("value=" + range2.get("Formula"));
            range.putN("Item", new Object[] { new Integer(1)}, 2, range2);

            Ole32.CoUninitialize();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static boolean exit = false;

    public static void demoConsoleEventHandler() {

        try {

            System.out.println(
                    "\nThis demo installs a console event handler that catches "
                            + "CTRL-C events and gives you the chance to shutdown the app");
            Console.SetConsoleCtrlHandler(new HandlerRoutine() {
                public boolean call(long dwCtrlType) {
                    if (CTRL_C_EVENT == dwCtrlType) {
                        System.out.println("Your pressed CTRL-C.  Type 'EXIT' to exit");
                        BufferedReader br =
                                new BufferedReader(new InputStreamReader(System.in));
                        try {
                            String choice = br.readLine();
                            System.out.println("You typed " + choice);
                            if ("EXIT".equalsIgnoreCase(choice)) {
                                exit = true;
                                return false;
                            }
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                            return false;
                        }
                    }
                    return true;
                }
            }, true);

            int countdown = 5;
            while (countdown-- > 0 && !exit) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ie) {
                }
                System.out.println("Still going " + countdown);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void DemoConsoleEventHandler() {
        JawinTest.demoConsoleEventHandler();
    }

    @Test
    public void DemoSecurityAPIs() {

        try {
            int handle = W32Process.GetCurrentProcess();
            System.out.println("Process handle is " + handle);
            int token = Security.OpenProcessToken(handle, SecurityConstants.TOKEN_READ);
            System.out.println("Process token is " + token);
            byte[] tokinfo = Security.GetTokenInformation(token, SecurityConstants.TokenUser);
            System.out.println("TokenUser raw data is...");
            System.out.println(HexFormatter.convertBytesToString(tokinfo, 16, true));
            byte[] sid = Security.GetTokenUserSid(token);
            System.out.println("User SID is...");
            System.out.println(HexFormatter.convertBytesToString(sid, 16, true));
            byte[] sididauth = Security.GetSidIdentifierAuthority(sid);
            System.out.println("SID_IDENTIFIER_AUTHORITY raw data is...");
            System.out.println(HexFormatter.convertBytesToString(sididauth, 16, true));
            System.out.println("Your human-readable sid is " + Security.SidToString(sid));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
