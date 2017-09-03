package com.chong.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 帖子图片实体类
 * Created by LXChild on 02/05/2017.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class PostImg {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long creatorId;

    @Column(nullable = false)
    private Long postId;

    public PostImg(String name, Long creatorId, Long postId) {
        this.name = name;
        this.creatorId = creatorId;
        this.postId = postId;
    }
}
