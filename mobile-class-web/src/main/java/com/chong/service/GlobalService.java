package com.chong.service;

import com.chong.dataobject.HotCourseDO;
import com.chong.dataobject.HotPostDO;
import com.chong.domain.CourseRepository;
import com.chong.domain.HotCourseRepository;
import com.chong.domain.HotPostRepository;
import com.chong.domain.PostRepository;
import com.chong.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局服务
 * Created by LXChild on 21/04/2017.
 */
@Service
public class GlobalService {

    private final HotPostRepository hotPostRepository;

    private final PostRepository postRepository;

    private final HotCourseRepository hotCourseRepository;

    private final CourseRepository courseRepository;

    private final PaperService paperService;

    @Autowired
    public GlobalService(HotPostRepository hotPostRepository, PostRepository postRepository,
                         CourseRepository courseRepository, HotCourseRepository hotCourseRepository,
                         PaperService paperService) {
        this.hotPostRepository = hotPostRepository;
        this.postRepository = postRepository;
        this.courseRepository = courseRepository;
        this.hotCourseRepository = hotCourseRepository;
        this.paperService = paperService;
    }

    public List<HotPostDO> findAllHotPosts() {
        List<HotPost> hotPosts = hotPostRepository.findAll();
        if (hotPosts == null || hotPosts.isEmpty()) {
            return new ArrayList<>();
        }
        List<HotPostDO> hotPostDOs = new ArrayList<>();
        for (HotPost hotPost : hotPosts) {
            Long postId = hotPost.getPostId();
            Post post = postRepository.findOne(postId);
            hotPostDOs.add(new HotPostDO(post, hotPost));
        }
        return hotPostDOs;
    }

    public List<HotCourseDO> findAllHotCourses() {
        List<HotCourse> hotCourses = hotCourseRepository.findAll();
        if (hotCourses == null || hotCourses.isEmpty()) {
            return new ArrayList<>();
        }

        List<HotCourseDO> hotCourseDOs = new ArrayList<>();
        for (HotCourse hotCourse : hotCourses) {
            Long courseId = hotCourse.getCourseId();
            Course course = courseRepository.findOne(courseId);
            hotCourseDOs.add(new HotCourseDO(course, hotCourse));
        }
        return hotCourseDOs;
    }

    public List<Paper> findRecentPaper() {
        return paperService.findRecentPaper().getContent();
    }
}
