package com.chong.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 公告实体类
 * Created by LXChild on 28/04/2017.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Bulletin {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, unique = true)
    private String position;

    @Column(nullable = false)
    private Long creatorId;

    @Column(nullable = false)
    private Date createTime;

    @Column(nullable = false)
    private Date updateTime = new Date();

    public Bulletin(String content, String position, Long creatorId, Date createTime) {
        this.content = content;
        this.position = position;
        this.creatorId = creatorId;
        this.createTime = createTime;
    }
}
