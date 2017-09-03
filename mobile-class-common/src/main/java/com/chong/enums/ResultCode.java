package com.chong.enums;

import lombok.Getter;

/**
 * 结果状态码
 * Created by LXChild on 04/04/2017.
 */
public enum ResultCode {
    UNKNOWN(-1, "未知错误"), OK(1, "请求成功"), FAILED(2, "请求失败"), NULL(3, "参数不能为空"),
    NOT_MATCH(4, "参数不匹配"), NOT_IN_DB(5, "在数据库中查询不到结果"), REPETITIVE(6, "重复数据"),
    STORAGE_FAILED(7, "文件存储异常"), NOT_FOUND(8, "找不到该资源");

    @Getter
    private Integer code;
    @Getter
    private String comment;

    ResultCode(Integer code, String comment) {
        this.code = code;
        this.comment = comment;
    }
}
