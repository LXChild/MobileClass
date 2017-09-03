package com.chong.domain;

import com.chong.entity.Essay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 简答题相关 JPA 操作
 * Created by LXChild on 06/04/2017.
 */
public interface EssayRepository extends JpaRepository<Essay, Long> {

    /**
     * 根据试卷 ID 查询简答题
     * @param paperId 所属试卷 ID
     * @return 对应简答题
     */
    List<Essay> findByPaperId(Long paperId);

    /**
     * 更新简答题题
     * @param id 简答题 ID
     * @param question 问题
     * @param answer 答案
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Essay set question =:question, answer =:answer, score =:score where id =:id")
    void update(@Param("id") Long id, @Param("question") String question, @Param("answer") String answer,
                @Param("score") Integer score);
}
