package com.chong.domain;

import com.chong.entity.AnswerType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 答案类型 JPA 操作
 * Created by LXChild on 06/04/2017.
 */
public interface AnswerTypeRepository extends JpaRepository<AnswerType, Integer> {}
