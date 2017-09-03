package com.chong.dataobject;

import com.chong.entity.AnswerInfo;
import com.chong.entity.FillingBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 填空题数据对象
 * Created by LXChild on 23/04/2017.
 */
@Getter
@Setter
@AllArgsConstructor
public class FillingBlankDO {

    private FillingBlank fillingBlank;

    private AnswerInfo answerInfo;
}
