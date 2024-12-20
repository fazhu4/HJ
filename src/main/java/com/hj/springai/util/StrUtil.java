package com.hj.springai.util;

import org.springframework.stereotype.Component;

/**
 * @FileName StrReplaceUtil
 * @Description
 * @Author fazhu
 * @date 2024-12-20
 **/
@Component
public class StrUtil {

    /**
     * 替换字符串中指定位置的字符
     * @param str 字符串
     * @param index 要替换的字符位置
     * @param str2 替换的字符
     * @return 删除后的字符串
     */
    public static String strUpdate(String str,int index,char str2) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(index,str2);
        return sb.toString();
    }
}
