package com.chong.service;

import com.chong.domain.SubPostRepository;
import com.chong.entity.HotPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 子贴服务
 * Created by LXChild on 20/04/2017.
 */
@Service
public class SubPostService {

    private final SubPostRepository repository;

    @Autowired
    public SubPostService(SubPostRepository repository) {
        this.repository = repository;
    }

    public List<HotPost> findHotPosts() {
        List<Object[]> hotPostDO = repository.findHotPosts();
        List<HotPost> hotPosts = new ArrayList<>();
        for (Object[] result : hotPostDO) {
            Long postId = ((Number) result[0]).longValue();
            Long heat = ((Number) result[1]).longValue();
            hotPosts.add(new HotPost(postId, heat));
        }
        return hotPosts;
    }
}
