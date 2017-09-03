package com.chong.controller;

import com.chong.entity.FileInfo;
import com.chong.exception.PersistenceException;
import com.chong.exception.StorageException;
import com.chong.service.FileInfoService;
import com.chong.utils.FileUtil;
import com.chong.utils.SecurityUtils;
import com.chong.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 文件控制器
 * Created by LXChild on 29/04/2017.
 */
@RestController
@RequestMapping("/files")
public class FileController {

    @Value("${course.courseware.file-path}")
    private String filePath;

    private final FileInfoService fileInfoService;

    private final UserUtil userUtil;

    @Autowired
    public FileController(FileInfoService fileInfoService, UserUtil userUtil) {
        this.fileInfoService = fileInfoService;
        this.userUtil = userUtil;
    }

    @PostMapping("/upload")
    public FileInfo upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception {
        File targetFile = FileUtil.upload(filePath, file);

        String digest = SecurityUtils.getFileDigestByMD5(targetFile);

        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(file.getOriginalFilename());
        fileInfo.setDigest(digest);
        fileInfo.setCreatorId(userUtil.getCurrentUserId(request));
        fileInfo.setCreateTime(new Date());
        if (fileInfoService.findByDigest(digest) != null) {
            throw new PersistenceException.RepetitiveParamException("文件重复");
        }
        return fileInfoService.save(fileInfo);
    }

    @GetMapping("/download")
    public void download(@RequestParam("fileName") String fileName, HttpServletResponse response)
            throws StorageException.StorageFileException, UnsupportedEncodingException {
        FileUtil.download(filePath, fileName, response);
    }
}
