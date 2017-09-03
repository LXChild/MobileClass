package com.chong.domain;

import com.chong.entity.Courseware;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 课件相关 JPA 操作
 * Created by LXChild on 07/04/2017.
 */
public interface CoursewareRepository extends JpaRepository<Courseware, Long> {

    /**
     * 根据课件名称查询课件
     * @param name 课件名称
     * @return 查询结果
     */
    Courseware findByName(String name);

    /**
     * 根据创建者 ID 查询课件
     * @param creatorId 创建者 ID
     * @return 查询结果
     */
    List<Courseware> findByCreatorId(Long creatorId);

    /**
     * 根据所属课程 ID 查询
     * @param courseId 课程 ID
     * @return 查询结果
     */
    List<Courseware> findByCourseIdOrderByIdDesc(Long courseId);
}
