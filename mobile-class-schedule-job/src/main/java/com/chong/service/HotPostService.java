package com.chong.service;

import com.chong.domain.HotPostRepository;
import com.chong.entity.HotPost;
import com.chong.validator.HotItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 热门帖子服务
 * Created by LXChild on 20/04/2017.
 */
@Service
public class HotPostService {

    private final HotPostRepository repository;

    @Autowired
    public HotPostService(HotPostRepository repository) {
        this.repository = repository;
    }

    public HotPost save(HotPost hotPost) {
        HotItemValidator.notEmptyHotPost(hotPost);
        return repository.save(hotPost);
    }

    public List<HotPost> findAll() {
        return repository.findAll();
    }

    public void updateHotPost(Integer rank, HotPost hotPost) {
        HotItemValidator.notEmptyHotPost(hotPost);
        repository.updateHotPost(rank, hotPost.getPostId(), hotPost.getHeat(), hotPost.getCreateTime());
    }
}
