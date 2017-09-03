package com.chong.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 子贴图片实体类
 * Created by LXChild on 02/05/2017.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class SubPostImg {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long creatorId;

    @Column(nullable = false)
    private Long subPostId;

    public SubPostImg(String name, Long creatorId, Long subPostId) {
        this.name = name;
        this.creatorId = creatorId;
        this.subPostId = subPostId;
    }
}
