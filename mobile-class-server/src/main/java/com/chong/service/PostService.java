package com.chong.service;

import com.chong.domain.PostRepository;
import com.chong.entity.Post;
import com.chong.exception.ParamException;
import com.chong.validator.PostValidator;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 帖子相关服务
 * Created by LXChild on 07/04/2017.
 */
@Service
public class PostService {

    private final PostRepository repository;
    private final UserService userService;

    @Autowired
    public PostService(PostRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public Post save(Post post) {
        PostValidator.notEmptyPost(post);
        return repository.save(post);
    }

    public List<Post> findByCreatorId(Long creatorId) {
        BaseValidator.notEmpty(creatorId, "创建者 ID 不能为空");
        if (userService.findOne(creatorId) == null) {
            throw new ParamException.NotInDBException(creatorId);
        }
        return repository.findByCreatorId(creatorId);
    }

    public List<Post> findAll() {
        // TODO page find
        return repository.findAll();
    }

    public Post findOne(Long id) {
        BaseValidator.notEmpty(id, "帖子 ID 不能为空");
        return repository.findOne(id);
    }
}
