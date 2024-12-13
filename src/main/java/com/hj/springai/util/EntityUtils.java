package com.hj.springai.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @FileName EntityUtils
 * @Description
 * @Author fazhu
 * @date 2024-12-12
 **/
@Component
public class EntityUtils {

    //判断对象是否有数据为空
    public static boolean YorN_null(Object obj) {
        if (obj == null) {
            return false;
        }
        // 获取对象的类
        Class<?> clazz = obj.getClass();

        // 获取类中的所有字段
        Field[] fields = clazz.getDeclaredFields();

        // 遍历字段
        for (Field field : fields) {
            // 设置字段可访问（如果需要访问私有字段）
            field.setAccessible(true);
            try {
                // 获取字段的值
                Object value = field.get(obj);
                System.out.println(value);
                if (value == "" || value == null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                // 可以选择抛出运行时异常，让调用者处理
                throw new RuntimeException("无法访问字段", e);
            }
        }
        return true;
    }
}
