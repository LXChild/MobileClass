package com.chong.dataobject;

import com.chong.entity.HotPost;
import com.chong.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 热门帖子对象
 * Created by LXChild on 21/04/2017.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class HotPostDO {

    private Post post;

    private HotPost hotPost;
}
