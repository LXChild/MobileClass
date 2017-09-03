package com.chong.domain;

import com.chong.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 帖子相关 JPA 操作
 * Created by LXChild on 07/04/2017.
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * 根据创建者 ID 查询帖子
     * @param creatorId 创建者 ID
     * @return 对应帖子
     */
    List<Post> findByCreatorId(Long creatorId);

    Page<Post> findAllByTitle(String title, Pageable pageable);
}
