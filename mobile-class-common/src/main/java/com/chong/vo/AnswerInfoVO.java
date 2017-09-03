package com.chong.vo;

import com.chong.entity.AnswerInfo;
import com.chong.entity.User;
import lombok.*;

/**
 * 答案视图对象
 * Created by LXChild on 27/04/2017.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnswerInfoVO {
    private AnswerInfo answerInfo;

    private User user;
}
