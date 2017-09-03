package com.chong.dataobject;

import com.chong.entity.AnswerInfo;
import com.chong.entity.Essay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 简答题数据对象
 * Created by LXChild on 23/04/2017.
 */
@Getter
@Setter
@AllArgsConstructor
public class EssayDO {

    private Essay essay;

    private AnswerInfo answerInfo;
}
