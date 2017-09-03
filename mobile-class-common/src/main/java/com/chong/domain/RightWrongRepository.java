package com.chong.domain;

import com.chong.entity.RightWrong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 对错题相关 JPA 操作
 * Created by LXChild on 06/04/2017.
 */
public interface RightWrongRepository extends JpaRepository<RightWrong, Long> {

    /**
     * 根据试卷 ID 获取所属对错题
     * @param paperId 所属试卷 ID
     * @return 对错题列表
     */
    List<RightWrong> findByPaperId(Long paperId);

    /**
     * 更新对错题
     * @param id 对错题 ID
     * @param question 问题
     * @param answer 答案
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update RightWrong set question =:question, answer =:answer, score =:score where id =:id")
    void update(@Param("id") Long id, @Param("question") String question, @Param("answer") Boolean answer,
                @Param("score") Integer score);
}
