package com.chong.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 热门帖子实体类
 * Created by LXChild on 20/04/2017.
 */
@Getter
@Setter
@ToString
@Entity
public class HotPost {
    @Id
    @GeneratedValue
    @Column(length = 1)
    private Integer id;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long heat;

    @Column(nullable = false)
    private Date createTime = new Date();

    public HotPost() {
        // For Jpa
    }

    public HotPost(Long postId, Long heat) {
        this.postId = postId;
        this.heat = heat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotPost)) return false;

        HotPost hotPost = (HotPost) o;

        if (getPostId() != null ? !getPostId().equals(hotPost.getPostId()) : hotPost.getPostId() != null) return false;
        return getHeat() != null ? getHeat().equals(hotPost.getHeat()) : hotPost.getHeat() == null;
    }
}
