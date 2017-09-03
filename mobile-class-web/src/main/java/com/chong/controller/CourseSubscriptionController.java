package com.chong.controller;

import com.chong.entity.CourseSubscription;
import com.chong.entity.User;
import com.chong.properties.RoleProperties;
import com.chong.service.CourseSubscriptionService;
import com.chong.service.UserService;
import com.chong.utils.UserUtil;
import com.chong.validator.BaseValidator;
import com.chong.vo.CourseSubscriptionVO;
import com.chong.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程关注视图控制器
 * Created by LXChild on 26/04/2017.
 */
@Controller
@RequestMapping("/courseSubscription")
public class CourseSubscriptionController {

    private final CourseSubscriptionService subscriptionService;

    private final UserService userService;

    private final UserUtil userUtil;

    @Autowired
    public CourseSubscriptionController(CourseSubscriptionService subscriptionService, UserUtil userUtil, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.userUtil = userUtil;
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String toSubscriptionPage(Long courseId, ModelMap map) {
        map.addAttribute("courseId", courseId);
        return "/course/subscription";
    }

    @PostMapping
    @ResponseBody
    public Boolean saveSubscription(HttpServletRequest request, @RequestParam("courseId") Long courseId) {
        Long userId = userUtil.getCurrentUserId(request);
        BaseValidator.notEmpty(courseId);
        CourseSubscription subscriptionInRepository = subscriptionService.findSubscription(courseId, userId);
        if (subscriptionInRepository != null) {
            return false;
        }
        CourseSubscription subscription = new CourseSubscription(courseId, userId);
        BaseValidator.notEmpty(subscriptionService.save(subscription));
        return true;
    }

    @GetMapping("/course/{id}")
    @ResponseBody
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public TableVO<CourseSubscriptionVO> findByCourseId(@PathVariable("id") Long courseId,
                                                      int limit, int offset, String realName) {
        Integer page = offset / limit;
        Page<CourseSubscription> subscriptions = subscriptionService.findAllByCourseId(courseId, limit, page);
        List<CourseSubscriptionVO> subscriptionVOs = new ArrayList<>();
        for (CourseSubscription courseSubscription : subscriptions) {
            User user = userService.findOne(courseSubscription.getUserId());
            if (realName != null && "".equals(realName)) {
                if (user.getRealName().equals(realName)) {
                    subscriptionVOs.add(new CourseSubscriptionVO(courseSubscription, user));
                }
            } else {
                subscriptionVOs.add(new CourseSubscriptionVO(courseSubscription, user));
            }
        }
        return new TableVO<>((long) subscriptionVOs.size(), subscriptionVOs);
    }

    @PostMapping("/user/{id}")
    @ResponseBody
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public Boolean remove(Long courseId, @PathVariable("id") Long userId) {
        subscriptionService.deleteByCourseIdAndUserId(courseId, userId);
        return true;
    }

    @GetMapping("remove")
    public String removeSubscription(HttpServletRequest request, Long courseId) {
        subscriptionService.deleteByCourseIdAndUserId(courseId, userUtil.getCurrentUserId(request));
        return "redirect:/courses/" + courseId;
    }
}
