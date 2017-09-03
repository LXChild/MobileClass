package com.chong.service;

import com.chong.domain.SubPostImgRepository;
import com.chong.entity.SubPostImg;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 帖子图片服务
 * Created by LXChild on 02/05/2017.
 */
@Service
public class SubPostImgService {

    private final SubPostImgRepository repository;

    @Autowired
    public SubPostImgService(SubPostImgRepository repository) {
        this.repository = repository;
    }

    public List<SubPostImg> findBySubPostId(Long postId) {
        BaseValidator.notEmpty(postId);
        return repository.findBySubPostId(postId);
    }

    public SubPostImg save(SubPostImg subPostImg) {
        BaseValidator.notEmptyString(subPostImg.getName());
        BaseValidator.notEmpty(subPostImg.getSubPostId());
        BaseValidator.notEmpty(subPostImg.getCreatorId());
        return repository.save(subPostImg);
    }
}
