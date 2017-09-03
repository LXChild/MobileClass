package com.chong.service;

import com.chong.domain.BulletinRepository;
import com.chong.entity.Bulletin;
import com.chong.enums.BulletinPositionEnum;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 公告服务
 * Created by LXChild on 28/04/2017.
 */
@Service
public class BulletinService {

    private final BulletinRepository repository;

    @Autowired
    public BulletinService(BulletinRepository repository) {
        this.repository = repository;
    }

    public Bulletin findByPosition(BulletinPositionEnum positionEnum) {
        return repository.findByPosition(positionEnum.getTag());
    }

    public Bulletin save(Long userId, String position, String content) {
        BaseValidator.notEmpty(userId);
        BaseValidator.notEmptyString(content);

        return repository.save(new Bulletin(content, position, userId, new Date()));
    }

    public void update(String content, String position, Long userId) {
        BaseValidator.notEmpty(userId);
        BaseValidator.notEmptyString(position);
        BaseValidator.notEmptyString(content);
        repository.update(content, position, userId, new Date());
    }
}
