package com.chong.domain;

import com.chong.entity.CourseSubscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课程关注数据库操作接口
 * Created by LXChild on 21/04/2017.
 */
public interface CourseSubscriptionRepository extends JpaRepository<CourseSubscription, Long> {

    /**
     * 根据订阅人数和创建时间查询最热门的 5 个课程
     * @return 热门课程
     */
    @Query(value = "SELECT course_id, count(*) AS heat FROM course_subscription GROUP BY course_id" +
            " ORDER BY count(*) DESC, max(create_time) DESC LIMIT 5", nativeQuery = true)
    List<Object[]> findHotCourses();

    /**
     * 根据课程名和用户名查询是否由此订阅
     * @param courseId 课程 ID
     * @param userId 用户 ID
     * @return 订阅
     */
    @Query(value = "SELECT * from course_subscription where course_id = ?1 and user_id = ?2", nativeQuery = true)
    List<Object[]> findSubscription(@Param("courseId") Long courseId, @Param("userId") Long userId);

    Page<CourseSubscription> findAllByCourseId(Long courseId, Pageable pageable);

    @Transactional
    void deleteByCourseIdAndUserId(Long courseId, Long userId);

    Page<CourseSubscription> findAllByUserId(Long userId, Pageable pageable);
}
