package com.chong.service;

import com.chong.domain.FileInfoRepository;
import com.chong.entity.FileInfo;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文件详情服务
 * Created by LXChild on 29/04/2017.
 */
@Service
public class FileInfoService {

    private final FileInfoRepository repository;

    @Autowired
    public FileInfoService(FileInfoRepository repository) {
        this.repository = repository;
    }

    public FileInfo save(FileInfo fileInfo) {
        BaseValidator.notEmpty(fileInfo);
        BaseValidator.notEmptyString(fileInfo.getName());
        BaseValidator.notEmpty(fileInfo.getCreatorId());
        BaseValidator.notEmpty(fileInfo.getCreateTime());
        return repository.save(fileInfo);
    }

    public FileInfo findOne(Long id) {
        BaseValidator.notEmpty(id);
        return repository.findOne(id);
    }

    public FileInfo findByName(String name) {
        BaseValidator.notEmptyString(name);
        return repository.findByName(name);
    }

    public FileInfo findByDigest(String digest) {
        BaseValidator.notEmptyString(digest);
        return repository.findByDigest(digest);
    }
}
