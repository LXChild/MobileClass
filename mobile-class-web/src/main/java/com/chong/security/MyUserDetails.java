package com.chong.security;

import com.chong.entity.Role;
import com.chong.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 自定义 UserDetails，它代表了Spring Security的用户认证实体，带有用户名、密码、权限列表、过期特性等性质
 * Created by LXChild on 03/04/2017.
 */
public class MyUserDetails extends User implements UserDetails {

    private Role role;

    public MyUserDetails(User user, Role role) {
        super(user);
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
        }
        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority(role.getTag()));
        return auths;
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return super.getEnable();
    }
}
