package com.chong.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 公告位置枚举
 * Created by LXChild on 28/04/2017.
 */
public enum  BulletinPositionEnum {

    PAPER(1, "paper"), POST(2, "post");

    @Getter
    @Setter
    private Integer index;

    @Getter
    @Setter
    private String tag;

    BulletinPositionEnum(Integer index, String tag) {
        this.index = index;
        this.tag = tag;
    }
}
