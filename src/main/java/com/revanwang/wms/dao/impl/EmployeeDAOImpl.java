package com.revanwang.wms.dao.impl;

import com.revanwang.wms.dao.IEmployeeDAO;
import com.revanwang.wms.domain.Employee;
import org.hibernate.SessionFactory;

import java.util.List;

public class EmployeeDAOImpl implements IEmployeeDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Employee employee) {
        this.sessionFactory.getCurrentSession().persist(employee);
    }

    @Override
    public void delete(Employee employee) {
        this.sessionFactory.getCurrentSession().delete(employee);
    }

    @Override
    public void update(Employee employee) {
        this.sessionFactory.getCurrentSession().update(employee);
    }

    @Override
    public Employee get(Long id) {
        return this.sessionFactory.getCurrentSession().load(Employee.class, id);
    }

    @Override
    public List<Employee> getList() {
        return this.sessionFactory.getCurrentSession().createCriteria(Employee.class).list();
    }
}
