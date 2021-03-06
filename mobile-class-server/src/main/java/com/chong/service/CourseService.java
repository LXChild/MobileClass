package com.chong.service;

import com.chong.domain.CourseRepository;
import com.chong.entity.Course;
import com.chong.validator.BaseValidator;
import com.chong.validator.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程服务
 * Created by LXChild on 07/04/2017.
 */
@Service
public class CourseService {

    private final CourseRepository repository;

    @Autowired
    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Course save(Course course) {
        CourseValidator.notEmpty(course);
        return repository.save(course);
    }

    public List<Course> findAll() {
        return repository.findAll();
    }

    public Course findOne(Long id) {
        BaseValidator.notEmpty(id, "课程 ID 不能为空");
        return repository.findOne(id);
    }

    public Course findByName(String name) {
        BaseValidator.notEmptyString(name, "课程名称不能为空");
        return repository.findByName(name);
    }

    public Course findByTeacherId(Long teacherId) {
        BaseValidator.notEmpty(teacherId, "授课老师 ID 不能为空");
        return repository.findByTeacherId(teacherId);
    }
}
