package com.revanwang.wms.dao.impl;

import com.revanwang.wms.dao.IEmployeeDAO;
import com.revanwang.wms.domain.Employee;
import com.revanwang.wms.query.EmployeeQueryObject;
import com.revanwang.wms.query.PageResultObject;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;

public class EmployeeDAOImpl
        extends GenericDAOImpl<Employee>
        implements IEmployeeDAO {

    @Override
    public List<Employee> query(EmployeeQueryObject qo) {
        StringBuilder sb = new StringBuilder(100);
        sb.append("SELECT obj FROM Employee obj");
        sb.append(qo.getQueryCondition());
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(sb.toString());
        //设置参数
        Map<String, Object> paramMap = qo.getQueryParams();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.list();
    }


    @Override
    public PageResultObject query(Integer currentPage, Integer pageSize) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT COUNT(obj) FROM Employee obj";
        Query query = session.createQuery(hql);
        //1 count
        Integer totalCount = ((Long) query.uniqueResult()).intValue();


        //2 Result
        hql = "SELECT obj FROM Employee obj";
        query = session.createQuery(hql);
        query.setFirstResult((currentPage - 1) * pageSize);
        query.setMaxResults(pageSize);

        return new PageResultObject(currentPage, pageSize, totalCount, query.list());
    }


}
