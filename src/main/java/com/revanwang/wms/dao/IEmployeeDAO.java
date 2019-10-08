package com.revanwang.wms.dao;


import com.revanwang.wms.domain.Employee;
import com.revanwang.wms.query.EmployeeQueryObject;
import com.revanwang.wms.query.PageResultObject;


public interface IEmployeeDAO extends IGenericDAO<Employee> {

    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    PageResultObject query(EmployeeQueryObject qo);


    /**
     * 分页查询
     * @param currentPage   当前页
     * @param pageSize      个数
     * @return
     */
    PageResultObject query(Integer currentPage, Integer pageSize);

}
