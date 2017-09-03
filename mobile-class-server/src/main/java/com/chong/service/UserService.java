package com.chong.service;

import com.chong.domain.UserRepository;
import com.chong.entity.User;
import com.chong.exception.PersistenceException;
import com.chong.validator.UserValidator;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * 用户管理相关服务
 * Created by LXChild on 19/03/2017.
 */
@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User add(User user) throws PersistenceException.RepetitiveParamException {
        UserValidator.notEmptyNameAndPassword(user);
        User result = null;
        try {
            result = repository.save(user);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                throw new PersistenceException.RepetitiveParamException("用户名不能重复");
            }
        }
        return result;
    }

    public User findOne(Long id) {
        BaseValidator.notEmpty(id, "用户 ID 不能为空");
        return repository.findOne(id);
    }
}
