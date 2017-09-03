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
 * 首页轮播图数据库实体类
 * Created by LXChild on 22/04/2017.
 */
@Getter
@Setter
@ToString
@Entity
public class Carousel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String imgName;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String pageUrl;

    @Column(nullable = false, unique = true)
    private Integer page;

    @Column(nullable = false)
    private Long creatorId;

    @Column(nullable = false)
    private Date createTime = new Date();
}
