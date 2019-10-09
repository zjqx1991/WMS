package com.revanwang.wms.web.action;

import com.revanwang.wms.service.IEmployeeService;
import lombok.Setter;

public class LoginAction extends BaseAction {

    @Setter
    private String username;

    @Setter
    private String password;

    @Setter
    private IEmployeeService employeeService;

    @Override
    public String execute() throws Exception {
        try {
            this.employeeService.login(this.username, this.password);
        }
        catch (Exception e) {
            super.addActionError(e.getMessage());
            return LOGIN;
        }
        return SUCCESS;
    }

}
