package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.revanwang.wms.domain.Department;
import com.revanwang.wms.domain.Employee;
import com.revanwang.wms.service.IDepartmentService;
import com.revanwang.wms.service.IEmployeeService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class EmployeeAction extends ActionSupport implements Preparable {

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
        System.out.println("EmployeeAction.saveOrUpdate: + " + this.employee);
        Long id = this.employee.getId();
        if (id == null) {
            //新增
            this.employeeService.save(this.employee);
        } else {
            //编辑
            this.employeeService.update(this.employee);
        }
        return SUCCESS;
    }


    public String delete() {
        Long employeeId = this.employee.getId();
        if (employeeId != null) {
            this.employeeService.delete(employeeId);
        }
        return SUCCESS;
    }

    /**
     * 拦截所有的方法
     * @throws Exception
     */
    @Override
    public void prepare() throws Exception {
        System.out.println("EmployeeAction.prepare");
    }

    /**
     * 拦截 saveOrUpdate方法
     * @throws Exception
     */
    public void prepareSaveOrUpdate() throws Exception {
        System.out.println("EmployeeAction.prepareSaveOrUpdate: " + this.employee);
        Long empId = this.employee.getId();
        if (empId != null) {
            //获取数据库数据
            this.employee = this.employeeService.get(empId);
        }
    }

}
