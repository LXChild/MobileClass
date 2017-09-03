package com.chong.controller;

import com.chong.entity.SingleSel;
import com.chong.properties.RoleProperties;
import com.chong.service.SingleSelService;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 单选题控制器
 * Created by LXChild on 22/04/2017.
 */
@Controller
@RequestMapping("/singleSels")
public class SingleSelController {

    private final SingleSelService singleSelService;


    @Autowired
    public SingleSelController(SingleSelService singleSelService) {
        this.singleSelService = singleSelService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String save(String paperId, String question, String answerA, String answerB, String answerC,
                                String answerD, String answerR, Integer score) {
        SingleSel singleSel = new SingleSel();
        singleSel.setPaperId(Long.valueOf(paperId));
        singleSel.setQuestion(question);
        singleSel.setAnswerA(answerA);
        singleSel.setAnswerB(answerB);
        singleSel.setAnswerC(answerC);
        singleSel.setAnswerD(answerD);
        singleSel.setAnswerR(answerR);
        singleSel.setScore(score);
        SingleSel result = singleSelService.save(singleSel);
        BaseValidator.notEmpty(result);
        return "redirect:/papers/" + paperId;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public void delete(@PathVariable("id") Long id) {
        singleSelService.delete(id);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String update(@PathVariable("id") Long id, String paperId, String question, String answerA,
                         String answerB, String answerC, String answerD, String answerR, Integer score) {
        singleSelService.update(id, Long.valueOf(paperId), question, answerA, answerB, answerC, answerD,
                answerR, score);
        return "redirect:/papers/" + paperId;
    }
}
