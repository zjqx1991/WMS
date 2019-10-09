package com.revanwang.wms.dao.impl;

import com.revanwang.utils.RevanMapUtils;
import com.revanwang.wms.dao.IEmployeeDAO;
import com.revanwang.wms.domain.Employee;

public class EmployeeDAOImpl
        extends GenericDAOImpl<Employee>
        implements IEmployeeDAO {

    @Override
    public Employee checkLogin(String username, String password) {
        return queryObject("obj.name = :name and obj.password = :password",
                RevanMapUtils.revan_createMapObject("name", username),
                RevanMapUtils.revan_createMapObject("password", password));
    }
}
