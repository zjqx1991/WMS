package com.revanwang.wms.dao.impl;

import com.revanwang.wms.dao.IEmployeeDAO;
import com.revanwang.wms.domain.Employee;
import com.revanwang.wms.query.EmployeeQueryObject;
import com.revanwang.wms.query.PageResultObject;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Map;

public class EmployeeDAOImpl
        extends GenericDAOImpl<Employee>
        implements IEmployeeDAO {

    @Override
    public PageResultObject query(EmployeeQueryObject qo) {
        //每页个数
        Integer pageSize = qo.getPageSize();
        //当前页
        Integer currentPage = qo.getCurrentPage();

        //拼接查询参数
        Map<String, Object> paramMap = qo.getQueryParams();

        //1、COUNT
        Session session = sessionFactory.getCurrentSession();

        //拼接查询条件
        StringBuilder countSB = new StringBuilder(100);
        countSB.append("SELECT COUNT(obj) FROM Employee obj");
        countSB.append(qo.getQueryCondition());
        //查询总个数
        Query query = session.createQuery(countSB.toString());
        //设置参数
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        //查询总个数
        Integer totalCount = ((Long) query.uniqueResult()).intValue();


        //查询数据
        StringBuilder dataSB = new StringBuilder(100);
        dataSB.append("SELECT obj FROM Employee obj");
        dataSB.append(qo.getQueryCondition());
        query = session.createQuery(dataSB.toString());
        //设置参数
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        //设置返回结果
        query.setFirstResult((currentPage - 1) * pageSize);
        query.setMaxResults(pageSize);

        return new PageResultObject(currentPage, pageSize, totalCount, query.list());
    }


    @Override
    public PageResultObject query(Integer currentPage, Integer pageSize) {
        System.out.println("EmployeeDAOImpl.query:==" + currentPage + "___" + pageSize);
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
