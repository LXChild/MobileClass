package com.chong.service;

import com.chong.domain.PaperRepository;
import com.chong.entity.Paper;
import com.chong.validator.PaperValidator;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 试卷服务
 * Created by LXChild on 06/04/2017.
 */
@Service
public class PaperService {

    private final PaperRepository repository;

    @Autowired
    public PaperService(PaperRepository repository) {
        this.repository = repository;
    }

    public Paper findOne(Long id) {
        BaseValidator.notEmpty(id, "试卷 ID 不能为空");
        return repository.findOne(id);
    }

    public Paper save(Paper paper) {
        PaperValidator.notEmptyPaper(paper);
        return repository.save(paper);
    }
}
