package com.chong.service;

import com.chong.domain.SubPostRepository;
import com.chong.entity.SubPost;
import com.chong.exception.ParamException;
import com.chong.validator.PostValidator;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 子贴相关服务
 * Created by LXChild on 07/04/2017.
 */
@Service
public class SubPostService {

    private final PostService postService;
    private final UserService userService;
    private final SubPostRepository repository;

    @Autowired
    public SubPostService(SubPostRepository repository, PostService postService, UserService userService) {
        this.repository = repository;
        this.postService = postService;
        this.userService = userService;
    }

    public SubPost save(SubPost subPost) {
        PostValidator.notEmptySubPost(subPost);
        if (postService.findOne(subPost.getParentId()) == null
                || userService.findOne(subPost.getCreatorId()) == null) {
            throw new ParamException.NotInDBException("帖子 ID／用户 ID 异常");
        }
        return repository.save(subPost);
    }

    public List<SubPost> findByParentId(Long parentId) {
        BaseValidator.notEmpty(parentId, "主贴 ID 不能为空");
        return repository.findByParentId(parentId);
    }
}
