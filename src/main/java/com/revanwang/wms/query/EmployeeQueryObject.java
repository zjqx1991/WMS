package com.revanwang.wms.query;


import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class EmployeeQueryObject {

    @Setter @Getter
    private String  keyword;
    @Setter @Getter
    private Long    departId;
    @Setter @Getter
    private Integer currentPage = 1;
    @Setter @Getter
    private Integer pageSize = 5;

    //查询条件
    private List<String> conditionList = new ArrayList<>();

    //查询条件参数
    private Map<String, Object> paramMap = new LinkedHashMap<>();

    /**
     * 高级查询条件
     */
    public String getQueryCondition() {

        if (hasLength(this.keyword)) {
            this.conditionList.add("(obj.name LIKE :name OR obj.email LIKE :email)");
            String keyString = "%" + this.keyword + "%";
            this.paramMap.put("name", keyString);
            this.paramMap.put("email", keyString);
        }

        if (this.departId != null && this.departId.intValue() > 0) {
            this.conditionList.add("obj.department.id = :departId");
            this.paramMap.put("departId", this.departId);
        }

        int conditionCount = this.conditionList.size();
        if (conditionCount == 0) {
            return "";
        }

        //拼接查询条件
        StringBuilder sb = new StringBuilder(80);
        for (int i = 0; i < conditionCount; i++) {
            if (i == 0) {
                sb.append(" WHERE ");
            }
            else {
                sb.append(" AND ");
            }
            sb.append(this.conditionList.get(i));
        }

        System.out.println("EmployeeQueryObject.getQueryCondition:==" + sb.toString());
        return sb.toString();
    }

    /**
     * 高级查询条件参数
     */
    public Map<String, Object> getQueryParams() {
        return this.paramMap;
    }

    private boolean hasLength(String string) {
        return string != null && string.length() > 0 && !"".equals(string);
    }


    @Override
    public String toString() {
        return "EmployeeQueryObject{" +
                "keyword='" + keyword + '\'' +
                ", departId=" + departId +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
