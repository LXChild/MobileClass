package com.chong.vo;

import com.chong.entity.CourseSubscription;
import com.chong.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 课程关注展示对象
 * Created by LXChild on 26/04/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseSubscriptionVO {

    private CourseSubscription subscription;

    private User user;
}
