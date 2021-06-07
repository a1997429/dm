package com.github.imusk.dm.aide.core.function;

import com.github.imusk.dm.aide.core.DmSoft;
import org.jawin.COMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Musk
 * @email: muskcool@protonmail.com
 * @datetime: 2021-06-07 08:31:24
 * @classname: DmFaq
 * @description: 答题
 */
public class DmFaq {

    private final static Logger logger = LoggerFactory.getLogger(DmFaq.class);

    private DmSoft dm;

    public DmFaq(DmSoft dm) {
        this.dm = dm;
    }


    /**
     * 可以把上次FaqPost的发送取消,接着下一次FaqPost
     *
     * @return Integer 0: 失败 1: 成功
     *
     * @throws COMException
     * @example 示例：
     * 截取这个范围内,3秒动画,图像质量为中等50,动画帧率间隔为100ms
     * handle = dm.FaqCapture(50,50,300,400,50,100,3000)
     * 调用FaqPost异步发送
     * dm_ret = dm.FaqPost("192.168.1.100:12345",handle,1,3 * 60 * 1000)
     * 取消发送
     * dm.FaqCancel()
     */
    public Integer FaqCancel() throws COMException {
        return Integer.parseInt(dm.invoke("FaqCancel").toString());
    }


    /**
     * 截取指定范围内的动画或者图像,并返回此句柄.
     *
     * @param x1      左上角X坐标
     * @param y1      左上角Y坐标
     * @param x2      右下角X坐标
     * @param y2      右下角Y坐标
     * @param quality 图像或动画品质,或者叫压缩率,此值越大图像质量越好 取值范围（1-100）
     * @param delay   截取动画时用,表示相隔两帧间的时间间隔,单位毫秒 （如果只是截取静态图像,这个参数必须是0）
     * @param time    表示总共截取多久的动画,单位毫秒 （如果只是截取静态图像,这个参数必须是0）
     *
     * @return Long 图像或者动画句柄
     *
     * @throws COMException
     * @example 截取这个范围内, 3秒动画, 图像质量为中等50, 动画帧率间隔为100ms
     * handle = dm.FaqCapture(intX - 50,intY - 232,intX+272,intY-12,50,100,3000)
     */
    public Long FaqCapture(Integer x1, Integer y1, Integer x2, Integer y2, Integer quality, Integer delay, Integer time) throws COMException {
        return Long.parseLong(dm.invokeN("FaqCapture", new Object[]{x1, y1, x2, y2, quality, delay, time}).toString());
    }


    /**
     * 截取指定图片中的图像,并返回此句柄.(vip)
     *
     * @param x1      左上角X坐标
     * @param y1      左上角Y坐标
     * @param x2      右下角X坐标
     * @param y2      右下角Y坐标
     * @param file    图片文件名,图像格式基本都支持.
     * @param quality 图像或动画品质,或者叫压缩率,此值越大图像质量越好 取值范围（1-100）
     *
     * @return Long 图像或者动画句柄
     *
     * @throws COMException
     * @example handle = dm.FaqCaptureFromFile(0,0,2000,2000,"c:\test.bmp",50)
     */
    public Long FaqCaptureFromFile(Integer x1, Integer y1, Integer x2, Integer y2, String file, Integer quality) throws COMException {
        return Long.parseLong(dm.invokeN("FaqCaptureFromFile", new Object[]{x1, y1, x2, y2, file, quality}).toString());
    }


    /**
     * 获取由FaqPost发送后，由服务器返回的答案.
     *
     * @return String 调用失败: "Error:错误描述", 调用成功: "OK:答案"
     *
     * @throws COMException
     */
    public String FaqFetch() throws COMException {
        return dm.invoke("FaqFetch").toString();
    }


    /**
     * 获取句柄所对应的数据包的大小,单位是字节
     *
     * @param handle 由FaqCapture返回的句柄
     *
     * @return Long 数据包大小,一般用于判断数据大小,选择合适的压缩比率.
     *
     * @throws COMException
     * @example 截取这个范围内, 3秒动画, 图像质量为中等50, 动画帧率间隔为100ms
     * handle = dm.FaqCapture(intX - 50,intY - 232,intX+272,intY-12,50,100,3000)
     * packet_size = dm.FaqGetSize(handle)
     */
    public Long FaqGetSize(Long handle) throws COMException {
        return Long.parseLong(dm.invokeN("FaqGetSize", new Object[]{handle}).toString());
    }


    /**
     * 发送指定的图像句柄到指定的服务器,并立即返回(异步操作).
     *
     * @param server       服务器地址以及端口,格式为(ip:port),例如 "192.168.1.100:12345"
     * @param handle       由FaqCapture获取到的句柄
     * @param request_type 0 : 要求获取坐标
     *                     1 : 要求获取选项,比如(ABCDE)
     *                     2 : 要求获取文字答案
     *                     3 : 要求获取两个坐标.此功能要求答题器必须是v15之后的版本.(vip)
     * @param time_out     表示等待多久,单位是毫秒
     *
     * @return Integer 0 : 失败，一般情况下是由于上个FaqPost还没有处理完毕(服务器还没返回). 1 : 成功
     *
     * @throws COMException
     * @example 截取这个范围内, 3秒动画, 图像质量为中等50, 动画帧率间隔为100ms
     * handle = dm.FaqCapture(50,50,300,400,50,100,3000)
     * 调用FaqPost异步发送
     * dm_ret = dm.FaqPost("192.168.1.100:12345",handle,1,3 * 60 * 1000)
     */
    public Integer FaqPost(String server, Long handle, Integer request_type, Integer time_out) throws COMException {
        return Integer.parseInt(dm.invokeN("FaqPost", new Object[]{server, handle, request_type, time_out}).toString());
    }


    /**
     * 发送指定的图像句柄到指定的服务器,并等待返回结果(同步等待).
     *
     * @param server       服务器地址以及端口,格式为(ip:port),例如 "192.168.1.100:12345"，多个地址可以用"|"符号连接。比如"192.168.1.100:12345|192.168.1.101:12345"。
     * @param handle       由FaqCapture获取到的句柄
     * @param request_type 0 : 要求获取坐标
     *                     1 : 要求获取选项,比如(ABCDE)
     *                     2 : 要求获取文字答案
     *                     3 : 要求获取两个坐标.此功能要求答题器必须是v15之后的版本.（vip）
     * @param time_out     表示等待多久,单位是毫秒
     *
     * @return Integer 0 : 失败，一般情况下是由于上个FaqPost还没有处理完毕(服务器还没返回). 1 : 成功
     *
     * @throws COMException
     * @example 截取这个范围内, 3秒动画, 图像质量为中等50, 动画帧率间隔为100ms
     * handle = dm.FaqCapture(20,20,100,100,50,100,3000)
     * 等待3分钟,答案要求是选项
     * result = dm.FaqSend("192.168.1.100:12345|192.168.1.101:12345",handle,1,3 * 60 * 1000)
     */
    public Integer FaqSend(String server, Long handle, Integer request_type, Integer time_out) throws COMException {
        return Integer.parseInt(dm.invokeN("FaqSend", new Object[]{server, handle, request_type, time_out}).toString());
    }


}
