package com.chong.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色枚举
 * Created by LXChild on 03/04/2017.
 */
public enum RoleEnum {
    SYS_ADMIN(1, "ROLE_SYS_ADMIN", "系统管理员"), ADMIN(2, "ROLE_ADMIN", "管理员"),
    TEACHER(3, "ROLE_TEACHER", "教师"), STUDENT(4, "ROLE_STUDENT", "学生");

    @Getter
    @Setter
    private Integer index;

    @Getter
    @Setter
    private String tag;

    @Getter
    @Setter
    private String comment;

    RoleEnum(Integer index, String tag, String comment) {
        this.index = index;
        this.tag = tag;
        this.comment = comment;
    }
}
