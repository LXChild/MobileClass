package com.chong.domain;

import com.chong.entity.HotPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 热门帖子相关 JPA 操作
 * Created by LXChild on 20/04/2017.
 */
public interface HotPostRepository extends JpaRepository<HotPost, Long> {

    /**
     * 更新热门帖子
     * @param postId 帖子 ID
     * @param heat 帖子热度
     * @param createTime 上榜时间
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update HotPost set postId =:postId, heat =:heat, createTime =:createTime where id =:rank")
    void updateHotPost(@Param("rank") Integer rank, @Param("postId") Long postId, @Param("heat") Long heat,
                                @Param("createTime") Date createTime);
}
