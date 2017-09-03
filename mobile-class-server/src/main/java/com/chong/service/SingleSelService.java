package com.chong.service;

import com.chong.domain.SingleSelRepository;
import com.chong.entity.SingleSel;
import com.chong.validator.PaperValidator;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 单选题相关服务
 * Created by LXChild on 06/04/2017.
 */
@Service
public class SingleSelService {

    private final SingleSelRepository repository;

    @Autowired
    public SingleSelService(SingleSelRepository repository) {
        this.repository = repository;
    }

    public List<SingleSel> findByPaperId(Long paperId) {
        BaseValidator.notEmpty(paperId, "试卷 ID 不能为空");
        return repository.findByPaperId(paperId);
    }

    public SingleSel save(SingleSel singleSel) {
        PaperValidator.notEmptySingleSel(singleSel);
        return repository.save(singleSel);
    }
}
