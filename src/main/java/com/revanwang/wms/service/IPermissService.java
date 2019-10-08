package com.revanwang.wms.service;

import com.revanwang.wms.domain.Permission;
import com.revanwang.wms.query.CommonQueryObject;
import com.revanwang.wms.query.QueryResultObject;

import java.util.List;

public interface IPermissService {

    /**
     * 保存权限
     * @param permission    权限
     */
    void save(Permission permission);

    /**
     * 删除权限
     * @param id    权限id
     */
    void delete(Long id);

    /**
     * 更新权限信息
     * @param permission    权限
     */
    void update(Permission permission);

    /**
     * 查询权限
     * @param id    权限id
     * @return  id员工
     */
    Permission get(Long id);

    /**
     * @return 返回所有的权限
     */
    List<Permission> getList();


    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(CommonQueryObject qo);



    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Permission> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Permission> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    Permission queryObject(String condition, Object...args);
}
