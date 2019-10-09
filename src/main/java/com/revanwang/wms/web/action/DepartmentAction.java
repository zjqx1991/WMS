package com.revanwang.wms.web.action;

import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.Department;
import com.revanwang.wms.query.DepartmentQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IDepartmentService;
import lombok.Getter;
import lombok.Setter;

public class DepartmentAction extends BaseAction {

    @Setter
    private IDepartmentService departmentService;
    @Getter
    private Department department = new Department();

    @Getter
    private DepartmentQueryObject qo = new DepartmentQueryObject();

    @Override
    @RequiredPermission("部门列表")
    public String execute() throws Exception {
        System.out.println("DepartmentAction.execute");
        QueryResultObject resultObject = this.departmentService.query(qo);
        ActionContextPut("pageResult", resultObject);
        return LIST;
    }


    @Override
    @RequiredPermission("部门编辑")
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
    @RequiredPermission("部门保存或更新")
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

    @RequiredPermission("部门删除")
    public String delete() {
        Long departId = this.department.getId();
        if (departId != null) {
            this.departmentService.delete(departId);
        }
        return SUCCESS;
    }
}
