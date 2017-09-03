package com.chong.vo;

import com.chong.dataobject.*;
import com.chong.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 试卷展示对象
 * Created by LXChild on 06/04/2017.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class PaperVO {

    private Paper paper;

    private List<SingleSelDO> singleSelDOs;

    private List<MultiSelDO> multiSelDOs;

    private List<RightWrongDO> rightWrongDOs;

    private List<FillingBlankDO> fillingBlankDOs;

    private List<EssayDO> essayDOs;
}
