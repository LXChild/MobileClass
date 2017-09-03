package com.chong.domain;

import com.chong.entity.Carousel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 轮播图数据库操作
 * Created by LXChild on 22/04/2017.
 */
public interface CarouselRepository extends JpaRepository<Carousel, Long> {

    /**
     * 更新轮播页
     * @param page 轮播页数
     * @param imgName 轮播页图片名
     * @param title 轮播页标题
     * @param content 轮播页内容
     * @param pageUrl 轮播页转向 URL
     * @param creatorId 上传轮播页作者
     * @param createTime 上传时间
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Carousel set imgName =:imgName, title=:title, content=:content," +
            "pageUrl=:pageUrl, creatorId=:creatorId, createTime=:createTime where page =:page")
    void update(@Param("page") Integer page, @Param("imgName") String imgName, @Param("title") String title,
                @Param("content") String content, @Param("pageUrl") String pageUrl,
                @Param("creatorId") Long creatorId, @Param("createTime") Date createTime);

    Carousel findByPage(Integer page);
}
