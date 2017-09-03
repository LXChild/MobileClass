package com.chong.dataobject;

import com.chong.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 试卷展示对象
 * Created by LXChild on 06/04/2017.
 */
@Getter
@Setter
public class PaperDO {

    private Paper paper;

    private List<SingleSel> singleSels;

    private List<MultiSel> multiSels;

    private List<RightWrong> rightWrongs;

    private List<FillingBlank> fillingBlanks;

    private List<Essay> essays;

    public PaperDO(Paper paper, List<SingleSel> singleSels, List<MultiSel> multiSels,
                   List<RightWrong> rightWrongs, List<FillingBlank> fillingBlanks, List<Essay> essays) {
        this.paper = paper;
        this.singleSels = singleSels;
        this.multiSels = multiSels;
        this.rightWrongs = rightWrongs;
        this.fillingBlanks = fillingBlanks;
        this.essays = essays;
    }

    @Override
    public String toString() {
        return "PaperDO{" +
                "paper=" + paper +
                ", singleSels=" + singleSels +
                ", multiSels=" + multiSels +
                ", rightWrongs=" + rightWrongs +
                ", fillingBlanks=" + fillingBlanks +
                ", essays=" + essays +
                '}';
    }
}
