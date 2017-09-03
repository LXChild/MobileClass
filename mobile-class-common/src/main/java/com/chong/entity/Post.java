package com.chong.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 帖子实体类
 * Created by LXChild on 07/04/2017.
 */
@Getter
@Setter
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long creatorId;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date createTime;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", creatorId='" + creatorId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
