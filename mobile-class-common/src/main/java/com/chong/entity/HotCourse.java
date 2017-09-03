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
 * 热门课程实体类
 * Created by LXChild on 21/04/2017.
 */
@Getter
@Setter
@ToString
@Entity
public class HotCourse {

    @Id
    @GeneratedValue
    @Column(length = 1)
    private Integer id;

    @Column(nullable = false)
    private Long courseId;

    @Column(nullable = false)
    private Long heat;

    @Column(nullable = false)
    private Date createTime = new Date();

    public HotCourse() {
        // For Jpa
    }

    public HotCourse(Long courseId, Long heat, Date createTime) {
        this.courseId = courseId;
        this.heat = heat;
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotCourse)) return false;

        HotCourse hotCourse = (HotCourse) o;

        if (getCourseId() != null ? !getCourseId().equals(hotCourse.getCourseId()) : hotCourse.getCourseId() != null)  {
            return false;
        }
        if (getHeat() != null ? !getHeat().equals(hotCourse.getHeat()) : hotCourse.getHeat() != null) {
            return false;
        }
        return true;
    }
}
