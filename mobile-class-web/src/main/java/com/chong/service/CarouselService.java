package com.chong.service;

import com.chong.domain.CarouselRepository;
import com.chong.dto.SortDTO;
import com.chong.entity.Carousel;
import com.chong.entity.FileInfo;
import com.chong.exception.PersistenceException;
import com.chong.utils.FileUtil;
import com.chong.utils.PageableUtils;
import com.chong.utils.SecurityUtils;
import com.chong.validator.BaseValidator;
import com.chong.validator.CarouselValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 轮播服务
 * Created by LXChild on 22/04/2017.
 */
@Service
public class CarouselService {

    private final CarouselRepository repository;

    private final FileInfoService fileInfoService;

    @Value("${course.courseware.file-path}")
    private String filePath;

    @Autowired
    public CarouselService(CarouselRepository repository, FileInfoService fileInfoService) {
        this.repository = repository;
        this.fileInfoService = fileInfoService;
    }

    @Transactional
    public Carousel save(Carousel carousel, MultipartFile file) throws Exception {
        File targetFile = FileUtil.upload(filePath, file);
        String fileName = file.getOriginalFilename();

        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(fileName);
        fileInfo.setDigest(SecurityUtils.getFileDigestByMD5(targetFile));
        fileInfo.setCreatorId(carousel.getCreatorId());
        fileInfo.setCreateTime(new Date());

        fileInfoService.save(fileInfo);

        carousel.setImgName(fileName);
        CarouselValidator.notEmpty(carousel);
        return repository.save(carousel);
    }

    @Transactional
    public void update(Integer page, Carousel carousel, MultipartFile file) throws Exception {
        File targetFile = FileUtil.upload(filePath, file);
        String fileName = file.getOriginalFilename();
        String digest = SecurityUtils.getFileDigestByMD5(targetFile);

        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(fileName);
        fileInfo.setDigest(digest);
        fileInfo.setCreatorId(carousel.getCreatorId());
        fileInfo.setCreateTime(new Date());

        if (fileInfoService.findByDigest(digest) != null) {
            throw new PersistenceException.RepetitiveParamException("文件重复");
        }

        fileInfoService.save(fileInfo);

        carousel.setImgName(fileName);
        CarouselValidator.notEmpty(carousel);
        repository.update(page, carousel.getImgName(), carousel.getTitle(), carousel.getContent(),
                carousel.getPageUrl(), carousel.getCreatorId(), carousel.getCreateTime());
    }

    public List<Carousel> findAll() {
        return repository.findAll(PageableUtils.basicPage(0, 3,
                new SortDTO("desc", "id"))).getContent();
    }

    public Carousel findByPage(Integer page) {
        BaseValidator.notEmpty(page);
        return repository.findByPage(page);
    }
}
