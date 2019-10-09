package com.revanwang.wms.web.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.revanwang.wms.domain.Employee;

/**
 * 登录拦截器
 */
public class CheckLoginInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Employee emp = (Employee) actionInvocation.getInvocationContext().getSession().get("user_in_session");
        if (null == emp) {
            return Action.LOGIN;
        }
        return actionInvocation.invoke();
    }
}
