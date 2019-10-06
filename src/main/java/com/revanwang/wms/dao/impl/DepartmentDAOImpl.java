package com.revanwang.wms.dao.impl;

import com.revanwang.wms.dao.IDepartmentDAO;
import com.revanwang.wms.domain.Department;
import org.hibernate.SessionFactory;

import java.util.List;

public class DepartmentDAOImpl implements IDepartmentDAO {

    private SessionFactory  sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Department department) {
        this.sessionFactory.getCurrentSession().persist(department);
    }

    @Override
    public void delete(Department department) {
        this.sessionFactory.getCurrentSession().delete(department);
    }

    @Override
    public void update(Department department) {
        this.sessionFactory.getCurrentSession().update(department);
    }

    @Override
    public Department get(Long id) {
        return (Department) this.sessionFactory.getCurrentSession().load(Department.class, id);
    }

    @Override
    public List<Department> getList() {
        return this.sessionFactory.getCurrentSession().createCriteria(Department.class).list();
    }
}
