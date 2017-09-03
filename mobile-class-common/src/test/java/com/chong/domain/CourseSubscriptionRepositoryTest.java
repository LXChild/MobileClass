package com.chong.domain;

import com.chong.entity.CourseSubscription;
import com.chong.utils.PageableUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 课程关注测试
 * Created by LXChild on 26/04/2017.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseSubscriptionRepositoryTest {

    @Autowired
    private CourseSubscriptionRepository repository;

    @Test
    public void findAllByCourseId() throws Exception {
//        Page<CourseSubscription> subscriptions = repository.findAllById(3L,
//                PageableUtils.basicPage(0, 10, "id"));
    }
}