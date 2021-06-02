## Jacob调用自己写dll

> https://www.yht7.com/news/28687



1. 编写 dll

   ```c#
   using System;
   namespace testDll
   {
       public class Test
       {
           public string MyTest()
           {
               return "ok";
           }
           public string good() {
               return "GOOD";
           }
       }
   }
   ```

   

2. 这里面有个坑：就是必须创建两个函数，一个函数会报个错误：com.jacob.com.ComFailException: Can"t map name to dispid:MyTest

   

3. 生成解决方案

   

4. 注册dll，注册dll时候注意。

   - 去查看你系统里面 `C:/Windows/Microsoft.NET/Framework64` 版本最高的NET。用最高的版本注册。不然会报错：`RegAsm : error RA0000 : “D: estDll.dll”不是有效的 .NET 程序集，因此未能加载它。`

   - 注册时dll文件不要放到c盘会报错：RegAsm : error RA0000 : 无法定位输入程序集“C:/Program”或它的一个依赖项，放到其他盘就可以。这里注册无所谓你文件放哪里注册。

     ```shell
     cd C:/Windows/Microsoft.NET/Framework64/v4.0.30319
     regasm D:testDll.dll
     ```

     

5. 将编写好的dll文件放到jdk/bin里面。或者发布的时候放在jre/bin。或者tomcat/bin。按具体情况定。

   

6. 这样就跳过所有的坑了。然后就是jacob的配置了，这个是不多说，可以参考：[jacob安装配置完整版](https://my.oschina.net/u/877016/blog/143878)

   

7. Java调用的代码

   ```java
   public static void MyTest() {
       ActiveXComponent dotnetCom1 = null;
       try {
           dotnetCom1 = new ActiveXComponent("testDll.Test");
           //Variant rs=Dispatch.call(dotnetCom1, "MyTest", new Object[]{st,var,st1,pst,pnst});
           Variant rs=Dispatch.call(dotnetCom1, "MyTest");
           System.out.println(rs.toString());
       } catch (Exception var11) {
           var11.printStackTrace();
           throw new RuntimeException(var11.getMessage());
       } finally {
           if (dotnetCom1 != null) {
               dotnetCom1.safeRelease();
           }
       }
   }
   ```









