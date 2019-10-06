package com.revanwang.wms.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Employee {
    private Long        id;
    private String      name;
    private String      password;
    private String      email;
    private Integer     age;
    private Department  department;

}
