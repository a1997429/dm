package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-08 08:39:19
 * @classname: DmFile
 * @description: 文件
 */
public class DmFile {

    private final static Logger logger = LoggerFactory.getLogger(DmFile.class);

    private DmSoft dm;

    public DmFile(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 拷贝文件.
     *
     * @param src_file 原始文件名
     * @param dst_file 目标文件名.
     * @param over     0 : 如果dst_file文件存在则不覆盖返回.
     *                 1 : 如果dst_file文件存在则覆盖.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * dm.CopyFile("123.txt", "456.txt", 1)
     */
    public Integer CopyFile(String src_file, String dst_file, Integer over) throws COMException {
        return Integer.parseInt(dm.invokeN("CopyFile", new Object[]{src_file, dst_file, over}).toString());
    }


    /**
     * 创建指定目录.
     *
     * @param folder 目录名
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * dm.CreateFolder("c:\123\456\789")
     */
    public Integer CreateFolder(String folder) throws COMException {
        return Integer.parseInt(dm.invokeN("CreateFolder", new Object[]{folder}).toString());
    }


    /**
     * 解密指定的文件.(vip)
     *
     * @param file 文件名.
     * @param pwd  密码.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * dm.SetPath("c:\test_game")
     * dm.DecodeFile("1.bmp","1234")
     */
    public Integer DecodeFile(String file, String pwd) throws COMException {
        return Integer.parseInt(dm.invokeN("DecodeFile", new Object[]{file, pwd}).toString());
    }


    /**
     * 删除文件.
     *
     * @param file 文件名
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * dm.DeleteFile("123.txt")
     */
    public Integer DeleteFile(String file) throws COMException {
        return Integer.parseInt(dm.invokeN("DeleteFile", new Object[]{file}).toString());
    }


    /**
     * 删除指定目录.
     *
     * @param folder 目录名
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * dm.DeleteFile("123.txt")
     */
    public Integer DeleteFolder(String folder) throws COMException {
        return Integer.parseInt(dm.invokeN("DeleteFolder", new Object[]{folder}).toString());
    }


    /**
     * 删除指定的ini小节.
     *
     * @param section 小节名
     * @param key     变量名. 如果这个变量为空串，则删除整个section小节.
     * @param file    ini文件名.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * dm.DeleteIni("Global","var1" ,"c:\test_game\cfg.ini")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * dm.DeleteIni("Global","" ,"cfg.ini")
     */
    public Integer DeleteIni(String section, String key, String file) throws COMException {
        return Integer.parseInt(dm.invokeN("DeleteIni", new Object[]{section, key, file}).toString());
    }


    /**
     * 删除指定的ini小节.支持加密文件(vip)
     *
     * @param section 小节名
     * @param key     变量名. 如果这个变量为空串，则删除整个section小节.
     * @param file    ini文件名.
     * @param pwd     密码.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * dm.DeleteIniPwd("Global","var1" ,"c:\test_game\cfg.ini", "1234")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * dm.DeleteIniPwd("Global","" ,"cfg.ini", "1234")
     */
    public Integer DeleteIniPwd(String section, String key, String file, String pwd) throws COMException {
        return Integer.parseInt(dm.invokeN("DeleteIniPwd", new Object[]{section, key, file, pwd}).toString());
    }


    /**
     * 从internet上下载一个文件.
     *
     * @param url       下载的url地址.
     * @param save_file 要保存的文件名.
     * @param timeout   连接超时时间，单位是毫秒.
     *
     * @return Integer 1 : 成功 -1 : 网络连接失败 -2 : 写入文件失败
     *
     * @throws COMException
     * @example 示例
     * dm.DownloadFile("http://www.sohu.com","d:\sohu.html",3000)
     */
    public Integer DownloadFile(String url, String save_file, Integer timeout) throws COMException {
        return Integer.parseInt(dm.invokeN("DownloadFile", new Object[]{url, save_file, timeout}).toString());
    }


    /**
     * 加密指定的文件.(vip)
     *
     * @param file 文件名.
     * @param pwd  密码.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * dm.EncodeFile("c:\test_game\cfg.ini","1234")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * dm.EncodeFile("1.bmp","1234")
     */
    public Integer EncodeFile(String file, String pwd) throws COMException {
        return Integer.parseInt(dm.invokeN("EncodeFile", new Object[]{file, pwd}).toString());
    }


    /**
     * 获取指定的文件长度.
     *
     * @param file 文件名
     *
     * @return Long 文件长度(字节数)
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * ret = dm.GetFileLength("c:\123.txt")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * ret = dm.GetFileLength("123.txt")
     */
    public Long GetFileLength(String file) throws COMException {
        return Long.parseLong(dm.invokeN("GetFileLength", new Object[]{file}).toString());
    }


    /**
     * 判断指定文件是否存在.
     *
     * @param file 文件名
     *
     * @return Integer 0 : 不存在 1 : 存在
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * ret = dm.IsFileExist("c:\123.txt")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * ret = dm.IsFileExist("123.txt")
     */
    public Integer IsFileExist(String file) throws COMException {
        return Integer.parseInt(dm.invokeN("IsFileExist", new Object[]{file}).toString());
    }


    /**
     * 移动文件.
     *
     * @param src_file 原始文件名
     * @param dst_file 目标文件名.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * dm.MoveFile("c:\123.txt","d:\456.txt")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * dm.MoveFile("123.txt","456.txt")
     */
    public Integer MoveFile(String src_file, String dst_file) throws COMException {
        return Integer.parseInt(dm.invokeN("MoveFile", new Object[]{src_file, dst_file}).toString());
    }


    /**
     * 从指定的文件读取内容.
     *
     * @param file 文件
     *
     * @return String 读入的文件内容
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * ret = dm.ReadFile("c:\123.txt")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * ret = dm.ReadFile("123.txt")
     */
    public String ReadFile(String file) throws COMException {
        return dm.invokeN("ReadFile", new Object[]{file}).toString();
    }


    /**
     * 从Ini中读取指定信息.
     *
     * @param section 小节名
     * @param key     变量名.
     * @param file    ini文件名.
     *
     * @return String 字符串形式表达的读取到的内容
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * Text = dm.ReadIni("Global","var1","c:\test_game\cfg.ini")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * Text = dm.ReadIni("Global","var1","cfg.ini")
     */
    public String ReadIni(String section, String key, String file) throws COMException {
        return dm.invokeN("ReadIni", new Object[]{section, key, file}).toString();
    }


    /**
     * 从Ini中读取指定信息.
     *
     * @param section 小节名
     * @param key     变量名.
     * @param file    ini文件名.
     *
     * @return String 字符串形式表达的读取到的内容
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * Text = dm.ReadIniPwd("Global","var1","c:\test_game\cfg.ini","1234")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * Text = dm.ReadIniPwd("Global","var1","cfg.ini","1234")
     */
    public String ReadIniPwd(String section, String key, String file, String pwd) throws COMException {
        return dm.invokeN("ReadIniPwd", new Object[]{section, key, file, pwd}).toString();
    }


    /**
     * 弹出选择文件夹对话框，并返回选择的文件夹.
     *
     * @return String 选择的文件夹全路径
     *
     * @throws COMException
     * @example dm.SelectDirectory()
     */
    public String SelectDirectory() throws COMException {
        return dm.invoke("SelectDirectory").toString();
    }


    /**
     * 弹出选择文件对话框，并返回选择的文件.
     *
     * @return String 选择的文件全路径
     *
     * @throws COMException
     * @example dm.SelectFile()
     */
    public String SelectFile() throws COMException {
        return dm.invoke("SelectFile").toString();
    }


    /**
     * 向指定文件追加字符串.
     *
     * @param file    文件
     * @param content 写入的字符串.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * dm.WriteFile("c:\123.txt","哈哈哈")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * dm.WriteFile("123.txt","哈哈哈")
     */
    public Integer WriteFile(String file, String content) throws COMException {
        return Integer.parseInt(dm.invokeN("WriteFile", new Object[]{file, content}).toString());
    }


    /**
     * 向指定的Ini写入信息.
     *
     * @param section 小节名
     * @param key     变量名.
     * @param value   变量内容
     * @param file    ini文件名.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * dm.WriteIni("Global","var1","123","c:\test_game\cfg.ini")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * dm.WriteIni("Global","var1","123","cfg.ini")
     */
    public Integer WriteIni(String section, String key, String value, String file) throws COMException {
        return Integer.parseInt(dm.invokeN("WriteIni", new Object[]{section, key, value, file}).toString());
    }


    /**
     * 向指定的Ini写入信息.支持加密文件.(vip)
     *
     * @param section 小节名
     * @param key     变量名.
     * @param value   变量内容
     * @param file    ini文件名.
     * @param pwd     密码.
     *
     * @return Integer 0 : 失败 1 : 成功
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * dm.WriteIniPwd("Global","var1","123","c:\test_game\cfg.ini", "1234")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * dm.WriteIniPwd("Global","var1","123","cfg.ini", "1234")
     */
    public Integer WriteIniPwd(String section, String key, String value, String file, String pwd) throws COMException {
        return Integer.parseInt(dm.invokeN("WriteIniPwd", new Object[]{section, key, value, file, pwd}).toString());
    }


}
