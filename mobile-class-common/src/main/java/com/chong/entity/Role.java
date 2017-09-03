package com.chong.entity;

import com.chong.enums.RoleEnum;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 角色信息表
 * Created by LXChild on 03/04/2017.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String tag;

    @Column(nullable = false, unique = true)
    private String comment;

    @Column(nullable = false)
    private Date createTime;

    @Column(nullable = false)
    private Date updateTime = new Date();

    public Role(String tag, String comment) {
        this.tag = tag;
        this.comment = comment;
    }

    public Role(String tag, String comment, Date createTime) {
        this.tag = tag;
        this.comment = comment;
        this.createTime = createTime;
    }

    public Role(RoleEnum roleEnum) {
        this.tag = roleEnum.getTag();
        this.comment = roleEnum.getComment();
    }
}
