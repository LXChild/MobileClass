package com.chong.dataobject;

import com.chong.entity.Course;
import com.chong.entity.Courseware;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 课程数据对象
 * Created by LXChild on 19/04/2017.
 */
@Getter
@Setter
public class CourseDO {

    private Course course;

    private List<Courseware> coursewares;

    public CourseDO(Course course, List<Courseware> coursewares) {
        this.course = course;
        this.coursewares = coursewares;
    }

    @Override
    public String toString() {
        return "CourseDO{" +
                "course=" + course +
                ", coursewares=" + coursewares +
                '}';
    }
}
