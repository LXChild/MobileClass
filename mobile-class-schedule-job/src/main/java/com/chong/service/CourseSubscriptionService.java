package com.chong.service;

import com.chong.domain.CourseSubscriptionRepository;
import com.chong.entity.CourseSubscription;
import com.chong.entity.HotCourse;
import com.chong.validator.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 课程关注服务处理
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

    public List<HotCourse> findHotCourses() {
        List<Object[]> hotCourses = repository.findHotCourses();
        List<HotCourse> result = new ArrayList<>();
        for (Object[] hotCourse : hotCourses) {
            Long courseId = ((Number) hotCourse[0]).longValue();
            Long heat = ((Number) hotCourse[1]).longValue();
            result.add(new HotCourse(courseId, heat, new Date()));
        }
        return result;
    }
}
