package com.chong.dataobject;

import com.chong.entity.Post;
import lombok.*;

import java.util.List;

/**
 * 帖子数据对象
 * Created by LXChild on 07/04/2017.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDO {

    private Post post;

    private List<SubPostDO> subPostDOs;
}
