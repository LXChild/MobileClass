package com.chong.service;

import com.chong.domain.MultiSelRepository;
import com.chong.entity.MultiSel;
import com.chong.validator.BaseValidator;
import com.chong.validator.PaperValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 多选题服务
 * Created by LXChild on 06/04/2017.
 */
@Service
public class MultiSelService {

    private final MultiSelRepository repository;

    @Autowired
    public MultiSelService(MultiSelRepository repository) {
        this.repository = repository;
    }

    public List<MultiSel> findByPaperId(Long paperId) {
        BaseValidator.notEmpty(paperId, "试卷 ID 不能为空");
        return repository.findByPaperId(paperId);
    }

    public MultiSel findOne(Long id) {
        BaseValidator.notEmpty(id);
        return repository.findOne(id);
    }

    public MultiSel save(MultiSel multiSel) {
        PaperValidator.notEmptyMultiSel(multiSel);
        return repository.save(multiSel);
    }

    public void delete(Long id) {
        BaseValidator.notEmpty(id);
        repository.delete(id);
    }

    public void update(Long id, Long paperId, String question, String answerA, String answerB, String answerC,
                       String answerD, String answerE, String answerF, String answerR, Integer score) {
        PaperValidator.notEmptyMultiSel(new MultiSel(id, paperId, question, answerA, answerB, answerC,
                answerD, answerE, answerF, answerR, score));
        repository.update(id, question, answerA, answerB, answerC, answerD, answerE, answerF, answerR, score);
    }
}
