package com.chong.domain;

import com.chong.entity.SubPostImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 子贴图片数据库操作
 * Created by LXChild on 02/05/2017.
 */
public interface SubPostImgRepository extends JpaRepository<SubPostImg, Long> {

    List<SubPostImg> findBySubPostId(Long subPostId);
}
