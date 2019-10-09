package com.revanwang.wms.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.revanwang.utils.RevanPermissionUtils;
import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.Employee;

import java.lang.reflect.Method;
import java.util.Set;

public class SecurityInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        Employee employee = (Employee) actionInvocation.getInvocationContext().getSession().get("user_in_session");
        //1: 如果是超级管理员，则直接放行
        if (employee.isAdmin()) {
            return actionInvocation.invoke();
        }
        //2: 判断当前请求的Action方法上是否有RequiredPermission标签，如果没有（不需要权限）直接放行
        //获取当前执行的方法名称
        String methodName = actionInvocation.getProxy().getMethod();
        //通过方法名称获取对应的方法对象
        Method actionMethod = actionInvocation.getProxy().getAction().getClass().getDeclaredMethod(methodName);
        //获取该方法上的标签
        RequiredPermission rpAnnotation = actionMethod.getAnnotation(RequiredPermission.class);
        //没有标签，则放行
        if (rpAnnotation == null) {
            return actionInvocation.invoke();
        }
        //3: 获取当前请求Action方法的权限表达式
        String exp = RevanPermissionUtils.buildExpression(actionMethod);

        //4: 获取当前用户所有权限，如果拥有该权限则放行
        Set<String> permissionSet = (Set<String>) actionInvocation.getInvocationContext().getSession().get("permission_in_session");
        if (permissionSet.contains(exp)) {
            return actionInvocation.invoke();
        }
        System.out.println("SecurityInterceptor.intercept:==" + "nopermission");
        return "nopermission";
    }
}
