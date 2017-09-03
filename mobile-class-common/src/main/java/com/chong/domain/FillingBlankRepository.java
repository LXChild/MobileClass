package com.chong.domain;

import com.chong.entity.FillingBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 填空题相关 JPA 操作
 * Created by LXChild on 06/04/2017.
 */
public interface FillingBlankRepository extends JpaRepository<FillingBlank, Long> {

    /**
     * 根据试卷 ID 查询填空题
     * @param paperId 所属试卷 ID
     * @return 对应填空题
     */
    List<FillingBlank> findByPaperId(Long paperId);

    /**
     * 更新填空题
     * @param id 填空题 ID
     * @param question 问题
     * @param answer 答案
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update FillingBlank set question =:question, answer =:answer, score =:score where id =:id")
    void update(@Param("id") Long id, @Param("question") String question, @Param("answer") String answer,
                @Param("score") Integer score);
}
