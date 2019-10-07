package com.revanwang.wms.service.impl;

import com.revanwang.wms.dao.IEmployeeDAO;
import com.revanwang.wms.domain.Employee;
import com.revanwang.wms.query.EmployeeQueryObject;
import com.revanwang.wms.service.IEmployeeService;
import lombok.Setter;

import java.util.List;

public class EmployeeServiceImpl implements IEmployeeService {


    @Setter
    private IEmployeeDAO employeeDAO;

    @Override
    public void save(Employee employee) {
        this.employeeDAO.save(employee);
    }

    @Override
    public void delete(Long id) {
        this.employeeDAO.delete(get(id));
    }

    @Override
    public void update(Employee employee) {
        this.employeeDAO.update(employee);
    }

    @Override
    public Employee get(Long id) {
        return this.employeeDAO.get(id);
    }

    @Override
    public List<Employee> getList() {
        return this.employeeDAO.getList();
    }

    @Override
    public List<Employee> query(EmployeeQueryObject qo) {
        return this.employeeDAO.query(qo);
    }
}
