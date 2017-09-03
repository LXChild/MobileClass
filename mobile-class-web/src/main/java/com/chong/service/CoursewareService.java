package com.chong.service;

import com.chong.domain.CoursewareRepository;
import com.chong.entity.Courseware;
import com.chong.entity.FileInfo;
import com.chong.exception.ParamException;
import com.chong.exception.PersistenceException;
import com.chong.utils.FileUtil;
import com.chong.utils.SecurityUtils;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 课件服务
 * Created by LXChild on 07/04/2017.
 */
@Service
public class CoursewareService {

    private final CoursewareRepository repository;
    private final CourseService courseService;
    private final UserService userService;

    private final FileInfoService fileInfoService;

    @Value("${course.courseware.file-path}")
    private String filePath;

    @Autowired
    public CoursewareService(CoursewareRepository repository, CourseService courseService, UserService userService,
                             FileInfoService fileInfoService) {
        this.repository = repository;
        this.courseService = courseService;
        this.userService = userService;
        this.fileInfoService = fileInfoService;
    }

    @Transactional(rollbackFor = Exception.class)
    public Courseware save(Long creatorId, Long courseId, MultipartFile file) throws Exception {
        BaseValidator.notEmpty(creatorId);
        BaseValidator.notEmpty(courseId);
        if (courseService.findOne(courseId) == null
                || userService.findOne(creatorId) == null) {
            throw new ParamException.NotInDBException("课程 ID／用户 ID异常");
        }
        File targetFile = FileUtil.upload(filePath, file);
        String digest = SecurityUtils.getFileDigestByMD5(targetFile);

        if (fileInfoService.findByDigest(digest) != null) {
            throw new PersistenceException.RepetitiveParamException("文件重复");
        }

        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(file.getOriginalFilename());
        fileInfo.setDigest(digest);
        fileInfo.setCreatorId(creatorId);
        fileInfo.setCreateTime(new Date());


        FileInfo result = fileInfoService.save(fileInfo);
        BaseValidator.notEmpty(result);
        BaseValidator.notEmptyString(result.getName());
        return repository.save(new Courseware(creatorId, courseId, result.getName(), new Date()));
    }

    public Courseware findOne(Long id) {
        BaseValidator.notEmpty(id, "课件 ID 不能为空");
        Courseware courseware = repository.findOne(id);
        if (courseware == null) {
            throw new ParamException.NotInDBException(id);
        }
        return courseware;
    }

    public Courseware findByName(String name) {
        BaseValidator.notEmptyString(name, "课件名不能为空");
        Courseware courseware = repository.findByName(name);
        if (courseware == null) {
            throw new ParamException.NotInDBException(String.format("课件名{%s}不存在", name));
        }
        return courseware;
    }

    public List<Courseware> findByCreatorId(Long creatorId) {
        BaseValidator.notEmpty(creatorId, "创建者 ID 不能为空");
        return repository.findByCreatorId(creatorId);
    }

    public List<Courseware> findByCourseId(Long courseId) {
        BaseValidator.notEmpty(courseId, "课程 ID 不能为空");
        return repository.findByCourseIdOrderByIdDesc(courseId);
    }
}
