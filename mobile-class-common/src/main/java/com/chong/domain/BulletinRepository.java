package com.chong.domain;

import com.chong.entity.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 公告数据库操作
 * Created by LXChild on 28/04/2017.
 */
public interface BulletinRepository extends JpaRepository<Bulletin, Integer> {

    Bulletin findByPosition(String position);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Bulletin set content =:content, creatorId =:creatorId, updateTime =:updateTime where position =:position")
    void update(@Param("content") String content, @Param("position") String position,
                @Param("creatorId") Long creatorId, @Param("updateTime") Date updateTime);
}
