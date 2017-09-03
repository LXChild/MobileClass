package com.chong.service;

import com.chong.domain.SubPostRepository;
import com.chong.dto.SortDTO;
import com.chong.entity.FileInfo;
import com.chong.entity.SubPost;
import com.chong.entity.SubPostImg;
import com.chong.exception.ParamException;
import com.chong.utils.FileUtil;
import com.chong.utils.PageableUtils;
import com.chong.utils.SecurityUtils;
import com.chong.validator.BaseValidator;
import com.chong.validator.PostValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

/**
 * 子贴相关服务
 * Created by LXChild on 07/04/2017.
 */
@Service
public class SubPostService {

    private final PostService postService;
    private final UserService userService;
    private final SubPostRepository repository;

    @Value("${course.courseware.file-path}")
    private String filePath;

    private final FileInfoService fileInfoService;

    private final SubPostImgService subPostImgService;

    @Autowired
    public SubPostService(SubPostRepository repository, PostService postService, UserService userService,
                          FileInfoService fileInfoService, SubPostImgService subPostImgService) {
        this.repository = repository;
        this.postService = postService;
        this.userService = userService;
        this.fileInfoService = fileInfoService;
        this.subPostImgService = subPostImgService;
    }

    public SubPost save(SubPost subPost, MultipartFile file) throws Exception {
        PostValidator.notEmptySubPost(subPost);
        if (postService.findOne(subPost.getParentId()) == null
                || userService.findOne(subPost.getCreatorId()) == null) {
            throw new ParamException.NotInDBException("帖子 ID／用户 ID 异常");
        }

        File targetFile = FileUtil.upload(filePath, file);
        String fileName = file.getOriginalFilename();

        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(fileName);
        fileInfo.setDigest(SecurityUtils.getFileDigestByMD5(targetFile));
        fileInfo.setCreatorId(subPost.getCreatorId());
        fileInfo.setCreateTime(new Date());

        fileInfoService.save(fileInfo);

        SubPost result = repository.save(subPost);
        subPostImgService.save(new SubPostImg(fileName, subPost.getCreatorId(), result.getId()));
        return result;
    }

    public Page<SubPost> findByParentId(Long parentId, Integer page) {
        BaseValidator.notEmpty(parentId, "主贴 ID 不能为空");
        return repository.findByParentId(parentId, PageableUtils.basicPage(page, 0,
                new SortDTO("asc", "id")));
    }
}
