package com.github.imusk.dm.aide.javaelf;

import com.qiyou.javaelf.elf.GlobalSetting;
import com.qiyou.javaelf.operation.Picture;
import com.qiyou.javaelf.operation.PictureOperations;
import com.qiyou.javaelf.system.Elf;
import org.junit.Test;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-04 10:03:05
 * @classname: JavaElfTest
 * @description: javaelf.cn
 * @refer1: http://javaelf.cn/
 * @refer2: https://blog.csdn.net/qq_38830428/article/details/108098007
 */
public class JavaElfTest {


    @Test
    public void elf() {

        try {
            String regPath = "";
            String dmPath = "";
            GlobalSetting.copy_dlls();
            //全局只调用一次
            Elf.init(regPath, dmPath);
            Elf elf = new Elf();

            //打印版本 7.2043
            System.out.println(elf.Ver());

            // FindPicS
            Object[] params1 = new Object[]{0, 0, 1920, 1080, "test.png", "", 0.9, 0, 0, 0};
            String res1 = elf.execute(Picture.class, PictureOperations.FindPicS, params1).toString();

            Picture picture = new Picture(elf);
            Object[] params = new Object[]{0, 0, 1920, 1080, "test.png", "", 0.9, 0, 0, 0};
            String res = picture.FindPicS(params);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
