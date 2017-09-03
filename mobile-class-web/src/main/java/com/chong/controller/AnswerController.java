package com.chong.controller;

import com.chong.entity.*;
import com.chong.enums.AnswerTypeEnum;
import com.chong.enums.QuestionTypeEnum;
import com.chong.properties.RoleProperties;
import com.chong.service.*;
import com.chong.utils.CosineSimilarAlgorithm;
import com.chong.utils.UserUtil;
import com.chong.validator.BaseValidator;
import com.chong.vo.AnswerInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 答案 Web 控制器
 * Created by LXChild on 23/04/2017.
 */
@Controller
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;

    private final UserUtil userUtil;

    private final UserService userService;

    private final SingleSelService singleSelService;

    private final MultiSelService multiSelService;

    private final RightWrongService rightWrongService;

    private final FillingBlankService fillingBlankService;

    private final EssayService essayService;

    @Autowired
    public AnswerController(AnswerService answerService, UserUtil userUtil, UserService userService, SingleSelService singleSelService, MultiSelService multiSelService, RightWrongService rightWrongService, FillingBlankService fillingBlankService, EssayService essayService) {
        this.answerService = answerService;
        this.userUtil = userUtil;
        this.userService = userService;
        this.singleSelService = singleSelService;
        this.multiSelService = multiSelService;
        this.rightWrongService = rightWrongService;
        this.fillingBlankService = fillingBlankService;
        this.essayService = essayService;
    }

    @PostMapping("/singleSel")
    public String saveSingleSel(HttpServletRequest request, Long questionId, Long paperId, String answer) {
        SingleSel singleSel = singleSelService.findOne(questionId);
        BaseValidator.notEmpty(singleSel);

        Integer score = 0;
        if (singleSel.getAnswerR().equals(answer)) {
            score += singleSel.getScore();
        }

        Long userId = userUtil.getCurrentUserId(request);
        String questionType = QuestionTypeEnum.SINGLESEL.getTag();

        AnswerInfo answerInfo = answerService.findQuestionAnswer(paperId, userId, questionType, questionId);
        if (answerInfo != null) {
            answerService.update(answerInfo.getId(), answer, score);
        } else {
            answerService.save(QuestionTypeEnum.SINGLESEL, AnswerTypeEnum.TEXT, userId, paperId,
                    questionId, answer, score);
        }
        return "redirect:/papers/" + paperId;
    }

    @PostMapping("/multiSel")
    public String saveMultiSel(HttpServletRequest request, Long questionId, Long paperId, String answer) {
        MultiSel multiSel = multiSelService.findOne(questionId);
        BaseValidator.notEmpty(multiSel);
        Integer score = 0;
        if (multiSel.getAnswerR().equals(answer)) {
            score += multiSel.getScore();
        }

        Long userId = userUtil.getCurrentUserId(request);

        String questionType = QuestionTypeEnum.MULTISEL.getTag();

        AnswerInfo answerInfo = answerService.findQuestionAnswer(paperId, userId, questionType, questionId);
        if (answerInfo != null) {
            answerService.update(answerInfo.getId(), answer, score);
        } else {
            answerService.save(QuestionTypeEnum.MULTISEL, AnswerTypeEnum.TEXT, userId,
                    paperId, questionId, answer, score);
        }

        return "redirect:/papers/" + paperId;
    }

    @PostMapping("/rightWrong")
    public String saveRightWrong(HttpServletRequest request, Long questionId, Long paperId, String answer) {
        RightWrong rightWrong = rightWrongService.findOne(questionId);
        BaseValidator.notEmpty(rightWrong);
        Integer score = 0;
        if (rightWrong.getAnswer() == Boolean.valueOf(answer)) {
            score += rightWrong.getScore();
        }

        Long userId = userUtil.getCurrentUserId(request);

        String questionType = QuestionTypeEnum.RIGHTWRONG.getTag();

        AnswerInfo answerInfo = answerService.findQuestionAnswer(paperId, userId, questionType, questionId);
        if (answerInfo != null) {
            answerService.update(answerInfo.getId(), answer, score);
        } else {
            answerService.save(QuestionTypeEnum.RIGHTWRONG, AnswerTypeEnum.TEXT, userId,
                    paperId, questionId, answer, score);
        }
        return "redirect:/papers/" + paperId;
    }

    @PostMapping("/fillingBlank")
    public String saveFillingBlank(HttpServletRequest request, Long questionId, Long paperId, String answer) {
        FillingBlank fillingBlank = fillingBlankService.findOne(questionId);
        BaseValidator.notEmpty(fillingBlank);
        Integer score = 0;
        double similar = CosineSimilarAlgorithm.getSimilarity(fillingBlank.getAnswer(), answer);
        score += (int) (fillingBlank.getScore() * similar);

        Long userId = userUtil.getCurrentUserId(request);

        String questionType = QuestionTypeEnum.FILLINGBLANK.getTag();

        AnswerInfo answerInfo = answerService.findQuestionAnswer(paperId, userId, questionType, questionId);
        if (answerInfo != null) {
            answerService.update(answerInfo.getId(), answer, score);
        } else {
            answerService.save(QuestionTypeEnum.FILLINGBLANK, AnswerTypeEnum.TEXT, userId,
                    paperId, questionId, answer, score);
        }
        return "redirect:/papers/" + paperId;
    }

    @PostMapping("/essay")
    public String saveEssay(HttpServletRequest request, Long questionId, Long paperId, String answer) {
        Essay essay = essayService.findOne(questionId);
        BaseValidator.notEmpty(essay);
        Integer score = 0;

        double similar = CosineSimilarAlgorithm.getSimilarity(essay.getAnswer(), answer);
        score += (int) (essay.getScore() * similar);
        Long userId = userUtil.getCurrentUserId(request);

        String questionType = QuestionTypeEnum.ESSAY.getTag();

        AnswerInfo answerInfo = answerService.findQuestionAnswer(paperId, userId, questionType, questionId);
        if (answerInfo != null) {
            answerService.update(answerInfo.getId(), answer, score);
        } else {
            answerService.save(QuestionTypeEnum.ESSAY, AnswerTypeEnum.TEXT, userId,
                    paperId, questionId, answer, score);
        }
        return "redirect:/papers/" + paperId;
    }

    @GetMapping("/singleSel")
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String getSingleSelByPaperId(Long paperId, Long questionId, ModelMap map) {
        List<AnswerInfo> answerInfos = answerService.findByPaperIdAndQuestionTypeAndQuestionId(paperId,
                QuestionTypeEnum.SINGLESEL.getTag(), questionId);
        List<AnswerInfoVO> vos = new ArrayList<>();
        for (AnswerInfo info : answerInfos) {
            User user = userService.findOne(info.getUserId());
            vos.add(new AnswerInfoVO(info, user));
        }
        SingleSel singleSel = singleSelService.findOne(questionId);
        map.addAttribute("singleSel", singleSel);
        map.addAttribute("answerInfoVOs", vos);
        return "/paper/answer";
    }

    @GetMapping("/multiSel")
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String getMultiSelByPaperId(Long paperId, Long questionId, ModelMap map) {
        List<AnswerInfo> answerInfos = answerService.findByPaperIdAndQuestionTypeAndQuestionId(paperId,
                QuestionTypeEnum.MULTISEL.getTag(), questionId);
        List<AnswerInfoVO> vos = new ArrayList<>();
        for (AnswerInfo info : answerInfos) {
            User user = userService.findOne(info.getUserId());
            vos.add(new AnswerInfoVO(info, user));
        }
        MultiSel multiSel = multiSelService.findOne(questionId);
        map.addAttribute("multiSel", multiSel);
        map.addAttribute("answerInfoVOs", vos);
        return "/paper/answer";
    }

    @GetMapping("/rightWrong")
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String getRightWrongByPaperId(Long paperId, Long questionId, ModelMap map) {
        List<AnswerInfo> answerInfos = answerService.findByPaperIdAndQuestionTypeAndQuestionId(paperId,
                QuestionTypeEnum.RIGHTWRONG.getTag(), questionId);
        List<AnswerInfoVO> vos = new ArrayList<>();
        for (AnswerInfo info : answerInfos) {
            User user = userService.findOne(info.getUserId());
            vos.add(new AnswerInfoVO(info, user));
        }
        RightWrong rightWrong = rightWrongService.findOne(questionId);
        map.addAttribute("rightWrong", rightWrong);
        map.addAttribute("answerInfoVOs", vos);
        return "/paper/answer";
    }

    @GetMapping("/fillingBlank")
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String getFillingBlankByPaperId(Long paperId, Long questionId, ModelMap map) {
        List<AnswerInfo> answerInfos = answerService.findByPaperIdAndQuestionTypeAndQuestionId(paperId,
                QuestionTypeEnum.FILLINGBLANK.getTag(), questionId);
        List<AnswerInfoVO> vos = new ArrayList<>();
        for (AnswerInfo info : answerInfos) {
            User user = userService.findOne(info.getUserId());
            vos.add(new AnswerInfoVO(info, user));
        }
        FillingBlank fillingBlank = fillingBlankService.findOne(questionId);
        map.addAttribute("fillingBlank", fillingBlank);
        map.addAttribute("answerInfoVOs", vos);
        return "/paper/answer";
    }

    @GetMapping("/essay")
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String getEssayByPaperId(Long paperId, Long questionId, ModelMap map) {
        List<AnswerInfo> answerInfos = answerService.findByPaperIdAndQuestionTypeAndQuestionId(paperId,
                QuestionTypeEnum.ESSAY.getTag(), questionId);
        List<AnswerInfoVO> vos = new ArrayList<>();
        for (AnswerInfo info : answerInfos) {
            User user = userService.findOne(info.getUserId());
            vos.add(new AnswerInfoVO(info, user));
        }
        Essay essay = essayService.findOne(questionId);
        map.addAttribute("essay", essay);
        map.addAttribute("answerInfoVOs", vos);
        return "/paper/answer";
    }
}
