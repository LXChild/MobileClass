package com.chong.controller;

import com.chong.entity.FillingBlank;
import com.chong.properties.RoleProperties;
import com.chong.service.FillingBlankService;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 填空题控制器
 * Created by LXChild on 22/04/2017.
 */
@Controller
@RequestMapping("/fillingBlanks")
public class FillingBlankController {

    private final FillingBlankService fillingBlankService;

    @Autowired
    public FillingBlankController(FillingBlankService fillingBlankService) {
        this.fillingBlankService = fillingBlankService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String saveFillingBlank(String paperId, String question,  String answer, Integer score) {
        FillingBlank fillingBlank = new FillingBlank();
        fillingBlank.setPaperId(Long.valueOf(paperId));
        fillingBlank.setQuestion(question);
        fillingBlank.setAnswer(answer);
        fillingBlank.setScore(score);
        FillingBlank result = fillingBlankService.save(fillingBlank);
        BaseValidator.notEmpty(result);
        return "redirect:/papers/" + paperId;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public void delete(@PathVariable("id") Long id) {
        fillingBlankService.delete(id);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String update(@PathVariable("id") Long id, String paperId, String question, String answer, Integer score) {
        fillingBlankService.update(id, Long.valueOf(paperId), question, answer, score);
        return "redirect:/papers/" + paperId;
    }
}
