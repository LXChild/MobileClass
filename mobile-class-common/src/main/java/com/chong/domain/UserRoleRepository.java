package com.chong.domain;

import com.chong.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 用户角色对应关系数据库
 * Created by LXChild on 24/04/2017.
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByUserId(Long userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update UserRole set roleId =:roleId, updateTime=:updateTime where userId =:userId")
    void update(@Param("userId") Long userId, @Param("roleId") Integer roleId, @Param("updateTime") Date updateTime);
}
