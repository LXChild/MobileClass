package com.chong.service;

import com.chong.domain.PostImgRepository;
import com.chong.entity.PostImg;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 帖子图片服务
 * Created by LXChild on 02/05/2017.
 */
@Service
public class PostImgService {

    private final PostImgRepository repository;

    @Autowired
    public PostImgService(PostImgRepository repository) {
        this.repository = repository;
    }

    public List<PostImg> findByPostId(Long postId) {
        BaseValidator.notEmpty(postId);
        return repository.findByPostId(postId);
    }

    public PostImg save(PostImg postImg) {
        BaseValidator.notEmptyString(postImg.getName());
        BaseValidator.notEmpty(postImg.getPostId());
        BaseValidator.notEmpty(postImg.getCreatorId());
        return repository.save(postImg);
    }
}
