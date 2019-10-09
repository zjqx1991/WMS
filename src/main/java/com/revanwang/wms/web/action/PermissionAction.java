package com.revanwang.wms.web.action;

import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.Permission;
import com.revanwang.wms.query.CommonQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IPermissionService;
import lombok.Getter;
import lombok.Setter;

public class PermissionAction extends BaseAction {

    @Setter
    private IPermissionService permissionService;

    @Getter
    private CommonQueryObject qo = new CommonQueryObject();
    @Getter
    private Permission permission = new Permission();

    @Override
    @RequiredPermission("权限列表")
    public String execute() throws Exception {
        QueryResultObject resultObject = this.permissionService.query(qo);
        ActionContextPut("pageResult", resultObject);
        return LIST;
    }


    @RequiredPermission("权限加载")
    public String reload() {
        System.out.println("PermissionAction.reload");
        this.permissionService.reload();
        return NONE;
    }

    @RequiredPermission("权限删除")
    public String delete() {
        this.permissionService.delete(this.permission.getId());
        return SUCCESS;
    }
}
