package com.chong.schedulejob;

import com.chong.entity.HotPost;
import com.chong.service.HotPostService;
import com.chong.service.SubPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 热门帖子定时任务
 * Created by LXChild on 20/04/2017.
 */
@Component
public class HotPostsScheduleJob {

    private final static long HALF_AN_HOUR = 1800000;

    private final SubPostService subPostService;

    private final HotPostService hotPostService;

    @Autowired
    public HotPostsScheduleJob(SubPostService subPostService, HotPostService hotPostService) {
        this.subPostService = subPostService;
        this.hotPostService = hotPostService;
    }

    /**
     * 定时任务：每半小时执行一次，统计这半个小时内热门帖子并写入数据库
     */
    @Scheduled(fixedRate = HALF_AN_HOUR)
    public void StatisticsHotPosts() {
        List<HotPost> hotPosts = subPostService.findHotPosts();
        List<HotPost> hotPostsInRepository = hotPostService.findAll();

        if (hotPostsInRepository.size() < 5) {
            int count = 0;
            for (int i = 0; i < hotPostsInRepository.size(); i++) {
                count++;
                if (hotPostsInRepository.get(i).equals(hotPosts.get(i))) {
                    continue;
                }
                hotPostService.updateHotPost(i + 1, hotPosts.get(i));
            }
            for (int i = count; i < hotPosts.size(); i++) {
                hotPostService.save(hotPosts.get(i));
                
            }
        } else {
            for (int i = 0; i < hotPostsInRepository.size(); i++) {
                if (hotPostsInRepository.get(i).equals(hotPosts.get(i))) {
                    continue;
                }
                hotPostService.updateHotPost(i + 1, hotPosts.get(i));
            }
        }
    }
}
