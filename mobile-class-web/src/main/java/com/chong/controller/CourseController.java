package com.chong.controller;

import com.chong.dataobject.CourseDO;
import com.chong.entity.Course;
import com.chong.entity.CourseSubscription;
import com.chong.entity.Courseware;
import com.chong.entity.User;
import com.chong.properties.RoleProperties;
import com.chong.service.CourseService;
import com.chong.service.CourseSubscriptionService;
import com.chong.service.CoursewareService;
import com.chong.service.UserService;
import com.chong.utils.UserUtil;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 课程控制器
 * Created by LXChild on 18/04/2017.
 */
@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    private final UserService userService;

    private final CoursewareService coursewareService;

    private final CourseSubscriptionService subscriptionService;

    private final UserUtil userUtil;

    @Autowired
    public CourseController(CourseService courseService, UserService userService, CoursewareService coursewareService,
                            CourseSubscriptionService subscriptionService, UserUtil userUtil) {
        this.courseService = courseService;
        this.userService = userService;
        this.coursewareService = coursewareService;
        this.subscriptionService = subscriptionService;
        this.userUtil = userUtil;
    }

    @GetMapping
    public String findAll(HttpServletRequest request, ModelMap map, String name) {
        String pageNum = request.getParameter("pageNum");
        Page<Course> datas;
        if (name != null && !"".equals(name)) {
            datas = courseService.findAllByName((pageNum == null || "".equals(pageNum)) ? 0 : Integer.valueOf(pageNum) - 1,
                    name);
        } else {
            datas = courseService.findAll((pageNum == null || "".equals(pageNum)) ? 0 : Integer.valueOf(pageNum) - 1);
        }
        map.addAttribute("pageNum", datas.getNumber() + 1);
        map.addAttribute("pageSize", datas.getSize());
        map.addAttribute("isFirstPage", datas.isFirst());
        map.addAttribute("isLastPage", datas.isLast());
        map.addAttribute("totalPages", datas.getTotalPages() == 0 ? 1 : datas.getTotalPages());
        map.addAttribute("searchText", name == null ? "" : name);
        map.addAttribute("courses", datas);
        map.addAttribute("bulletin", "在学的都是弱🐔🐔");
        return "/course/list";
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String save(HttpServletRequest request, @RequestParam("name") String name,
                       @RequestParam("introduction") String introduction) {
        String teacherName = request.getRemoteUser();
        BaseValidator.notEmptyString(teacherName);
        User user = userService.findByName(teacherName);
        BaseValidator.notEmpty(user);
        Course course = new Course();
        course.setTeacherId(user.getId());
        course.setTeacherName(teacherName);
        course.setName(name);
        course.setIntroduction(introduction);
        course.setCreateTime(new Date());
        Course result = courseService.save(course);
        BaseValidator.notEmpty(result);
        return "redirect:/courses";
    }

    @GetMapping("/{id}")
    public String findOne(HttpServletRequest request, ModelMap map, @PathVariable("id") Long id) {

        Long userId = userUtil.getCurrentUserId(request);

        BaseValidator.notEmpty(id);
        Course course = courseService.findOne(id);
        BaseValidator.notEmpty(course);

        List<Courseware> coursewares = coursewareService.findByCourseId(id);

        CourseDO courseDO = new CourseDO(course, coursewares);

        CourseSubscription subscriptionInRepository = subscriptionService.findSubscription(id, userId);
        if (subscriptionInRepository != null) {
            map.addAttribute("subscription", true);
        } else {
            map.addAttribute("subscription", false);
        }

        if (!Objects.equals(userId, course.getTeacherId())) {
            map.addAttribute("isCreator", false);
        } else {
            map.addAttribute("isCreator", true);
        }

        map.addAttribute("courseDO", courseDO);
        map.addAttribute("videoURL", "/");
        return "/course/detail";
    }

    @GetMapping("/mine")
    public String findAllBySubscriptionUserId(HttpServletRequest request, ModelMap map) {
        String pageNum = request.getParameter("pageNum");
        Page<CourseSubscription> datas = subscriptionService.findAllByUserId(userUtil.getCurrentUserId(request),
                (pageNum == null || "".equals(pageNum)) ? 0 : Integer.valueOf(pageNum) - 1);
        List<Course> courses = new ArrayList<>();
        for (CourseSubscription subscription : datas) {
            courses.add(courseService.findOne(subscription.getCourseId()));
        }
        map.addAttribute("pageNum", datas.getNumber() + 1);
        map.addAttribute("pageSize", datas.getSize());
        map.addAttribute("isFirstPage", datas.isFirst());
        map.addAttribute("isLastPage", datas.isLast());
        map.addAttribute("totalPages", datas.getTotalPages() == 0 ? 1 : datas.getTotalPages());
        map.addAttribute("courses", courses);
        return "/mine/course";
    }
}
