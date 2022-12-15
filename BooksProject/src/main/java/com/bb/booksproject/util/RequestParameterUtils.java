package com.bb.booksproject.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;

public class RequestParameterUtils {

    /**
     * 通过反射获取表单的数据到pojo对象中去
     * @param req
     * @param cls
     * @return
     * @param <T>
     * @throws Exception
     */
    public static  <T> T  getRequestParameterForReflect(HttpServletRequest req, Class<T> cls) throws Exception{
        T t = cls.newInstance();
        Map<String, String[]> parameterMap = req.getParameterMap();
        Field[] declaredFields = cls.getDeclaredFields();
        if(declaredFields != null && declaredFields.length > 0){
            for (Field declaredField : declaredFields) {
                String[] values = parameterMap.get(declaredField.getName());
                if(values==null || values.length == 0){
                    continue;
                }
                //如果表单中的值为空，也不用处理
                if (values[0] == null || "".equals(values[0])){
                    continue;
                }

                if(declaredField.getType() == String[].class){
                    //判断是否是数组
                    declaredField.setAccessible(true);//放开访问权限
                    try {
                        declaredField.set(t,values);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    declaredField.setAccessible(false);
                    continue;
                }
                if(declaredField.getType() == Integer.class){
                    declaredField.setAccessible(true);//放开访问权限
                    try {
                        declaredField.set(t,Integer.parseInt(values[0]));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    declaredField.setAccessible(false);
                    continue;
                }
                //普通的数据类型
                //判断是否是数组
                declaredField.setAccessible(true);//放开访问权限
                try {
                    declaredField.set(t,values[0]);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                declaredField.setAccessible(false);
                continue;
            }
        }
        return t;
    }
}
