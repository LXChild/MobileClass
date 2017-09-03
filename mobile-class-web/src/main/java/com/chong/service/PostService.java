package com.chong.service;

import com.chong.domain.PostRepository;
import com.chong.entity.FileInfo;
import com.chong.entity.Post;
import com.chong.entity.PostImg;
import com.chong.utils.FileUtil;
import com.chong.utils.PageableUtils;
import com.chong.utils.SecurityUtils;
import com.chong.validator.BaseValidator;
import com.chong.validator.PostValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;


/**
 * 帖子服务
 * Created by LXChild on 15/04/2017.
 */
@Service
public class PostService {

    private final PostRepository repository;

    @Value("${course.courseware.file-path}")
    private String filePath;

    private final FileInfoService fileInfoService;

    private final PostImgService postImgService;

    @Autowired
    public PostService(PostRepository repository, FileInfoService fileInfoService, PostImgService postImgService) {
        this.repository = repository;
        this.fileInfoService = fileInfoService;
        this.postImgService = postImgService;
    }

    public Page<Post> findAll(Integer page) {
        return repository.findAll(PageableUtils.basicPage(page));
    }

    public Page<Post> findAllByTitle(Integer page, String title) {
        return repository.findAllByTitle(title, PageableUtils.basicPage(page));
    }

    public Post findOne(Long id) {
        BaseValidator.notEmpty(id);
        return repository.findOne(id);
    }

    @Transactional
    public Post save(Post post, MultipartFile file) throws Exception {

        PostValidator.notEmptyPost(post);
        Post result = repository.save(post);

        if (file != null && !"".equals(file.getOriginalFilename())) {
            File targetFile = FileUtil.upload(filePath, file);
            String fileName = file.getOriginalFilename();

            FileInfo fileInfo = new FileInfo();
            fileInfo.setName(fileName);
            fileInfo.setDigest(SecurityUtils.getFileDigestByMD5(targetFile));
            fileInfo.setCreatorId(post.getCreatorId());
            fileInfo.setCreateTime(new Date());

            fileInfoService.save(fileInfo);
            postImgService.save(new PostImg(fileName, post.getCreatorId(), result.getId()));

        }

        return result;
    }
}
