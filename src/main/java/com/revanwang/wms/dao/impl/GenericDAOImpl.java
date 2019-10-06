package com.revanwang.wms.dao.impl;

import com.revanwang.wms.dao.IGenericDAO;
import org.hibernate.SessionFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

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
}
