package com.revanwang.wms.dao;

import com.revanwang.wms.domain.Department;

import java.util.List;

public interface IDepartmentDAO {

    /**
     * 保存部门
     * @param department    部门
     */
    void save(Department department);

    /**
     * 删除部门
     * @param department    部门
     */
    void delete(Department department);

    /**
     * 更新部门信息
     * @param department    部门
     */
    void update(Department department);

    /**
     * 查询部门
     * @param id    部门id
     * @return  id部门
     */
    Department get(Long id);

    /**
     * @return 返回所有的部门
     */
    List<Department> getList();

}
