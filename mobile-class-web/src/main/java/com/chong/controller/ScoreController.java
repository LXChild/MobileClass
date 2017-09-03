package com.chong.controller;

import com.chong.entity.AnswerInfo;
import com.chong.entity.Score;
import com.chong.entity.User;
import com.chong.properties.RoleProperties;
import com.chong.service.AnswerService;
import com.chong.service.ScoreService;
import com.chong.service.UserService;
import com.chong.utils.UserUtil;
import com.chong.vo.ScoreVO;
import com.chong.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 总分视图控制器
 * Created by LXChild on 27/04/2017.
 */
@Controller
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreService scoreService;

    private final UserService userService;

    private final AnswerService answerService;

    private final UserUtil userUtil;

    @Autowired
    public ScoreController(ScoreService scoreService, UserService userService, AnswerService answerService, UserUtil userUtil) {
        this.scoreService = scoreService;
        this.userService = userService;
        this.answerService = answerService;
        this.userUtil = userUtil;
    }

    @GetMapping
    public String findByPaperId(Long paperId, ModelMap map) {
        map.addAttribute("paperId", paperId);
        return "/paper/score";
    }

    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public TableVO<ScoreVO> getScore(@RequestParam("paperId") Long paperId, String realName) {
        List<Score> scores = scoreService.findByPaperId(paperId);

        List<ScoreVO> scoreVOs = new ArrayList<>();
        for (Score score : scores) {
            User user = userService.findOne(score.getUserId());
            if (realName == null || "".equals(realName)) {
                scoreVOs.add(new ScoreVO(score, user));
            } else {
                if (realName.equals(user.getRealName())) {
                    scoreVOs.add(new ScoreVO(score, user));
                }
            }
        }
        return new TableVO<>((long) scoreVOs.size(), scoreVOs);
    }

    @PostMapping
    @ResponseBody
    public void countTotalScore(HttpServletRequest request, Long paperId) {
        Long userId = userUtil.getCurrentUserId(request);
        List<AnswerInfo> answerInfos = answerService.findByPaperIdAndUserId(paperId, userId);

        Integer score = 0;
        for (AnswerInfo info : answerInfos) {
            score += info.getScore();
        }
        scoreService.save(new Score(paperId, userId, score, new Date()));
    }
}
