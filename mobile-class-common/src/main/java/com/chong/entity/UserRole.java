package com.chong.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户角色对应表信息
 * Created by LXChild on 03/04/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserRole {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false)
    private Integer roleId;

    @Column(nullable = false)
    private Date createTime;

    @Column(nullable = false)
    private Date updateTime = new Date();

    public UserRole(Long userId, Integer roleId, Date createTime) {
        this.userId = userId;
        this.roleId = roleId;
        this.createTime = createTime;
    }
}
