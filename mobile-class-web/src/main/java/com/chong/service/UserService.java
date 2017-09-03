package com.chong.service;

import com.chong.domain.UserRepository;
import com.chong.entity.User;
import com.chong.utils.PageableUtils;
import com.chong.utils.SecurityUtils;
import com.chong.validator.BaseValidator;
import com.chong.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * 用户管理相关服务
 * Created by LXChild on 19/03/2017.
 */
@Service
public class UserService {

    private final UserRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User add(User user) {
        UserValidator.notEmpty(user);
        String password = user.getPassword();
        try {
            String digest = SecurityUtils.getDigestByMD5(password);
            password = SecurityUtils.encodeByBase64(digest);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("没有该加密算法", e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("加密失败", e);
        }
        user.setPassword(password);
        return repository.save(user);
    }

    public Page<User> findAllByRealName(Integer limit, Integer page, String realName) {
        return repository.findAllByRealName(realName, PageableUtils.basicPage(page, limit));
    }

    public Page<User> findAll(Integer limit, Integer page) {
        return repository.findAll(PageableUtils.basicPage(page, limit));
    }

    public User findByName(String name) {
        BaseValidator.notEmptyString(name, "用户名不能为空");
        return repository.findByName(name);
    }

    public User findOne(Long id) {
        BaseValidator.notEmpty(id, "用户 ID 不能为空");
        return repository.findOne(id);
    }

    public void update(Long id, String name, String realName, String mobile, String email, Boolean enable) {
        BaseValidator.notEmpty(id);
        UserValidator.notEmpty(new User(name, mobile, email, enable, realName, new Date()));
        repository.update(id, name, mobile, email, enable, realName, new Date());
    }
}
