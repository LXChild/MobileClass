package com.chong.domain;

import com.chong.entity.HotCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 热门课程数据库操作
 * Created by LXChild on 21/04/2017.
 */
public interface HotCourseRepository extends JpaRepository<HotCourse, Long> {

    /**
     * 更新热门课程
     * @param courseId 课程 ID
     * @param heat 课程热度
     * @param createTime 上榜时间
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update HotCourse set courseId =:courseId, heat =:heat, createTime =:createTime where id =:rank")
    void updateHotCourse(@Param("rank") Integer rank, @Param("courseId") Long courseId, @Param("heat") Long heat,
                         @Param("createTime") Date createTime);
}
