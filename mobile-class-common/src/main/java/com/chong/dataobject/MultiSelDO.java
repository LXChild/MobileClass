package com.chong.dataobject;

import com.chong.entity.AnswerInfo;
import com.chong.entity.MultiSel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 多选题数据对象
 * Created by LXChild on 23/04/2017.
 */
@Getter
@Setter
@AllArgsConstructor
public class MultiSelDO {

    private MultiSel multiSel;

    private AnswerInfo answerInfo;
}
