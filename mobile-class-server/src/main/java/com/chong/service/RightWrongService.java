package com.chong.service;

import com.chong.domain.RightWrongRepository;
import com.chong.entity.RightWrong;
import com.chong.validator.PaperValidator;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 对错题服务
 * Created by LXChild on 06/04/2017.
 */
@Service
public class RightWrongService {

    private final RightWrongRepository repository;

    @Autowired
    public RightWrongService(RightWrongRepository repository) {
        this.repository = repository;
    }

    public List<RightWrong> findByPaperId(Long paperId) {
        BaseValidator.notEmpty(paperId, "试卷 ID 不能为空");
        return repository.findByPaperId(paperId);
    }

    public RightWrong save(RightWrong rightWrong) {
        PaperValidator.notEmptyRightWrong(rightWrong);
        return repository.save(rightWrong);
    }
}
