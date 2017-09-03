package com.chong.controller;

import com.chong.dataobject.PostDO;
import com.chong.dataobject.SubPostDO;
import com.chong.entity.*;
import com.chong.enums.BulletinPositionEnum;
import com.chong.service.*;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 帖子控制器
 * Created by LXChild on 15/04/2017.
 */
@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    private final UserService userService;

    private final SubPostService subPostService;

    private final BulletinService bulletinService;

    private final PostImgService postImgService;

    private final SubPostImgService subPostImgService;

    @Value("${course.courseware.request-path}")
    private String requestPath;

    @Autowired
    public PostController(PostService postService, UserService userService, SubPostService subPostService,
                          BulletinService bulletinService, PostImgService postImgService, SubPostImgService subPostImgService) {
        this.postService = postService;
        this.userService = userService;
        this.subPostService = subPostService;
        this.bulletinService = bulletinService;
        this.postImgService = postImgService;
        this.subPostImgService = subPostImgService;
    }

    @GetMapping
    public String findAll(HttpServletRequest request, ModelMap map, String title) {
        String pageNum = request.getParameter("pageNum");

        Page<Post> datas;
        if (title != null && !"".equals(title)) {
            datas = postService.findAllByTitle((pageNum == null || "".equals(pageNum)) ? 0 : Integer.valueOf(pageNum) - 1,
                    title);
        } else {
            datas = postService.findAll((pageNum == null || "".equals(pageNum)) ? 0 : Integer.valueOf(pageNum) - 1);
        }
        map.addAttribute("pageNum", datas.getNumber() + 1);
        map.addAttribute("pageSize", datas.getSize());
        map.addAttribute("isFirstPage", datas.isFirst());
        map.addAttribute("isLastPage", datas.isLast());
        map.addAttribute("totalPages", datas.getTotalPages() == 0 ? 1 : datas.getTotalPages());
        map.addAttribute("searchText", title == null ? "" : title);
        map.addAttribute("posts", datas);

        Bulletin bulletin = bulletinService.findByPosition(BulletinPositionEnum.POST);

        map.addAttribute("bulletin", bulletin == null ? "尚未发布公告" : bulletin.getContent());
        return "/post/list";
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public String save(HttpServletRequest request, String title, String content, MultipartFile file) throws Exception {
        String username = request.getRemoteUser();
        BaseValidator.notEmptyString(username);

        User user = userService.findByName(username);
        BaseValidator.notEmpty(user);
        BaseValidator.notEmpty(user.getId());

        Post post = new Post();
        post.setCreatorId(user.getId());
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());

        Post result = postService.save(post, file);
        BaseValidator.notEmpty(result);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String findOne(HttpServletRequest request, ModelMap map, @PathVariable("id") Long id) {
        String page = request.getParameter("pageNum");
        Integer pageNum = (page == null || "".equals(page)) ? 0 : Integer.valueOf(page) - 1;
        Page<SubPost> subPosts = subPostService.findByParentId(id, pageNum);

        List<PostImg> postImgs = postImgService.findByPostId(id);

        for (PostImg postImg : postImgs) {
            postImg.setName(requestPath + postImg.getName());
        }

        List<SubPostDO> subPostDOs = new ArrayList<>();
        for (SubPost subPost : subPosts) {
            List<SubPostImg> subPostImgs = subPostImgService.findBySubPostId(subPost.getId());
            for (SubPostImg subPostImg : subPostImgs) {
                subPostImg.setName(requestPath + subPostImg.getName());
            }
            subPostDOs.add(new SubPostDO(subPost, subPostImgs));
        }

        PostDO postDO = new PostDO(postService.findOne(id), subPostDOs);

        map.addAttribute("pageNum", subPosts.getNumber() + 1);
        map.addAttribute("pageSize", subPosts.getSize());
        map.addAttribute("isFirstPage", subPosts.isFirst());
        map.addAttribute("isLastPage", subPosts.isLast());
        map.addAttribute("totalPages", subPosts.getTotalPages() == 0 ? 1 : subPosts.getTotalPages());
        map.addAttribute("postDO", postDO);
        map.addAttribute("postImgs", postImgs);
        return "/post/detail";
    }
}
