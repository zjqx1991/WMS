package com.revanwang.wms.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工对象
 */
@Setter
@Getter
@ToString
public class Employee extends BaseDomain {
    private String      name;               //员工名称
    private String      password;           //员工密码
    private String      email;              //员工邮箱
    private Integer     age;                //员工年龄
    private boolean     admin;              //是否是超级管理员

    //员工的所有角色
    private List<Role> roles = new ArrayList<>();
    private Department  department;         //员工所属部门

}
