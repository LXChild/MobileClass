package com.chong.vo;

import com.chong.entity.Role;
import com.chong.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用户显示对象
 * Created by LXChild on 24/04/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    private User user;

    private Role role;
}
