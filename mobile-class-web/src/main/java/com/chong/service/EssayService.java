package com.chong.service;

import com.chong.domain.EssayRepository;
import com.chong.entity.Essay;
import com.chong.validator.BaseValidator;
import com.chong.validator.PaperValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 简答题服务
 * Created by LXChild on 06/04/2017.
 */
@Service
public class EssayService {

    private final EssayRepository repository;

    @Autowired
    public EssayService(EssayRepository repository) {
        this.repository = repository;
    }

    public List<Essay> findByPaperId(Long paperId) {
        BaseValidator.notEmpty(paperId, "试卷 ID 不能为空");
        return repository.findByPaperId(paperId);
    }

    public Essay findOne(Long id) {
        BaseValidator.notEmpty(id);
        return repository.findOne(id);
    }

    public Essay save(Essay essay) {
        PaperValidator.notEmptyEssay(essay);
        return repository.save(essay);
    }

    public void delete(Long id) {
        BaseValidator.notEmpty(id);
        repository.delete(id);
    }

    public void update(Long id, Long paperId, String question, String answer, Integer score) {
        PaperValidator.notEmptyEssay(new Essay(id, paperId, question, answer, score));
        repository.update(id, question, answer, score);
    }
}
