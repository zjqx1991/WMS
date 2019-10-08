package com.revanwang.wms.domain;


import lombok.Data;

/**
 * 权限对象
 */
@Data
public class Permission extends BaseDomain {
    private String  name;           //权限名称
    private String  expression;     //权限表达式
}
