package by.jd2.jdbc.util;

import by.jd2.jdbc.annotation.MyColumn;

import java.lang.reflect.Field;

public class ReflectBean {


    public static String getFieldValue(String s, Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
            String d = f.getDeclaredAnnotation(MyColumn.class).value();
            if (d.equals(s)) {
                try {
                    f.setAccessible(true);
                    return String.valueOf(f.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public static void setFieldValue(String s, Object obj,Object s2){
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
            String d = f.getDeclaredAnnotation(MyColumn.class).value();
            if (d.equals(s)) {
                try {
                    f.setAccessible(true);
                    f.set(obj,s2);
                    f.setAccessible(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
