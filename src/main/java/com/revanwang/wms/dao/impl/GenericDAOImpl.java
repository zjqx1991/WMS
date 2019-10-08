package com.revanwang.wms.dao.impl;

import com.revanwang.wms.dao.IGenericDAO;
import com.revanwang.wms.query.AbstractQueryObject;
import com.revanwang.wms.query.PageResultObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GenericDAOImpl<T> implements IGenericDAO<T> {

    protected SessionFactory sessionFactory;
    private Class<T> targetClass;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    /**
     * 创建子类对象时，首先要创建父类对象
     */
    public GenericDAOImpl() {
        //获取this的 带有泛型信息的父类
        ParameterizedType clazz = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取泛型父类中泛型参数
        Type[] types = clazz.getActualTypeArguments();
        this.targetClass = (Class<T>) types[0];
    }

    @Override
    public void save(T obj) {
        this.sessionFactory.getCurrentSession().persist(obj);
    }

    @Override
    public void delete(T obj) {
        this.sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public void update(T obj) {
        this.sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public T get(Long id) {
        return this.sessionFactory.getCurrentSession().load(this.targetClass, id);
    }

    @Override
    public List<T> getList() {
        return this.sessionFactory.getCurrentSession().createCriteria(this.targetClass).list();
    }

    @Override
    public PageResultObject query(AbstractQueryObject qo) {

        //每页个数
        Integer pageSize = qo.getPageSize();
        //当前页
        Integer currentPage = qo.getCurrentPage();

        //拼接查询参数
        List paramList = qo.getQueryParams();

        //1、COUNT
        Session session = sessionFactory.getCurrentSession();

        //拼接查询条件
        StringBuilder countSB = new StringBuilder(100);
        countSB.append("SELECT COUNT(obj) FROM ");
        countSB.append(this.targetClass.getSimpleName());
        countSB.append(" obj");
        countSB.append(qo.getQueryCondition());
        //查询总个数
        Query query = session.createQuery(countSB.toString());
        //设置参数
        setConditionParame(paramList, query);
        //查询总个数
        Integer totalCount = ((Long) query.uniqueResult()).intValue();

        //查询数据
        StringBuilder dataSB = new StringBuilder(100);
        dataSB.append("SELECT obj FROM ");
        dataSB.append(this.targetClass.getSimpleName());
        dataSB.append(" obj");
        dataSB.append(qo.getQueryCondition());
        query = session.createQuery(dataSB.toString());
        //设置参数
        setConditionParame(paramList, query);
        //设置返回结果
        query.setFirstResult((currentPage - 1) * pageSize);
        query.setMaxResults(pageSize);

        return new PageResultObject(currentPage, pageSize, totalCount, query.list());
    }


    /**
     * 给请求参数设置
     */
    private void setConditionParame(List paramList, Query query) {
        for (Object obj : paramList) {
            Map<String, Object> map = (Map<String, Object>) obj;
            for (Map.Entry entry : map.entrySet()) {
                query.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
    }
}
