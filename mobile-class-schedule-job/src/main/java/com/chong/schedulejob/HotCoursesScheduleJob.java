package com.chong.schedulejob;

import com.chong.entity.HotCourse;
import com.chong.service.CourseSubscriptionService;
import com.chong.service.HotCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 热门课程定时任务
 * Created by LXChild on 21/04/2017.
 */
@Component
public class HotCoursesScheduleJob {

    private final CourseSubscriptionService subscriptionService;

    private final HotCourseService hotCourseService;

    private final static long HALF_AN_HOUR = 1800000;

    @Autowired
    public HotCoursesScheduleJob(HotCourseService hotCourseService, CourseSubscriptionService subscriptionService) {
        this.hotCourseService = hotCourseService;
        this.subscriptionService = subscriptionService;
    }

    @Scheduled(fixedRate = HALF_AN_HOUR)
    public void statisticsHotCourses() {
        List<HotCourse> hotCourses = subscriptionService.findHotCourses();
        List<HotCourse> hotCoursesInRepository = hotCourseService.findAll();

        if (hotCoursesInRepository.size() < 5) {
            int count = 0;
            for (int i = 0; i < hotCoursesInRepository.size(); i++) {
                count++;
                if (hotCoursesInRepository.get(i).equals(hotCourses.get(i))) {
                    continue;
                }
                hotCourseService.update(i + 1, hotCourses.get(i));
            }
            for (int i = count; i < hotCourses.size(); i++) {
                hotCourseService.save(hotCourses.get(i));
            }
        } else {
            for (int i = 0; i < hotCoursesInRepository.size(); i++) {
                if (hotCoursesInRepository.get(i).equals(hotCourses.get(i))) {
                    continue;
                }
                hotCourseService.update(i + 1, hotCourses.get(i));
            }
        }
    }
}
