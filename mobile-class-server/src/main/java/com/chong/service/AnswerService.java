package com.chong.service;

import com.chong.domain.AnswerInfoRepository;
import com.chong.domain.AnswerTypeRepository;
import com.chong.domain.QuestionTypeRepository;
import com.chong.entity.AnswerInfo;
import com.chong.entity.AnswerType;
import com.chong.entity.QuestionType;
import com.chong.exception.ParamException;
import com.chong.validator.BaseValidator;
import com.chong.validator.PaperValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 答案服务
 * Created by LXChild on 06/04/2017.
 */
@Service
public class AnswerService {

    private final PaperService paperService;
    private final UserService userService;
    private final QuestionTypeRepository questionTypeRepository;
    private final AnswerTypeRepository answerTypeRepository;
    private final AnswerInfoRepository answerInfoRepository;

    @Autowired
    public AnswerService(PaperService paperService, UserService userService, QuestionTypeRepository questionTypeRepository,
                         AnswerTypeRepository answerTypeRepository, AnswerInfoRepository answerInfoRepository) {
        this.paperService = paperService;
        this.userService = userService;
        this.questionTypeRepository = questionTypeRepository;
        this.answerTypeRepository = answerTypeRepository;
        this.answerInfoRepository = answerInfoRepository;
    }

    public AnswerInfo save(AnswerInfo answerInfo) {
        PaperValidator.notEmptyAnswerInfo(answerInfo);
        if (paperService.findOne(answerInfo.getPaperId()) == null
                || userService.findOne(answerInfo.getUserId()) == null) {
            throw new ParamException.NotInDBException("试卷 ID／用户 ID 异常");
        }
        return answerInfoRepository.save(answerInfo);
    }

    public List<AnswerInfo> findByPaperIdAndUserId(Long paperId, Long userId) {
        BaseValidator.notEmpty(paperId, "试卷 ID 不能为空");
        BaseValidator.notEmpty(userId, "用户 ID 不能为空");
        return answerInfoRepository.findByPaperIdAndUserId(paperId, userId);
    }

    public QuestionType findQuestionTypeById(Integer questionTypeId) {
        BaseValidator.notEmpty(questionTypeId, "问题类型 ID 不能为空");
        return questionTypeRepository.findOne(questionTypeId);
    }

    public AnswerType findAnswerTypeById(Integer answerTypeId) {
        BaseValidator.notEmpty(answerTypeId, "答案类型 ID 不能为空");
        return answerTypeRepository.findOne(answerTypeId);
    }
}
