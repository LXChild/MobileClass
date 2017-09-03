package com.chong.service;

import com.chong.domain.CoursewareRepository;
import com.chong.entity.Courseware;
import com.chong.exception.ParamException;
import com.chong.exception.StorageException;
import com.chong.utils.FileUtil;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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

    @Value("${course.courseware.file-path}")
    private String filePath;

    @Autowired
    public CoursewareService(CoursewareRepository repository, CourseService courseService, UserService userService) {
        this.repository = repository;
        this.courseService = courseService;
        this.userService = userService;
    }

    @Transactional(rollbackFor = Exception.class)
    public Courseware save(Long creatorId, Long courseId, MultipartFile file) throws Exception {
        BaseValidator.notEmpty(creatorId);
        BaseValidator.notEmpty(courseId);
        if (courseService.findOne(courseId) == null
                || userService.findOne(creatorId) == null) {
            throw new ParamException.NotInDBException("课程 ID／用户 ID异常");
        }
        String url = FileUtil.upload(filePath, file);
        return repository.save(new Courseware(creatorId, courseId, file.getOriginalFilename(), new Date()));
    }

    public Courseware findOne(Long id, HttpServletResponse response) throws StorageException.StorageFileException {
        BaseValidator.notEmpty(id, "课件 ID 不能为空");
        Courseware courseware = repository.findOne(id);
        if (courseware == null) {
            throw new ParamException.NotInDBException(id);
        }
        FileUtil.download(filePath, courseware.getName(), response);
        return courseware;
    }

    public Courseware findByName(String name, HttpServletResponse response) throws StorageException.StorageFileException {
        BaseValidator.notEmptyString(name, "课件名不能为空");
        Courseware courseware = repository.findByName(name);
        if (courseware == null) {
            throw new ParamException.NotInDBException(String.format("课件名{%s}不存在", name));
        }
        FileUtil.download(filePath, courseware.getName(), response);
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
