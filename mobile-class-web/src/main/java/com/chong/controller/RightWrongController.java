package com.chong.controller;

import com.chong.entity.RightWrong;
import com.chong.properties.RoleProperties;
import com.chong.service.RightWrongService;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 对错题控制器
 * Created by LXChild on 22/04/2017.
 */
@Controller
@RequestMapping("/rightWrongs")
public class RightWrongController {

    private final RightWrongService rightWrongService;

    @Autowired
    public RightWrongController(RightWrongService rightWrongService) {
        this.rightWrongService = rightWrongService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String save(String paperId, String question,  String answer, Integer score) {
        RightWrong rightWrong = new RightWrong();
        rightWrong.setPaperId(Long.valueOf(paperId));
        rightWrong.setQuestion(question);
        rightWrong.setAnswer(Boolean.valueOf(answer));
        rightWrong.setScore(score);
        RightWrong result = rightWrongService.save(rightWrong);
        BaseValidator.notEmpty(result);
        return "redirect:/papers/" + paperId;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public void delete(@PathVariable("id") Long id) {
        rightWrongService.delete(id);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String update(@PathVariable("id") Long id, String paperId, String question, String answer, Integer score) {
        rightWrongService.update(id, Long.valueOf(paperId), question, Boolean.valueOf(answer), score);
        return "redirect:/papers/" + paperId;
    }
}
