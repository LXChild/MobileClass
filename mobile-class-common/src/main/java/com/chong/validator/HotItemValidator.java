package com.chong.validator;

import com.chong.entity.HotCourse;
import com.chong.entity.HotPost;

/**
 * 热门帖子校验器
 * Created by LXChild on 20/04/2017.
 */
public final class HotItemValidator {

    private HotItemValidator() {}

    public static void notEmptyHotPost(HotPost hotPost) {
        BaseValidator.notEmpty(hotPost);
        BaseValidator.notEmpty(hotPost.getPostId());
        BaseValidator.notEmpty(hotPost.getHeat());
        BaseValidator.notEmpty(hotPost.getCreateTime());
    }

    public static void notEmptyHotCourse(HotCourse hotCourse) {
        BaseValidator.notEmpty(hotCourse);
        BaseValidator.notEmpty(hotCourse.getCourseId());
        BaseValidator.notEmpty(hotCourse.getHeat());
        BaseValidator.notEmpty(hotCourse.getCreateTime());
    }
}
