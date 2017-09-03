package com.chong.domain;

import com.chong.entity.AnswerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 答案详情 JPA 操作
 * Created by LXChild on 06/04/2017.
 */
public interface AnswerInfoRepository extends JpaRepository<AnswerInfo, Long> {

    List<AnswerInfo> findByPaperIdAndUserId(Long paperId, Long userId);

    AnswerInfo findByPaperIdAndUserIdAndQuestionTypeAndQuestionId(Long paperId, Long userId,
                                                                        String questionType, Long questionId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update AnswerInfo set answer =:answer, score =:score where id =:id")
    void update(@Param("id") Long id, @Param("answer") String answer, @Param("score") Integer score);

    List<AnswerInfo> findByPaperIdAndQuestionTypeAndQuestionId(Long paperId, String questionType, Long questionId);
}
