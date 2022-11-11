package com.l.dynamic.datasource.utils;

import java.lang.reflect.Field;

public class ClazzUtil {
    public static void testReflect(Object model) throws Exception {
        // 获取实体类的所有属性，返回Field数组
        Field[] field = model.getClass().getDeclaredFields();

        for (int i = 0; i < field.length; i++) {


            //关键。。。可访问私有变量
            field[i].setAccessible(true);
            //给属性设置
            field[i].set(model, field[i].getType().getConstructor(field[i].getType()).newInstance("kou"));


        }

    }
}
