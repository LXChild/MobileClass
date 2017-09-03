package com.chong.dataobject;

import com.chong.entity.AnswerInfo;
import com.chong.entity.RightWrong;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 对错题数据对象
 * Created by LXChild on 23/04/2017.
 */
@Getter
@Setter
@AllArgsConstructor
public class RightWrongDO {

    private RightWrong rightWrong;

    private AnswerInfo answerInfo;
}
