package com.chong.domain;

import com.chong.entity.PostImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 帖子图片数据库操作
 * Created by LXChild on 02/05/2017.
 */
public interface PostImgRepository extends JpaRepository<PostImg, Long> {

    List<PostImg> findByPostId(Long postId);
}
