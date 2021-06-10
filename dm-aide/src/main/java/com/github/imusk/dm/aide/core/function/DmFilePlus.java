package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-10 22:04
 * @classname: DmFilePlus
 * @description: 文件
 * @version: 7.2107
 */
public class DmFilePlus extends DmFile {

    private final static Logger logger = LoggerFactory.getLogger(DmFilePlus.class);

    private DmSoft dm;

    public DmFilePlus(DmSoft dm) {
        super(dm);
        this.dm = dm;
    }


    /**
     * 根据指定的ini文件以及section,枚举此section中所有的key名
     *
     * @param section 小节名. (不可为空)
     * @param file    ini文件名.
     *
     * @return String 每个key用"|"来连接，如果没有key，则返回空字符串. 比如"aaa|bbb|ccc"
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * dm_ret = dm.EnumIniKey("aaa","c:\test_game\cfg.ini")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * dm_ret = dm.EnumIniKey("aaa","cfg.ini")
     */
    public String EnumIniKey(String section, String file) throws COMException {
        return dm.invokeN("EnumIniKey", new Object[]{section, file}).toString();
    }


    /**
     * 根据指定的ini文件以及section,枚举此section中所有的key名.可支持加密文件
     *
     * @param section 小节名. (不可为空)
     * @param file    ini文件名.
     * @param pwd     密码
     *
     * @return String 每个key用"|"来连接，如果没有key，则返回空字符串. 比如"aaa|bbb|ccc"
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * dm_ret = dm.EnumIniKey("aaa","c:\test_game\cfg.ini","123")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * dm_ret = dm.EnumIniKey("aaa","cfg.ini","123")
     */
    public String EnumIniKeyPwd(String section, String file, String pwd) throws COMException {
        return dm.invokeN("EnumIniKeyPwd", new Object[]{section, file, pwd}).toString();
    }


    /**
     * 根据指定的ini文件,枚举此ini中所有的Section(小节名)
     *
     * @param file ini文件名.
     *
     * @return String 每个key用"|"来连接，如果没有key，则返回空字符串. 比如"aaa|bbb|ccc"
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * dm_ret = dm.EnumIniSection("c:\test_game\cfg.ini")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * dm_ret = dm.EnumIniSection("cfg.ini")
     */
    public String EnumIniSection(String file) throws COMException {
        return dm.invokeN("EnumIniSection", new Object[]{file}).toString();
    }


    /**
     * 根据指定的ini文件,枚举此ini中所有的Section(小节名) 可支持加密文件
     *
     * @param file ini文件名.
     * @param pwd  密码
     *
     * @return String 每个key用"|"来连接，如果没有key，则返回空字符串. 比如"aaa|bbb|ccc"
     *
     * @throws COMException
     * @example 示例
     * // 绝对路径
     * dm_ret = dm.EnumIniSectionPwd("c:\test_game\cfg.ini","123")
     * // 相对路径
     * dm.SetPath("c:\test_game")
     * dm_ret = dm.EnumIniSectionPwd("cfg.ini","123")
     */
    public String EnumIniSectionPwd(String file, String pwd) throws COMException {
        return dm.invokeN("EnumIniSectionPwd", new Object[]{file, pwd}).toString();
    }


    /**
     * 获取指定文件或目录的真实路径
     *
     * @param path 路径名,可以是文件路径，也可以是目录. 这里必须是全路径
     *
     * @return String 真实路径,如果失败,返回空字符串
     *
     * @throws COMException
     * @example ret = dm.GetRealPath("c:\11\22\3.txt")
     */
    public String GetRealPath(String path) throws COMException {
        return dm.invokeN("GetRealPath", new Object[]{path}).toString();
    }


    /**
     * 判断指定目录是否存在.
     *
     * @param folder 目录名
     *
     * @return Integer 0 : 不存在 1 : 存在
     *
     * @throws COMException
     * @example ret = dm.IsFolderExist("c:\test_game")
     */
    public Integer IsFolderExist(String folder) throws COMException {
        return Integer.parseInt(dm.invokeN("IsFolderExist", new Object[]{folder}).toString());
    }


}
