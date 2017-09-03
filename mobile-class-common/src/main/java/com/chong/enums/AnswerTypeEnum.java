package com.chong.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 答案类型枚举(非常重要，如需增加类型，请在后面添加，如需更改请联系作者)
 * Created by LXChild on 23/04/2017.
 * @Email lxchild@outlook.com
 */
public enum AnswerTypeEnum {
    TEXT(1, "txt", "文本"), IMG(2, "img", "照片");

    @Getter
    @Setter
    private Integer index;
    @Getter
    @Setter
    private String tag;

    @Getter
    @Setter
    private String comment;

    AnswerTypeEnum(Integer index, String tag, String comment) {
        this.index = index;
        this.tag = tag;
        this.comment = comment;
    }
}
