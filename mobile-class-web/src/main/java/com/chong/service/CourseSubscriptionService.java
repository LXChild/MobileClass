package com.chong.service;

import com.chong.domain.CourseSubscriptionRepository;
import com.chong.entity.CourseSubscription;
import com.chong.utils.PageableUtils;
import com.chong.validator.BaseValidator;
import com.chong.validator.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 课程订阅服务
 * Created by LXChild on 21/04/2017.
 */
@Service
public class CourseSubscriptionService {

    private final CourseSubscriptionRepository repository;

    @Autowired
    public CourseSubscriptionService(CourseSubscriptionRepository repository) {
        this.repository = repository;
    }

    public CourseSubscription save(CourseSubscription subscription) {
        CourseValidator.notEmptySubscription(subscription);
        return repository.save(subscription);
    }

    public CourseSubscription findSubscription(Long courseId, Long userId) {
        BaseValidator.notEmpty(courseId);
        BaseValidator.notEmpty(userId);
        List<Object[]> result = repository.findSubscription(courseId, userId);
        if (result == null || result.isEmpty()) {
            return null;
        }
        CourseSubscription courseSubscription = new CourseSubscription();
        for (Object[] objects : result) {
            courseSubscription.setId(((Number) objects[0]).longValue());
            courseSubscription.setCourseId(((Number) objects[1]).longValue());
            courseSubscription.setUserId(((Number) objects[3]).longValue());
            courseSubscription.setCreateTime((Date) objects[2]);
        }
        return courseSubscription;
    }

    public Page<CourseSubscription> findAllByCourseId(Long courseId, Integer limit, Integer page) {
        BaseValidator.notEmpty(courseId);
        return repository.findAllByCourseId(courseId, PageableUtils.basicPage(page, limit));
    }

    public void deleteByCourseIdAndUserId(Long courseId, Long userId) {
        BaseValidator.notEmpty(courseId);
        BaseValidator.notEmpty(userId);
        repository.deleteByCourseIdAndUserId(courseId, userId);
    }

    public Page<CourseSubscription> findAllByUserId(Long userId, Integer page) {
        BaseValidator.notEmpty(userId);
        return repository.findAllByUserId(userId, PageableUtils.basicPage(page));
    }
}
