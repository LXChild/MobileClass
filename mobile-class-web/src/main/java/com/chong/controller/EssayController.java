package com.chong.controller;

import com.chong.entity.Essay;
import com.chong.properties.RoleProperties;
import com.chong.service.EssayService;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 简答题控制器
 * Created by LXChild on 22/04/2017.
 */
@Controller
@RequestMapping("/essays")
public class EssayController {

    private final EssayService essayService;

    @Autowired
    public EssayController(EssayService essayService) {
        this.essayService = essayService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String saveEssay(String paperId, String question,  String answer, Integer score) {
        Essay essay = new Essay();
        essay.setPaperId(Long.valueOf(paperId));
        essay.setQuestion(question);
        essay.setAnswer(answer);
        essay.setScore(score);
        Essay result = essayService.save(essay);
        BaseValidator.notEmpty(result);
        return "redirect:/papers/" + paperId;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public void delete(@PathVariable("id") Long id) {
        essayService.delete(id);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String update(@PathVariable("id") Long id, String paperId, String question, String answer, Integer score) {
        essayService.update(id, Long.valueOf(paperId), question, answer, score);
        return "redirect:/papers/" + paperId;
    }
}
