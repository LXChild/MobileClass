package com.chong.controller;

import com.chong.dataobject.Result;
import com.chong.entity.Post;
import com.chong.entity.SubPost;
import com.chong.enums.ResultCode;
import com.chong.service.PostService;
import com.chong.service.SubPostService;
import com.chong.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 帖子控制器
 * Created by LXChild on 07/04/2017.
 */
@RestController
@RequestMapping("/api/v1/posts")
@Api(value = "试卷管理接口", protocols = "JSON")
public class PostController {

    private final PostService postService;

    private final SubPostService subPostService;

    @Autowired
    public PostController(PostService postService, SubPostService subPostService) {
        this.postService = postService;
        this.subPostService = subPostService;
    }

    @PostMapping
    @ApiParam(required = true, name = "Post", value = "帖子")
    @ApiOperation(value = "创建帖子", httpMethod = "POST", response = Result.class, notes = "创建帖子")
    public Result save(HttpServletRequest request, @RequestBody @Valid Post post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultCode.FAILED, String.valueOf(request.getRequestURL()),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(request.getRequestURI(), postService.save(post));
    }

    @PostMapping("/sub")
    @ApiParam(required = true, name = "SubPost", value = "子贴")
    @ApiOperation(value = "创建子贴", httpMethod = "POST", response = Result.class, notes = "创建子贴")
    public Result saveSub(HttpServletRequest request, @RequestBody @Valid SubPost subPost, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultCode.FAILED, String.valueOf(request.getRequestURL()),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(request.getRequestURI(), subPostService.save(subPost));
    }

    @GetMapping
    @ApiOperation(value = "获取所有帖子", httpMethod = "GET", response = Result.class, notes = "获取所有帖子")
    public Result get(HttpServletRequest request) {
        return ResultUtils.success(request.getRequestURI(), postService.findAll());
    }

//    @GetMapping("/{id}")
//    @ApiParam(required = true, name = "id", value = "帖子 ID")
//    @ApiOperation(value = "根据 ID 获取对应帖子", httpMethod = "GET", response = Result.class, notes = "根据 ID 获取对应帖子")
//    public Result findById(HttpServletRequest request, @PathVariable("id") Long id) {
//        List<SubPost> subPosts = subPostService.findByParentId(id);
//        PostDO paperDO = new PostDO(postService.findOne(id), subPosts);
//        return ResultUtils.success(request.getRequestURI(), paperDO);
//    }

    @GetMapping("/creator/{creatorId}")
    @ApiParam(required = true, name = "creatorId", value = "创建者 ID")
    @ApiOperation(value = "根据创建者 ID 获取对应帖子", httpMethod = "GET", response = Result.class,
            notes = "根据创建者 ID 获取对应帖子")
    public Result findByCreatorId(HttpServletRequest request, @PathVariable("creatorId") Long creatorId) {
        return ResultUtils.success(request.getRequestURI(), postService.findByCreatorId(creatorId));
    }
}
