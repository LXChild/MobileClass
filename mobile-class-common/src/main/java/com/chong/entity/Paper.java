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
 * 试卷实体类
 * Created by LXChild on 03/04/2017.
 */
@Getter
@Setter
@ToString
@Entity
public class Paper {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String remark;

    @Column(nullable = false)
    private Long creatorId;

    @Column(nullable = false)
    private Long courseId;

    @Column(nullable = false)
    private Date createTime;

    @Column(nullable = false)
    private Date updateTime = new Date();

    @Column(nullable = false)
    private Date quizTime;

    @Column(nullable = false)
    private Integer quizDuration;

    @Column(nullable = false)
    private Boolean showAnswer = false;

    @Column(nullable = false)
    private Boolean showPaper = false;
}
