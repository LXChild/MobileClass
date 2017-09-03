package com.chong.controller;

import com.chong.entity.MultiSel;
import com.chong.properties.RoleProperties;
import com.chong.service.MultiSelService;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 多选题控制器
 * Created by LXChild on 22/04/2017.
 */
@Controller
@RequestMapping("/multiSels")
public class MultiSelController {

    private final MultiSelService multiSelService;

    @Autowired
    public MultiSelController(MultiSelService multiSelService) {
        this.multiSelService = multiSelService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String saveMultiSel(String paperId, String question, String answerA, String answerB, String answerC,
                               String answerD, String answerE, String answerF, String answerR, Integer score) {
        MultiSel multiSel = new MultiSel();
        multiSel.setPaperId(Long.valueOf(paperId));
        multiSel.setQuestion(question);
        multiSel.setAnswerA(answerA);
        multiSel.setAnswerB(answerB);
        multiSel.setAnswerC(answerC);
        multiSel.setAnswerD(answerD);
        multiSel.setAnswerE(answerE);
        multiSel.setAnswerF(answerF);
        multiSel.setAnswerR(answerR);
        multiSel.setScore(score);
        MultiSel result = multiSelService.save(multiSel);
        BaseValidator.notEmpty(result);
        return "redirect:/papers/" + paperId;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public void delete(@PathVariable("id") Long id) {
        multiSelService.delete(id);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String update(@PathVariable("id") Long id, String paperId, String question, String answerA,
                         String answerB, String answerC, String answerD, String answerE, String answerF,
                         String answerR, Integer score) {
        multiSelService.update(id, Long.valueOf(paperId), question, answerA, answerB, answerC, answerD, answerE,
                answerF, answerR, score);
        return "redirect:/papers/" + paperId;
    }
}
