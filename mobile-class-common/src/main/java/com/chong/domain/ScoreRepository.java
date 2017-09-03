package com.chong.domain;

import com.chong.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 总分数据库操作
 * Created by LXChild on 27/04/2017.
 */
public interface ScoreRepository extends JpaRepository<Score, Long> {

    Score findByPaperIdAndUserId(Long paperId, Long userId);

    List<Score> findByPaperId(Long paperId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Score set totalScore =:totalScore where id =:id")
    void update(@Param("id") Long id, @Param("totalScore") Integer score);
}
