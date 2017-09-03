package com.chong.controller;

import com.chong.entity.SubPost;
import com.chong.entity.User;
import com.chong.service.SubPostService;
import com.chong.service.UserService;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 子贴视图控制器
 * Created by LXChild on 02/05/2017.
 */
@Controller
@RequestMapping("/subPosts")
public class SubPostController {

    private final SubPostService subPostService;


    private final UserService userService;

    @Autowired
    public SubPostController(SubPostService subPostService, UserService userService) {
        this.subPostService = subPostService;
        this.userService = userService;
    }

    @PostMapping
    public String save(HttpServletRequest request, Long parentId, String content, MultipartFile file) throws Exception {
        User user = userService.findByName(request.getRemoteUser());
        BaseValidator.notEmpty(user);
        BaseValidator.notEmpty(user.getId());

        SubPost subPost = new SubPost();
        subPost.setCreatorId(user.getId());
        subPost.setParentId(parentId);
        subPost.setContent(content);
        subPost.setCreateTime(new Date());
        subPostService.save(subPost, file);

        return "redirect:/posts/" + subPost.getParentId();
    }
}
