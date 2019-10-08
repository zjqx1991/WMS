package com.revanwang.wms.query;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


public class EmployeeQueryObject extends AbstractQueryObject {

    @Setter @Getter
    private String  keyword;
    @Setter @Getter
    private Long    departId = -1L;

    /**
     * 高级查询条件
     */
    @Override
    void customQueryCondition() {
        //1、关键字（名称 邮箱）
        if (hasLength(this.keyword)) {
            String keyValue = "%" + this.keyword + "%";
            addQueryCondition("(obj.name LIKE :name OR obj.email LIKE :email)",
                    setConditionValue("name", keyValue),
                    setConditionValue("email", keyValue));
        }

        //2、部门id
        if (this.departId != null && this.departId.intValue() > 0) {
            addQueryCondition("obj.department.id = :departId",
                    setConditionValue("departId", this.departId));
        }
    }

    private Map<String, Object> setConditionValue(String key, Object value) {
        Map<String, Object> map = new HashMap();
        map.put(key, value);
        return map;
    }


    @Override
    public String toString() {
        return "EmployeeQueryObject{" +
                "keyword='" + keyword + '\'' +
                ", departId=" + departId +
                '}';
    }
}
