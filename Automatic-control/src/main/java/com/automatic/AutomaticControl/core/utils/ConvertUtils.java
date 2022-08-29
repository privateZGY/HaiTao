package com.automatic.AutomaticControl.core.utils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version v1.0
 * @ProjectName: micro-questions-answers
 * @ClassName: ConvertUtils
 * @Description: 转换工具类
 * @Author: xiefan
 * @Date: 12/9/2021 8:53 AM
 */
public class ConvertUtils {
    /**
     * 获取类的所有属性，包括父类
     * 获取所有属性的作用是判断这个javaBean里面有没有我们自定义的@Dict这个注解
     *
     * @param object
     * @return
     */
    public static Field[] getAllFields(Object object) {
        Class<?> clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }
}
