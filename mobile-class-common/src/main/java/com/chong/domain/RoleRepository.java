package com.chong.domain;

import com.chong.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 角色数据库操作
 * Created by LXChild on 24/04/2017.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findAllByComment(String comment);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Role set tag =:tag, comment =:comment, updateTime=:updateTime where id =:id")
    void update(@Param("id") Integer id, @Param("tag") String tag, @Param("comment") String comment,
                @Param("updateTime") Date updateTime);
}
