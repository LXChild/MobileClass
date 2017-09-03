package com.chong.service;

import com.chong.domain.PaperRepository;
import com.chong.dto.SortDTO;
import com.chong.entity.Paper;
import com.chong.utils.PageableUtils;
import com.chong.validator.BaseValidator;
import com.chong.validator.PaperValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 试卷服务
 * Created by LXChild on 17/04/2017.
 */
@Service
public class PaperService {

    private final PaperRepository repository;

    @Autowired
    public PaperService(PaperRepository repository) {
        this.repository = repository;
    }

    public Page<Paper> findAll(Integer page) {
        return repository.findAllByShowPaper(true, PageableUtils.basicPage(page));
    }

    public Page<Paper> findAllByShowPaperAndNameLike(Integer page, String name) {
        return repository.findAllByShowPaperAndNameLike(true, name, PageableUtils.basicPage(page));
    }

    public Paper findOne(Long id) {
        BaseValidator.notEmpty(id, "试卷 ID 不能为空");
        return repository.findOne(id);
    }

    public Paper save(Paper paper) {
        PaperValidator.notEmptyPaper(paper);
        return repository.save(paper);
    }

    public Page<Paper> findRecentPaper() {
        return repository.findAll(PageableUtils.basicPage(0, 5, new SortDTO("desc", "id")));
    }

    public void updateShowAnswer(Long id, Boolean show) {
        BaseValidator.notEmpty(id);
        BaseValidator.notEmpty(show);
        repository.updateShowAnswer(id, show);
    }

    public void updateShowPaper(Long id, Boolean show) {
        BaseValidator.notEmpty(id);
        BaseValidator.notEmpty(show);
        repository.updateShowPaper(id, show);
    }

    public Page<Paper> findAllByCourseIdAndShowPaper(Long courseId, Boolean showPaper, Integer page) {
        BaseValidator.notEmpty(courseId);
        BaseValidator.notEmpty(showPaper);
        return repository.findAllByCourseIdAndShowPaper(courseId, showPaper, PageableUtils.basicPage(page));
    }

    public List<Paper> findAllByCourseIdAndShowPaper(Long courseId, Boolean showPaper) {
        BaseValidator.notEmpty(courseId);
        BaseValidator.notEmpty(showPaper);
        return repository.findAllByCourseIdAndShowPaper(courseId, showPaper);
    }
}
