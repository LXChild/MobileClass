package com.chong.domain;

import com.chong.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 课程相关 JPA 操作
 * Created by LXChild on 07/04/2017.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * 根据课程名称进行查询
     * @param name 课程名称
     * @return 查询结果
     */
    Course findByName(String name);

    /**
     * 根据课程名称进行查询
     * @param name 课程名称
     * @return 查询结果
     */
    Page<Course> findAllByName(String name, Pageable pageable);

    /**
     * 根据授课老师 ID 进行查询
     * @param id 授课老师 ID
     * @return 查询结果
     */
    Course findByTeacherId(Long id);
}
