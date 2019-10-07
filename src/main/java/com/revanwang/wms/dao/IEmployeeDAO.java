package com.revanwang.wms.dao;


import com.revanwang.wms.domain.Employee;
import com.revanwang.wms.query.EmployeeQueryObject;

import java.util.List;

public interface IEmployeeDAO extends IGenericDAO<Employee> {

    /**
     * 高级查询
     * @param qo    查询对象
     * @return
     */
    List<Employee> query(EmployeeQueryObject qo);

}
