package util;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class OrderSystemUtil {
    //需要实现一个读取body的功能
    //需要先把整个Body读取出来然后才能解析JSON
    public static String readBody(HttpServletRequest request) throws UnsupportedEncodingException {
    //先去获取body的长度(单位为字节)
        int length = request.getContentLength();
        byte[] buffer = new byte[length]; //保存读出来的数据
        try(InputStream inputStream = request.getInputStream()) {
           inputStream.read(buffer,0,length);
        } catch(IOException e) {
            e.getStackTrace();
        }
        //构造String时要指定字符串编码形式 否则可能出现字符乱码问题
        return new String(buffer,"utf-8");
    }
}
