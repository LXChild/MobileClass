package com.chong.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户对象
 * Created by LXChild on 19/03/2017.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String mobile;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Boolean enable = true;
    @Column(nullable = false)
    private String realName;
    @Column(nullable = false)
    private Date createTime;
    @Column(nullable = false)
    private Date updateTime = new Date();
    private String avatar;

    public User(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.mobile = user.getMobile();
        this.email = user.getEmail();
        this.enable = user.getEnable();
        this.realName = user.getRealName();
        this.createTime = user.getCreateTime();
        this.updateTime = user.getUpdateTime();
        this.avatar = user.getAvatar();
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String mobile, String email, Boolean enable, String realName, Date updateTime) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.enable = enable;
        this.realName = realName;
        this.updateTime = updateTime;
    }

    public User(String name, String password, String mobile, String email, String realName, Date createTime) {
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
        this.realName = realName;
        this.createTime = createTime;
    }
}
