package com.chong.domain;

import com.chong.entity.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 试卷 JPA 操作
 * Created by LXChild on 06/04/2017.
 */
public interface PaperRepository extends JpaRepository<Paper, Long> {

    Page<Paper> findAllByShowPaper(Boolean showPaper, Pageable pageable);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Paper set showAnswer =:show where id =:id")
    void updateShowAnswer(@Param("id") Long id, @Param("show") Boolean show);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Paper set showPaper =:show where id =:id")
    void updateShowPaper(@Param("id") Long id, @Param("show") Boolean show);

    Page<Paper> findAllByCourseIdAndShowPaper(Long courseId, Boolean showPaper, Pageable pageable);

    List<Paper> findAllByCourseIdAndShowPaper(Long courseId, Boolean showPaper);

    Page<Paper> findAllByShowPaperAndNameLike(Boolean showPaper, String name, Pageable pageable);
}
