package com.chong.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 试题类型实体类
 * Created by LXChild on 06/04/2017.
 */
@Getter
@Setter
@Entity
public class QuestionType {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String type;

    @Override
    public String toString() {
        return "QuestionType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
