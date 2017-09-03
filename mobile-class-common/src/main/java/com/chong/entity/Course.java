package com.chong.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 课程实体类
 * Created by LXChild on 07/04/2017.
 */
@Getter
@Setter
@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long teacherId;

    @Column(nullable = false)
    private String teacherName;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String introduction;

    @Column(nullable = false)
    private Date createTime;

    @Column(nullable = false)
    private Date updateTime = new Date();

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
