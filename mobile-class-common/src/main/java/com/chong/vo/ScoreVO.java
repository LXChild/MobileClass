package com.chong.vo;

import com.chong.entity.Score;
import com.chong.entity.User;
import lombok.*;

/**
 * 总分视图对象
 * Created by LXChild on 27/04/2017.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScoreVO {

    private Score score;

    private User user;
}
