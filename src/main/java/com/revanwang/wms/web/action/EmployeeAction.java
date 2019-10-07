package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.revanwang.wms.domain.Department;
import com.revanwang.wms.domain.Employee;
import com.revanwang.wms.service.IDepartmentService;
import com.revanwang.wms.service.IEmployeeService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class EmployeeAction extends ActionSupport {

    private final String LIST = "list";

    @Setter
    private IEmployeeService employeeService;

    @Setter
    private IDepartmentService departmentService;

    @Getter
    private Employee employee = new Employee();


    @Override
    public String execute() throws Exception {
        List<Employee> employees = this.employeeService.getList();
        ActionContext.getContext().put("employees", employees);
        return LIST;
    }


    @Override
    public String input() throws Exception {
        List<Department> departments = this.departmentService.getList();
        ActionContext.getContext().put("depts", departments);

        Long employeeId = this.employee.getId();
        if (employeeId != null) {
            this.employee = this.employeeService.get(employeeId);
            System.out.println("EmployeeAction.input: " + this.employee);
        }
        return INPUT;
    }


    public String saveOrUpdate() {
        Long id = this.employee.getId();
        if (id == null) {
            //新增
            this.employeeService.save(this.employee);
        } else {
            //编辑
            this.employeeService.update(this.employee);
        }
        System.out.println("EmployeeAction.saveOrUpdate: + " + this.employee);
        return SUCCESS;
    }


    public String delete() {
        Long employeeId = this.employee.getId();
        if (employeeId != null) {
            this.employeeService.delete(employeeId);
        }
        return SUCCESS;
    }

}
