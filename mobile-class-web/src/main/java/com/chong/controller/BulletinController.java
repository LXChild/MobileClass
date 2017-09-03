package com.chong.controller;

import com.chong.entity.Bulletin;
import com.chong.enums.BulletinPositionEnum;
import com.chong.exception.ParamException;
import com.chong.properties.RoleProperties;
import com.chong.service.BulletinService;
import com.chong.utils.UserUtil;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 公告视图控制器
 * Created by LXChild on 28/04/2017.
 */
@Controller
@RequestMapping("/bulletins")
public class BulletinController {

    private final BulletinService bulletinService;

    private final UserUtil userUtil;

    @Autowired
    public BulletinController(BulletinService bulletinService, UserUtil userUtil) {
        this.bulletinService = bulletinService;
        this.userUtil = userUtil;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "')")
    public String save(HttpServletRequest request, String content, String position) {
        BaseValidator.notEmptyString(position);

        Bulletin bulletin;
        if (position.equals(BulletinPositionEnum.PAPER.getTag())) {
            bulletin = bulletinService.findByPosition(BulletinPositionEnum.PAPER);
        } else if (position.equals(BulletinPositionEnum.POST.getTag())){
            bulletin = bulletinService.findByPosition(BulletinPositionEnum.POST);
        } else {
            throw new ParamException.NullParamException("公告位置输入错误");
        }
        if (bulletin != null) {
            bulletinService.update(content, position, userUtil.getCurrentUserId(request));
        } else {
            bulletinService.save(userUtil.getCurrentUserId(request), position, content);
        }
        if (position.equals(BulletinPositionEnum.PAPER.getTag())) {
            return "redirect:/papers";
        } else if (position.equals(BulletinPositionEnum.POST.getTag())){
            return "redirect:/posts";
        } else {
            throw new ParamException.NullParamException("公告位置输入错误");
        }
    }
}
