package com.chong.dataobject;

import com.chong.entity.Course;
import com.chong.entity.HotCourse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 热门课程数据对象
 * Created by LXChild on 21/04/2017.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class HotCourseDO {

    private Course course;

    private HotCourse hotCourse;
}
