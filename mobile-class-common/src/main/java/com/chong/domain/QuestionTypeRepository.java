package com.chong.domain;

import com.chong.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 试卷类型 JPA 操作
 * Created by LXChild on 06/04/2017.
 */
public interface QuestionTypeRepository extends JpaRepository<QuestionType, Integer> {}
