package com.chong.dto;

/**
 * 分页查询排序数据传输对象
 * Created by LXChild on 16/04/2017.
 */
public class SortDTO {

    //排序方式
    private String orderType;

    //排序字段
    private String orderField;

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public SortDTO(String orderType, String orderField) {
        this.orderType = orderType;
        this.orderField = orderField;
    }

    //默认为DESC排序
    public SortDTO(String orderField) {
        this.orderField = orderField;
        this.orderType = "desc";
    }
}
