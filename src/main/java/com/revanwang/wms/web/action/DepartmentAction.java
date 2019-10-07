package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.revanwang.wms.domain.Department;
import com.revanwang.wms.service.IDepartmentService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DepartmentAction extends ActionSupport {

    private final String LIST = "list";

    @Setter
    private IDepartmentService departmentService;
    @Getter
    private Department department = new Department();

    @Override
    public String execute() throws Exception {
        System.out.println("DepartmentAction.execute");
        List<Department> departmentList = this.departmentService.getList();
        ActionContext.getContext().put("department", departmentList);
        return LIST;
    }


    @Override
    public String input() throws Exception {
        Long departId = this.department.getId();
        if (departId != null) {
            //Edit
            this.department = this.departmentService.get(departId);
            System.out.println("DepartmentAction.Edit:==" + this.department);
        } else {
            System.out.println("DepartmentAction.input:==" + this.department);
        }

        return INPUT;
    }


    /**
     * 保存和更新
     */
    public String saveOrUpdate() {
        Long departId = this.department.getId();
        if (departId == null) {
            //保存
            this.departmentService.save(this.department);
            System.out.println("DepartmentAction.save：" + this.department);
        } else {  //更新
            this.departmentService.update(this.department);
            System.out.println("DepartmentAction.Update：" + this.department);
        }
        return SUCCESS;
    }

    public String delete() {
        Long departId = this.department.getId();
        if (departId != null) {
            this.departmentService.delete(departId);
        }
        return SUCCESS;
    }
}
