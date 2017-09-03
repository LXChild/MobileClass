package com.chong.domain;

import com.chong.entity.SubPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 子贴相关 JPA 操作
 * Created by LXChild on 07/04/2017.
 */
public interface SubPostRepository extends JpaRepository<SubPost, Long> {

    /**
     * 根据主贴 ID 查询子贴
     * @param parentId 所属主贴 ID
     * @return 相应子贴
     */
    List<SubPost> findByParentId(Long parentId);

    /**
     * 根据主贴 ID 分页查询子贴
     * @param parentId 所属主贴 ID
     * @param pageable 分页属性 ID
     * @return 相应子贴
     */
    Page<SubPost> findByParentId(Long parentId, Pageable pageable);

    /**
     * 根据回复数量和创建时间查询最热门的 5 个帖子
     * @return 热门帖子
     */
    @Query(value = "SELECT parent_id AS post_id, count(*) AS heat FROM sub_post GROUP BY parent_id" +
            " ORDER BY count(*) DESC, max(create_time) DESC LIMIT 5", nativeQuery = true)
    List<Object[]> findHotPosts();
}
