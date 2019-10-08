package com.revanwang.wms.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 权限对象
 */
@Setter
@Getter
public class Permission extends BaseDomain {
    private String  name;           //权限名称
    private String  expression;     //权限表达式

    @Override
    public String toString() {
        return "Permission{" +
                "name='" + name + '\'' +
                ", expression='" + expression + '\'' +
                ", id=" + id +
                '}';
    }
}
