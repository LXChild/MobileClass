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
 * 分数实体类
 * Created by LXChild on 27/04/2017.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Score {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long paperId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer totalScore;

    @Column(nullable = false)
    private Date createTime;

    public Score(Long paperId, Long userId, Integer totalScore, Date createTime) {
        this.paperId = paperId;
        this.userId = userId;
        this.totalScore = totalScore;
        this.createTime = createTime;
    }
}
