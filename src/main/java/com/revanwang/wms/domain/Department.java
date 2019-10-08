package com.revanwang.wms.domain;

import lombok.Data;

/**
 * 部门对象
 */
@Data
public class Department extends BaseDomain {
    private String      name;           //部门名称
    private String      sn;             //部门编码
}
