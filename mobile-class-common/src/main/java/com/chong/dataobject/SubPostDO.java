package com.chong.dataobject;

import com.chong.entity.SubPost;
import com.chong.entity.SubPostImg;
import lombok.*;

import java.util.List;

/**
 * 子贴数据对象
 * Created by LXChild on 02/05/2017.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubPostDO {

    private SubPost subPost;

    private List<SubPostImg> subPostImgs;
}
