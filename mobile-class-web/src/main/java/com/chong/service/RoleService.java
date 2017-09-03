package com.chong.service;

import com.chong.domain.RoleRepository;
import com.chong.entity.Role;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 角色管理
 * Created by LXChild on 03/04/2017.
 */
@Service
public class RoleService {

    private final RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> findAllByComment(String comment) {
        return repository.findAllByComment(comment);
    }

    public List<Role> findAll() {
        return repository.findAll();
    }

    public Role findOne(Integer roleId) {
        BaseValidator.notEmpty(roleId, "角色不能为空");
        return repository.findOne(roleId);
    }

    public Role save(Role role) {
        BaseValidator.notEmpty(role.getTag(), "角色标识不能为空");
        BaseValidator.notEmpty(role.getComment(), "角色名称不能为空");
        return repository.save(role);
    }

    public void update(Integer id, String tag, String comment) {
        BaseValidator.notEmpty(tag, "角色标识不能为空");
        BaseValidator.notEmpty(comment, "角色名称不能为空");
        repository.update(id, tag, comment, new Date());
    }
}
