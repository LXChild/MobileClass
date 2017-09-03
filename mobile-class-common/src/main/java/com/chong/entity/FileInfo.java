package com.chong.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 文件详情实体类
 * Created by LXChild on 29/04/2017.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class FileInfo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String digest;

//    @Column(nullable = false)
//    private Integer categoryId;

    @Column(nullable = false)
    private Long creatorId;

    @Column(nullable = false)
    private Date createTime;
}
