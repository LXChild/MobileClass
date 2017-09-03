package com.chong.service;

import com.chong.domain.FillingBlankRepository;
import com.chong.entity.FillingBlank;
import com.chong.validator.PaperValidator;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 填空题相关服务
 * Created by LXChild on 06/04/2017.
 */
@Service
public class FillingBlankService {

    private final FillingBlankRepository repository;

    @Autowired
    public FillingBlankService(FillingBlankRepository repository) {
        this.repository = repository;
    }

    public List<FillingBlank> findByPaperId(Long paperId) {
        BaseValidator.notEmpty(paperId, "试卷 ID 不能为空");
        return repository.findByPaperId(paperId);
    }

    public FillingBlank save(FillingBlank fillingBlank) {
        PaperValidator.notEmptyFillingBlank(fillingBlank);
        return repository.save(fillingBlank);
    }
}
