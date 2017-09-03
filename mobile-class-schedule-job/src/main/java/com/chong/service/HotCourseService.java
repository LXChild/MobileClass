package com.chong.service;

import com.chong.domain.HotCourseRepository;
import com.chong.entity.HotCourse;
import com.chong.validator.HotItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 热门课程服务
 * Created by LXChild on 21/04/2017.
 */
@Service
public class HotCourseService {

    private final HotCourseRepository repository;

    @Autowired
    public HotCourseService(HotCourseRepository repository) {
        this.repository = repository;
    }

    public List<HotCourse> findAll() {
        return repository.findAll();
    }

    public HotCourse save(HotCourse hotCourse) {
        HotItemValidator.notEmptyHotCourse(hotCourse);
        return repository.save(hotCourse);
    }

    public void update(Integer rank, HotCourse hotCourse) {
        HotItemValidator.notEmptyHotCourse(hotCourse);
        repository.updateHotCourse(rank, hotCourse.getCourseId(), hotCourse.getHeat(), hotCourse.getCreateTime());
    }
}
