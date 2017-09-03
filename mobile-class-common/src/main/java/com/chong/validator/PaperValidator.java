package com.chong.validator;

import com.chong.dataobject.PaperDO;
import com.chong.entity.*;

import java.util.List;

/**
 * 试卷验证器
 * Created by LXChild on 05/04/2017.
 */
public final class PaperValidator {

    private static final String NOT_NULL_PAPER_ID = "所属试卷 ID 不能为空";
    private static final String NOT_NULL_QUESTION = "问题不能为空";
    private static final String NOT_NULL_ANSWER = "答案不能为空";
    private static final String NOT_NULL_SCORE = "分值不能为空";

    private PaperValidator() {}

    public static void notEmptyPaperDO(PaperDO paperDO) {
        notEmptyPaper(paperDO.getPaper());

        List<SingleSel> singleSels = paperDO.getSingleSels();
        for (SingleSel singleSel : singleSels) {
            notEmptySingleSel(singleSel);
        }

        List<MultiSel> multiSels = paperDO.getMultiSels();
        for (MultiSel multiSel : multiSels) {
            notEmptyMultiSel(multiSel);
        }

        List<RightWrong> rightWrongs = paperDO.getRightWrongs();
        for (RightWrong rightWrong : rightWrongs) {
            notEmptyRightWrong(rightWrong);
        }

        List<FillingBlank> fillingBlanks = paperDO.getFillingBlanks();
        for (FillingBlank fillingBlank : fillingBlanks) {
            notEmptyFillingBlank(fillingBlank);
        }

        List<Essay> essays = paperDO.getEssays();
        for (Essay essay : essays) {
            notEmptyEssay(essay);
        }
    }

    public static void notEmptyPaper(Paper paper) {
        BaseValidator.notEmpty(paper, "试卷信息不能为空");
        BaseValidator.notEmptyString(paper.getName(), "试卷名不能为空");
        BaseValidator.notEmpty(paper.getCourseId(), "课程 ID 不能为空");
        BaseValidator.notEmpty(paper.getCreatorId(), "密码不能为空");
        BaseValidator.notEmpty(paper.getCreateTime(), "创建时间不能为空");
        BaseValidator.notEmpty(paper.getUpdateTime(), "更新时间不能为空");
        BaseValidator.notEmpty(paper.getQuizTime(), "考试时间不能为空");
        BaseValidator.notEmpty(paper.getQuizDuration(), "考试时长不能为空");
    }

    public static void notEmptySingleSel(SingleSel singleSel) {
        BaseValidator.notEmpty(singleSel, "单选题信息不能为空");
        BaseValidator.notEmpty(singleSel.getPaperId(), NOT_NULL_PAPER_ID);
        BaseValidator.notEmptyString(singleSel.getQuestion(), NOT_NULL_QUESTION);
        BaseValidator.notEmptyString(singleSel.getAnswerA(), "选项 A 不能为空");
        BaseValidator.notEmptyString(singleSel.getAnswerB(), "选项 B 不能为空");
        BaseValidator.notEmptyString(singleSel.getAnswerC(), "选项 C 不能为空");
        BaseValidator.notEmptyString(singleSel.getAnswerD(), "选项 D 不能为空");
        BaseValidator.notEmptyString(singleSel.getAnswerR(), "正确答案不能为空");
        BaseValidator.notEmpty(singleSel.getScore(), NOT_NULL_SCORE);
    }

    public static void notEmptyMultiSel(MultiSel multiSel) {
        BaseValidator.notEmpty(multiSel, "多选题信息不能为空");
        BaseValidator.notEmpty(multiSel.getPaperId(), NOT_NULL_PAPER_ID);
        BaseValidator.notEmptyString(multiSel.getQuestion(), NOT_NULL_QUESTION);
        BaseValidator.notEmptyString(multiSel.getAnswerA(), "选项 A 不能为空");
        BaseValidator.notEmptyString(multiSel.getAnswerB(), "选项 B 不能为空");
        BaseValidator.notEmptyString(multiSel.getAnswerC(), "选项 C 不能为空");
        BaseValidator.notEmptyString(multiSel.getAnswerD(), "选项 D 不能为空");
        BaseValidator.notEmptyString(multiSel.getAnswerE(), "选项 E 不能为空");
        BaseValidator.notEmptyString(multiSel.getAnswerF(), "选项 F 不能为空");
        BaseValidator.notEmptyString(multiSel.getAnswerR(), "正确答案不能为空");
        BaseValidator.notEmpty(multiSel.getScore(), NOT_NULL_SCORE);
    }

    public static void notEmptyRightWrong(RightWrong rightWrong) {
        BaseValidator.notEmpty(rightWrong, "对错题信息不能为空");
        BaseValidator.notEmpty(rightWrong.getPaperId(), NOT_NULL_PAPER_ID);
        BaseValidator.notEmptyString(rightWrong.getQuestion(), NOT_NULL_QUESTION);
        BaseValidator.notEmpty(rightWrong.getAnswer(), NOT_NULL_ANSWER);
        BaseValidator.notEmpty(rightWrong.getScore(), NOT_NULL_SCORE);
    }

    public static void notEmptyFillingBlank(FillingBlank fillingBlank) {
        BaseValidator.notEmpty(fillingBlank, "填空题信息不能为空");
        BaseValidator.notEmpty(fillingBlank.getPaperId(), NOT_NULL_PAPER_ID);
        BaseValidator.notEmptyString(fillingBlank.getQuestion(), NOT_NULL_QUESTION);
        BaseValidator.notEmptyString(fillingBlank.getAnswer(), NOT_NULL_ANSWER);
        BaseValidator.notEmpty(fillingBlank.getScore(), NOT_NULL_SCORE);
    }

    public static void notEmptyEssay(Essay essay) {
        BaseValidator.notEmpty(essay, "简答题信息不能为空");
        BaseValidator.notEmpty(essay.getPaperId(), NOT_NULL_PAPER_ID);
        BaseValidator.notEmptyString(essay.getQuestion(), NOT_NULL_QUESTION);
        BaseValidator.notEmptyString(essay.getAnswer(), NOT_NULL_ANSWER);
        BaseValidator.notEmpty(essay.getScore(), NOT_NULL_SCORE);
    }

    public static void notEmptyAnswerInfo(AnswerInfo answerInfo) {
        BaseValidator.notEmpty(answerInfo, "答案详情不能为空");
        BaseValidator.notEmpty(answerInfo.getPaperId(), "试卷 ID 不能为空");
        BaseValidator.notEmpty(answerInfo.getUserId(), "用户 ID 不能为空");
        BaseValidator.notEmpty(answerInfo.getQuestionTypeId(), "问题类型 ID 不能为空");
        BaseValidator.notEmptyString(answerInfo.getQuestionType(), "问题类型不能为空");
        BaseValidator.notEmpty(answerInfo.getQuestionId(), "问题 ID 不能为空");
        BaseValidator.notEmpty(answerInfo.getAnswerTypeId(), "答案类型 ID 不能为空");
        BaseValidator.notEmptyString(answerInfo.getAnswerType(), "答案类型不能为空");
        BaseValidator.notEmptyString(answerInfo.getAnswer(), "答案不能为空");
        BaseValidator.notEmpty(answerInfo.getScore(), NOT_NULL_SCORE);
        BaseValidator.notEmpty(answerInfo.getCreateTime(), "交卷时间不能为空");
    }
}
