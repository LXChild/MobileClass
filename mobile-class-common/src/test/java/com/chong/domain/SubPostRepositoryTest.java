package com.chong.domain;

import com.chong.utils.PageableUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * 测试子贴数据查询
 * Created by LXChild on 17/04/2017.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SubPostRepositoryTest {

    @Autowired
    private SubPostRepository repository;

    @Test
    public void findByParentId() throws Exception {
//        assertNotNull(repository.findByParentId(115L, PageableUtils.basicPage(0)));
    }
}