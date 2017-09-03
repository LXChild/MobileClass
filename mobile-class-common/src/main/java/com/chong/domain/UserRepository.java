package com.chong.domain;

import com.chong.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 用户 JPA 操作
 * Created by LXChild on 03/04/2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 通过用户名进行查询
     * @param name 用户名
     * @return 查询出来的用户
     */
    User findByName(String name);

    /**
     * 根据用户真实姓名进行分页查询
     * @param realName 用户真实姓名
     * @param pageable 分页数据
     * @return 分页查询结果
     */
    Page<User> findAllByRealName(String realName, Pageable pageable);

    /**
     * 分页查询
     * @param pageable 分页数据
     * @return 分页查询结果
     */
    Page<User> findAll(Pageable pageable);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User set name =:name, mobile =:mobile, email =:email, enable =:enable," +
            "realName =:realName, updateTime=:updateTime where id =:id")
    void update(@Param("id") Long id, @Param("name") String name, @Param("mobile") String mobile,
                @Param("email") String email, @Param("enable") Boolean enable,
                @Param("realName") String realName,@Param("updateTime") Date updateTime);
}
