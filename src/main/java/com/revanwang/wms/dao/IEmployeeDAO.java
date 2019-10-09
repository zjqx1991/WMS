package com.revanwang.wms.dao;


import com.revanwang.wms.domain.Employee;


public interface IEmployeeDAO extends IGenericDAO<Employee> {

    /**
     *  检测用户登录
     * @param username
     * @param password
     * @return
     */
    Employee checkLogin(String username, String password);
}
