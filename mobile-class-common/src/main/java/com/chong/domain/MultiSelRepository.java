package com.chong.domain;

import com.chong.entity.MultiSel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 多选题相关 JPA 操作
 * Created by LXChild on 06/04/2017.
 */
public interface MultiSelRepository extends JpaRepository<MultiSel, Long> {

    /**
     * 根据试卷 ID 查询多选题
     * @param paperId 所属试卷 ID
     * @return 对应多选题
     */
    List<MultiSel> findByPaperId(Long paperId);

    /**
     * 更新多选题
     * @param id 多选题 ID
     * @param question 问题
     * @param answerA 答案 A
     * @param answerB 答案 B
     * @param answerC 答案 C
     * @param answerD 答案 D
     * @param answerE 答案 E
     * @param answerF 答案 F
     * @param answerR 正确答案
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update MultiSel set question =:question, answerA =:answerA, answerB =:answerB, answerC =:answerC," +
            " answerD =:answerD, answerE =:answerE, answerF =:answerF, answerR =:answerR, score =:score where id =:id")
    void update(@Param("id") Long id, @Param("question") String question, @Param("answerA") String answerA,
                @Param("answerB") String answerB, @Param("answerC") String answerC, @Param("answerD") String answerD,
                @Param("answerE") String answerE, @Param("answerF") String answerF, @Param("answerR") String answerR,
                @Param("score") Integer score);
}
