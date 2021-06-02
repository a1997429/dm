
1. pom.xml 添加依赖

   ```xml
   <dependency>
       <groupId>com.jacob</groupId>
       <artifactId>jacob</artifactId>
       <version>1.20</version>
       <scope>system</scope>
       <systemPath>${basedir}/src/main/resources/jacob-1.20/jacob.jar</systemPath>
   </dependency>
   ```

2. 把 `jacob-x.yz-x86.dll` 和 `jacob-x.yz-x64.dll` 拷贝到 `jdk` 的安装目录 `jre\bin` 下面，例如：

   ```html
   C:\Program Files (x86)\Java\jdk1.8.0_291\jre\bin
   ```

3. 亦可对应 dll 到系统的环境目录下，如：`C:\WINDOWS\system32` 和 `C:\Windows\SysWOW64`

4. 如果是使用 `tomcat` 部署项目的，建议拷贝对应 jar 和 dll 到 `tomcat\bin` 目录下



