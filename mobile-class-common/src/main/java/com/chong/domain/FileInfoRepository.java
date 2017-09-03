package com.chong.domain;

import com.chong.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 文件信息数据库操作
 * Created by LXChild on 29/04/2017.
 */
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {

    FileInfo findByName(String name);

    FileInfo findByDigest(String digest);
}
