package com.chong.security;

import com.chong.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

/**
 * 提供用户UserDetails的具体验证方式，在这里可以自定义用户密码的加密、验证方式等等
 * Created by LXChild on 03/04/2017.
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private final MyUserDetailsService userDetailsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationProvider.class);

    @Autowired
    public MyAuthenticationProvider(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 自定义验证方式
     *
     * @param authentication 用户验证信息
     * @return 用户验证信息
     */
    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Username not found.");
        }
        if (!userDetails.getEnable()) {
            throw new DisabledException("该用户已被禁用");
        }

        String newPassword = password;
        try {
            String digest = SecurityUtils.getDigestByMD5(password);
            newPassword = SecurityUtils.encodeByBase64(digest);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("没有该加密算法失败", e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("加密密码失败", e);
        }
        if (!newPassword.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
