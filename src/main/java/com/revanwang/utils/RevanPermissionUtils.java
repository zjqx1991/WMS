package com.revanwang.utils;

import java.lang.reflect.Method;

public class RevanPermissionUtils {

    /**
     * 获取method所在类的全限定类名
     * @param method
     * @return
     */
    public static String buildExpression(Method method) {
        StringBuilder sb = new StringBuilder(50);
        sb.append(method.getDeclaringClass().getName());
        sb.append(":");
        sb.append(method.getName());
        return sb.toString();
    }

}
