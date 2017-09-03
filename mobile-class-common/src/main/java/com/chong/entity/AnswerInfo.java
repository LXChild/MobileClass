package com.chong.entity;

import com.chong.enums.AnswerTypeEnum;
import com.chong.enums.QuestionTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 答案详情实体类
 * Created by LXChild on 06/04/2017.
 */
@Getter
@Setter
@ToString
@Entity
public class AnswerInfo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long paperId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer questionTypeId;

    @Column(nullable = false)
    private String questionType;

    @Column(nullable = false)
    private Long questionId;

    @Column(nullable = false)
    private Integer answerTypeId;

    @Column(nullable = false)
    private String answerType;

    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private Date createTime;

    public void setAnswerType(AnswerTypeEnum answerTypeEnum) {
        this.answerTypeId = answerTypeEnum.getIndex();
        this.answerType = answerTypeEnum.getTag();
    }

    public void setQuestionType(QuestionTypeEnum questionTypeEnum) {
        this.questionTypeId = questionTypeEnum.getIndex();
        this.questionType = questionTypeEnum.getTag();
    }
}
