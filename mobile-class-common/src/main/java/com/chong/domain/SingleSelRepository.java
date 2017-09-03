package com.chong.domain;

import com.chong.entity.SingleSel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 单选题 JPA 操作
 * Created by LXChild on 06/04/2017.
 */
public interface SingleSelRepository extends JpaRepository<SingleSel, Long> {

    /**
     * 根据试卷 ID 查询单选题
     * @param paperId 所属试卷 ID
     * @return 对应单选题
     */
    List<SingleSel> findByPaperId(Long paperId);

    /**
     * 更新单选题
     * @param id 单选题 ID
     * @param question 问题
     * @param answerA 答案 A
     * @param answerB 答案 B
     * @param answerC 答案 C
     * @param answerD 答案 D
     * @param answerR 正确答案
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update SingleSel set question =:question, answerA =:answerA, answerB =:answerB, answerC =:answerC," +
            " answerD =:answerD, answerR =:answerR, score =:score where id =:id")
    void update(@Param("id") Long id, @Param("question") String question, @Param("answerA") String answerA,
                @Param("answerB") String answerB, @Param("answerC") String answerC, @Param("answerD") String answerD,
                @Param("answerR") String answerR, @Param("score") Integer score);
}
