package com.chong.validator;

import com.chong.entity.Carousel;

/**
 * 轮播图验证器
 * Created by LXChild on 22/04/2017.
 */
public final class CarouselValidator {

    private CarouselValidator() {}

    public static void notEmpty(Carousel carousel) {
        BaseValidator.notEmpty(carousel);
        BaseValidator.notEmptyString(carousel.getImgName());
        BaseValidator.notEmpty(carousel.getPage());
        BaseValidator.notEmptyString(carousel.getTitle());
        BaseValidator.notEmptyString(carousel.getContent());
        BaseValidator.notEmptyString(carousel.getPageUrl());
        BaseValidator.notEmpty(carousel.getCreatorId());
        BaseValidator.notEmpty(carousel.getCreateTime());
    }
}
