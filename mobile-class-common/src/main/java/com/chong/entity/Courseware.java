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
 * 课件实体类
 * Created by LXChild on 07/04/2017.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Courseware {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long creatorId;

    @Column(nullable = false)
    private Long courseId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Date createTime;

    public Courseware(Long creatorId, Long courseId, String name, Date createTime) {
        this.creatorId = creatorId;
        this.courseId = courseId;
        this.name = name;
//        this.name = name.substring(0, name.lastIndexOf('.'));
//        this.type = name.substring(name.lastIndexOf('.') + 1);
        this.createTime = createTime;
    }
}
