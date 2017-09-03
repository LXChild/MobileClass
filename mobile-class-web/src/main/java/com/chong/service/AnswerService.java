package com.chong.service;

import com.chong.domain.AnswerInfoRepository;
import com.chong.entity.AnswerInfo;
import com.chong.enums.AnswerTypeEnum;
import com.chong.enums.QuestionTypeEnum;
import com.chong.validator.BaseValidator;
import com.chong.validator.PaperValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 答案服务
 * Created by LXChild on 06/04/2017.
 */
@Service
public class AnswerService {

    private final AnswerInfoRepository repository;

    @Autowired
    public AnswerService(AnswerInfoRepository repository) {
        this.repository = repository;
    }

    public void save(QuestionTypeEnum questionTypeEnum, AnswerTypeEnum answerTypeEnum,
                     Long userId, Long paperId, Long questionId, String answer, Integer score) {

        AnswerInfo answerInfo = new AnswerInfo();
        answerInfo.setUserId(userId);
        answerInfo.setPaperId(paperId);
        answerInfo.setQuestionType(questionTypeEnum);
        answerInfo.setAnswerType(answerTypeEnum);
        answerInfo.setQuestionId(questionId);
        answerInfo.setAnswer(answer);
        answerInfo.setCreateTime(new Date());
        answerInfo.setScore(score);
        PaperValidator.notEmptyAnswerInfo(answerInfo);
        repository.save(answerInfo);
    }

    public List<AnswerInfo> findByPaperIdAndUserId(Long paperId, Long userId) {
        BaseValidator.notEmpty(paperId, "试卷 ID 不能为空");
        BaseValidator.notEmpty(userId, "用户 ID 不能为空");
        return repository.findByPaperIdAndUserId(paperId, userId);
    }

    public AnswerInfo findQuestionAnswer(Long paperId, Long userId, String questionType, Long questionId) {
        BaseValidator.notEmpty(paperId, "试卷 ID 不能为空");
        BaseValidator.notEmpty(userId, "用户 ID 不能为空");
        BaseValidator.notEmptyString(questionType, "问题类型不能为空");
        BaseValidator.notEmpty(questionId, "问题 ID 不能为空");
        return repository.findByPaperIdAndUserIdAndQuestionTypeAndQuestionId(paperId, userId, questionType, questionId);
    }

    public void update(Long id, String answer, Integer score) {
        BaseValidator.notEmpty(id, "答案 ID 不能为空");
        BaseValidator.notEmptyString(answer, "答案不能为空");
        BaseValidator.notEmpty(score, "分值不能为空");
        repository.update(id, answer,score);
    }

    public List<AnswerInfo> findByPaperIdAndQuestionTypeAndQuestionId(Long paperId, String questionType,
                                                                      Long questionId) {
        BaseValidator.notEmpty(paperId, "试卷 ID 不能为空");
        BaseValidator.notEmptyString(questionType, "问题类型不能为空");
        BaseValidator.notEmpty(questionId, "问题 ID 不能为空");
        return repository.findByPaperIdAndQuestionTypeAndQuestionId(paperId, questionType, questionId);
    }
}
