package com.chong.controller;

import com.chong.entity.Courseware;
import com.chong.exception.StorageException;
import com.chong.properties.RoleProperties;
import com.chong.service.CoursewareService;
import com.chong.utils.UserUtil;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 课件视图控制器
 * Created by LXChild on 29/04/2017.
 */
@Controller
@RequestMapping("/coursewares")
public class CoursewareController {

    private final CoursewareService coursewareService;

    private final UserUtil userUtil;

    @Autowired
    public CoursewareController(CoursewareService coursewareService, UserUtil userUtil) {
        this.coursewareService = coursewareService;
        this.userUtil = userUtil;
    }

    @GetMapping("/{id}")
    public String findOneCourseware(@PathVariable("id") Long id) {
        Courseware courseware = coursewareService.findOne(id);
        BaseValidator.notEmpty(courseware);
        BaseValidator.notEmpty(courseware.getCourseId());
        return "redirect:/courses/" + courseware.getCourseId();
    }

    @GetMapping("/name/{name}")
    public String downloadCourseware(@PathVariable("name") String name)
            throws StorageException.StorageFileException {
        Courseware courseware = coursewareService.findByName(name);
        BaseValidator.notEmpty(courseware);
        BaseValidator.notEmpty(courseware.getCourseId());
        return "redirect:https://view.officeapps.live.com/op/view.aspx?src=newteach.pbworks.com%2Ff%2Fele%2Bnewsletter.docx";
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String save(HttpServletRequest request, MultipartFile file, Long courseId) throws Exception {
        BaseValidator.notEmpty(coursewareService.save(userUtil.getCurrentUserId(request), courseId, file));
        return "redirect:/courses/" + courseId;
    }
}
