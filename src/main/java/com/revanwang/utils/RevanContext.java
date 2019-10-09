package com.revanwang.utils;

import com.opensymphony.xwork2.ActionContext;

import java.util.Set;

/**
 * session 上下文
 */
public class RevanContext {

    private static final String USER_IN_SESSION = "user_in_session";
    private static final String PERMISSION_IN_SESSION = "permission_in_session";


    /**
     * 设置数据到session中
     * @param obj
     */
    public static void revan_setCurrentSessionObject(Object obj) {
        ActionContext.getContext().getSession().put(USER_IN_SESSION,obj);
    }


    /**
     * 获取session中对象
     * @return
     */
    public static Object revan_getCurrentSession() {
        return ActionContext.getContext().getSession().get(USER_IN_SESSION);
    }


    /**
     * 设置用户权限到session中
     */
    public static void revan_setCurrentPermission(Set<String> permissionSet) {
        ActionContext.getContext().getSession().put(PERMISSION_IN_SESSION,permissionSet);
    }


    /**
     * @return session中的权限
     */
    public static Set<String> revan_getCurrentPermission() {
        return (Set<String>) ActionContext.getContext().getSession().get(PERMISSION_IN_SESSION);
    }

}
