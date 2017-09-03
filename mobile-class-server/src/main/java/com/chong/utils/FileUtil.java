package com.chong.utils;

import com.chong.exception.ParamException;
import com.chong.exception.PersistenceException;
import com.chong.exception.StorageException;
import com.chong.validator.BaseValidator;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 文件上传／下载工具类
 * Created by LXChild on 07/04/2017.
 */
public final class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
    }

    public static String upload(String path, MultipartFile file) throws IOException {
        BaseValidator.notEmptyString(path, "文件路径不能为空");
        if (file == null || file.isEmpty()) {
            throw new ParamException.NullParamException("文件不能为空");
        }
        // 计算文件大小
        long size = file.getSize() / (1024 * 1024);
        if (size > 50) {
            throw new StorageException.StorageFileException("文件大小不得超过50Mb");
        }

        File fileDir = new File(path);
        if (!fileDir.exists() && !fileDir.mkdirs()) {
            throw new StorageException.StorageFileException("创建文件／目录失败");
        }
        File targetFile = new File(path, file.getOriginalFilename());
        if (!targetFile.exists()) {
            file.transferTo(targetFile);
        } else {
            throw new PersistenceException.RepetitiveParamException("文件名重复");
        }

        return targetFile.getAbsolutePath();
    }

    public static void download(String filePath, String fileName, HttpServletResponse response)
            throws StorageException.StorageFileException {
        BaseValidator.notEmptyString(filePath, "文件路径不能为空");
        BaseValidator.notEmptyString(fileName, "文件名不能为空");
        File file = new File(filePath, fileName);
        try (
                InputStream input = new FileInputStream(file);
        ) {
            byte[] data = IOUtils.toByteArray(input);
            String fName = URLEncoder.encode(fileName, "UTF-8");
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fName + "\"");
            response.addHeader("Content-Length", Integer.toString(data.length));
            response.setContentType("application/octet-stream; charset=UTF-8");
            IOUtils.write(data, response.getOutputStream());
        } catch (IOException e) {
            LOGGER.error("文件下载异常,文件名称：{}", fileName, e);
            throw new StorageException.StorageFileException(String.format("文件{%s}下载异常，请联系管理员", fileName));
        }
    }
}
