package com.revanwang.wms.service.impl;

import com.revanwang.wms.dao.IPermissionDAO;
import com.revanwang.wms.domain.Permission;
import com.revanwang.wms.query.CommonQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IPermissService;
import lombok.Setter;

import java.util.List;

public class PermissServiceImpl implements IPermissService {

    @Setter
    private IPermissionDAO  permissionDAO;

    @Override
    public void save(Permission permission) {
        this.permissionDAO.save(permission);
    }

    @Override
    public void delete(Long id) {
        this.permissionDAO.delete(get(id));
    }

    @Override
    public void update(Permission permission) {
        this.permissionDAO.update(permission);
    }

    @Override
    public Permission get(Long id) {
        return this.permissionDAO.get(id);
    }

    @Override
    public List<Permission> getList() {
        return this.permissionDAO.getList();
    }

    @Override
    public QueryResultObject query(CommonQueryObject qo) {
        return this.permissionDAO.query(qo);
    }

    @Override
    public List<Permission> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.permissionDAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<Permission> query(String condition, Object... args) {
        return this.permissionDAO.query(condition, args);
    }

    @Override
    public Permission queryObject(String condition, Object... args) {
        return this.permissionDAO.queryObject(condition, args);
    }
}
