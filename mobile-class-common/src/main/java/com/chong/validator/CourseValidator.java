package com.chong.validator;

import com.chong.entity.Course;
import com.chong.entity.CourseSubscription;

/**
 * 课程校验器
 * Created by LXChild on 07/04/2017.
 */
public final class CourseValidator {

    private CourseValidator(){}

    public static void notEmpty(Course course) {
        BaseValidator.notEmpty(course, "课程不能为空");
        BaseValidator.notEmpty(course.getTeacherId(), "授课老师 ID 不能为空");
        BaseValidator.notEmpty(course.getTeacherName(), "授课老师名称不能为空");
        BaseValidator.notEmptyString(course.getName(), "课程名称不能为空");
        BaseValidator.notEmptyString(course.getIntroduction(), "课程简介不能为空");
    }

    public static void notEmptySubscription(CourseSubscription courseSubscription) {
        BaseValidator.notEmpty(courseSubscription);
        BaseValidator.notEmpty(courseSubscription.getCourseId());
        BaseValidator.notEmpty(courseSubscription.getUserId());
        BaseValidator.notEmpty(courseSubscription.getCreateTime());
    }
}
