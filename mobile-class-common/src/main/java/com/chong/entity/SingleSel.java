package com.chong.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 单选题实体
 * Created by LXChild on 03/04/2017.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SingleSel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long paperId;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answerA;

    @Column(nullable = false)
    private String answerB;

    @Column(nullable = false)
    private String answerC;

    @Column(nullable = false)
    private String answerD;

    @Column(nullable = false)
    private String answerR;

    @Column(nullable = false)
    private Integer score;
}
