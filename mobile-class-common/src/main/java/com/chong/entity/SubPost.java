package com.chong.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 子贴实体类
 * Created by LXChild on 07/04/2017.
 */
@Getter
@Setter
@Entity
public class SubPost {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long parentId;

    @Column(nullable = false)
    private Long creatorId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date createTime;

    @Override
    public String toString() {
        return "SubPost{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", creatorId=" + creatorId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
