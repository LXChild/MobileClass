package com.chong.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 对错题实体类
 * Created by LXChild on 03/04/2017.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RightWrong {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long paperId;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private Boolean answer;

    @Column(nullable = false)
    private Integer score;
}
