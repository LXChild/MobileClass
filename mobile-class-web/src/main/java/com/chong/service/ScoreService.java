package com.chong.service;

import com.chong.domain.ScoreRepository;
import com.chong.entity.Score;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 总分服务
 * Created by LXChild on 27/04/2017.
 */
@Service
public class ScoreService {

    private final ScoreRepository repository;

    @Autowired
    public ScoreService(ScoreRepository repository) {
        this.repository = repository;
    }

    public List<Score> findByPaperId(Long paperId) {

        BaseValidator.notEmpty(paperId);
        return repository.findByPaperId(paperId);
    }

    public Score findByPaperIdAndUserId(Long paperId, Long userId) {
        BaseValidator.notEmpty(paperId);
        BaseValidator.notEmpty(userId);
        return repository.findByPaperIdAndUserId(paperId, userId);
    }

    public Score save(Score score) {
        BaseValidator.notEmpty(score);
        BaseValidator.notEmpty(score.getPaperId());
        BaseValidator.notEmpty(score.getUserId());
        BaseValidator.notEmpty(score.getTotalScore());
        Score result = repository.findByPaperIdAndUserId(score.getPaperId(), score.getUserId());
        if (result == null) {
            return repository.save(score);
        } else {
            repository.update(result.getId(), score.getTotalScore());
            return null;
        }
    }
}
