package com.chong.enums;


import lombok.Getter;
import lombok.Setter;

/**
 * 问题类型枚举(非常重要，如需增加类型，请在后面添加，如需更改请联系作者)
 * Created by LXChild on 23/04/2017.
 * @Email lxchild@outlook.com
 */
public enum  QuestionTypeEnum {
    SINGLESEL(1, "singleSel", "单选题"), MULTISEL(2, "multiSel", "多选题"), RIGHTWRONG(3, "rightWrong", "对错题"),
    FILLINGBLANK(4, "fillingBlank", "填空题"), ESSAY(5, "essay", "简答题");

    @Getter
    @Setter
    private Integer index;

    @Getter
    @Setter
    private String tag;

    @Getter
    @Setter
    private String comment;

    QuestionTypeEnum(Integer index, String tag, String comment) {
        this.index = index;
        this.tag = tag;
        this.comment = comment;
    }
}
