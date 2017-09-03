package com.chong.controller;

import com.chong.dataobject.*;
import com.chong.entity.*;
import com.chong.enums.BulletinPositionEnum;
import com.chong.enums.QuestionTypeEnum;
import com.chong.properties.RoleProperties;
import com.chong.service.*;
import com.chong.utils.UserUtil;
import com.chong.validator.BaseValidator;
import com.chong.vo.PaperVO;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 试卷控制器
 * Created by LXChild on 17/04/2017.
 */
@Controller
@RequestMapping("/papers")
public class PaperController {

    private final PaperService paperService;

    private final SingleSelService singleSelService;

    private final MultiSelService multiSelService;

    private final RightWrongService rightWrongService;

    private final FillingBlankService fillingBlankService;

    private final EssayService essayService;

    private final UserService userService;

    private final AnswerService answerService;

    private final CourseService courseService;

    private final ScoreService scoreService;

    private final BulletinService bulletinService;

    private final UserUtil userUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(PaperController.class);

    @Autowired
    public PaperController(PaperService paperService, UserService userService, SingleSelService singleSelService,
                           MultiSelService multiSelService, RightWrongService rightWrongService,
                           FillingBlankService fillingBlankService, EssayService essayService,
                           AnswerService answerService, UserUtil userUtil, CourseService courseService,
                           ScoreService scoreService, BulletinService bulletinService) {
        this.paperService = paperService;
        this.userService = userService;
        this.multiSelService = multiSelService;
        this.rightWrongService = rightWrongService;
        this.fillingBlankService = fillingBlankService;
        this.essayService = essayService;
        this.singleSelService = singleSelService;
        this.answerService = answerService;
        this.userUtil = userUtil;
        this.courseService = courseService;
        this.scoreService = scoreService;
        this.bulletinService = bulletinService;
    }

    @GetMapping
    public String findAll(HttpServletRequest request, ModelMap map, String name) {
        String pageNum = request.getParameter("pageNum");

        Page<Paper> papers;
        if (name != null && !"".equals(name)) {
            papers = paperService.findAllByShowPaperAndNameLike((pageNum == null || "".equals(pageNum)) ? 0 :
                    Integer.valueOf(pageNum) - 1, name);
        } else {
            papers = paperService.findAll((pageNum == null || "".equals(pageNum)) ? 0 : Integer.valueOf(pageNum) - 1);
        }
        BaseValidator.notEmpty(papers);
        map.addAttribute("pageNum", papers.getNumber() + 1);
        map.addAttribute("pageSize", papers.getSize());
        map.addAttribute("isFirstPage", papers.isFirst());
        map.addAttribute("isLastPage", papers.isLast());
        map.addAttribute("totalPages", papers.getTotalPages() == 0 ? 1 : papers.getTotalPages());
        map.addAttribute("searchText", name == null ? "" : name);
        map.addAttribute("papers", papers);

        Bulletin bulletin = bulletinService.findByPosition(BulletinPositionEnum.PAPER);

        map.addAttribute("bulletin", bulletin == null ? "尚未发布公告" : bulletin.getContent());
        return "/paper/list";
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String save(HttpServletRequest request, Long courseId, String name, String remark,
                       String quizTime, Integer quizDuration) {
        String username = request.getRemoteUser();
        BaseValidator.notEmptyString(username);
        User user = userService.findByName(username);
        BaseValidator.notEmpty(user);
        BaseValidator.notEmpty(courseId);
        BaseValidator.notEmpty(user.getId());
        BaseValidator.notEmptyString(quizTime);
        BaseValidator.notEmpty(quizDuration);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = null;
        try {
            time = formatter.parse(quizTime);
        } catch (ParseException e) {
            LOGGER.error("创建试卷时格式化日期失败", e);
        }

        Paper paper = new Paper();
        paper.setCourseId(courseId);
        paper.setName(name);
        paper.setRemark(remark);
        paper.setCreateTime(new Date());
        paper.setCreatorId(user.getId());
        paper.setQuizTime(time);
        paper.setQuizDuration(quizDuration);
        Paper result = paperService.save(paper);
        BaseValidator.notEmpty(result);
        return "redirect:/papers/course/" + courseId;
    }

    @GetMapping("/{id}")
    public String findOne(HttpServletRequest request, ModelMap map, @PathVariable("id") Long id) throws NotFoundException {
        Paper paper = paperService.findOne(id);

        Long userId = userUtil.getCurrentUserId(request);

        if (!paper.getShowPaper() && !userId.equals(paper.getCreatorId())) {
            throw new NotFoundException("页面未找到");
        }

        List<SingleSel> singleSels = singleSelService.findByPaperId(id);
        List<MultiSel> multiSels = multiSelService.findByPaperId(id);
        List<RightWrong> rightWrongs = rightWrongService.findByPaperId(id);
        List<FillingBlank> fillingBlanks = fillingBlankService.findByPaperId(id);
        List<Essay> essays = essayService.findByPaperId(id);

        Integer totalScore = 0;

        List<SingleSelDO> singleSelDOs = new ArrayList<>();
        for (SingleSel singleSel : singleSels) {
            singleSelDOs.add(new SingleSelDO(singleSel, answerService.findQuestionAnswer(id,
                    userId, QuestionTypeEnum.SINGLESEL.getTag(), singleSel.getId())));
            totalScore += singleSel.getScore();
        }

        List<MultiSelDO> multiSelDOs = new ArrayList<>();
        for (MultiSel multiSel : multiSels) {
            multiSelDOs.add(new MultiSelDO(multiSel, answerService.findQuestionAnswer(id,
                    userId, QuestionTypeEnum.MULTISEL.getTag(), multiSel.getId())));
            totalScore += multiSel.getScore();
        }

        List<RightWrongDO> rightWrongDOs = new ArrayList<>();
        for (RightWrong rightWrong : rightWrongs) {
            rightWrongDOs.add(new RightWrongDO(rightWrong, answerService.findQuestionAnswer(id,
                    userId, QuestionTypeEnum.RIGHTWRONG.getTag(), rightWrong.getId())));
            totalScore += rightWrong.getScore();
        }

        List<FillingBlankDO> fillingBlankDOs = new ArrayList<>();
        for (FillingBlank fillingBlank : fillingBlanks) {
            fillingBlankDOs.add(new FillingBlankDO(fillingBlank, answerService.findQuestionAnswer(id,
                    userId, QuestionTypeEnum.FILLINGBLANK.getTag(), fillingBlank.getId())));
            totalScore += fillingBlank.getScore();
        }

        List<EssayDO> essayDOs = new ArrayList<>();
        for (Essay essay : essays) {
            essayDOs.add(new EssayDO(essay, answerService.findQuestionAnswer(id,
                    userId, QuestionTypeEnum.ESSAY.getTag(), essay.getId())));
            totalScore += essay.getScore();
        }


        if (!Objects.equals(userId, paper.getCreatorId())) {
            map.addAttribute("isCreator", false);
        } else {
            map.addAttribute("isCreator", true);
        }

        List<AnswerInfo> answerInfo = answerService.findByPaperIdAndUserId(paper.getId(), userId);
        if (answerInfo != null && !answerInfo.isEmpty()) {
            map.addAttribute("haveDone", true);
        } else {
            map.addAttribute("haveDone", false);
        }

        PaperVO paperVO = new PaperVO(paper, singleSelDOs, multiSelDOs, rightWrongDOs, fillingBlankDOs, essayDOs);

        Score score = scoreService.findByPaperIdAndUserId(paper.getId(), userId);


        map.addAttribute("score", score == null ? null : score.getTotalScore());
        map.addAttribute("totalScore", totalScore);
        map.addAttribute("showPaper", paper.getShowPaper());
        map.addAttribute("showAnswer", paper.getShowAnswer());
        map.addAttribute("paperVO", paperVO);
        return "/paper/detail";
    }

    @PostMapping("/showAnswer")
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String updateShowAnswer(Long id, Boolean show) {
        paperService.updateShowAnswer(id, show);
        return "redirect:/papers/" + id;
    }

    @PostMapping("/showPaper")
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "','" +
            "','" + RoleProperties.TEACHER + "')")
    public String updateShowPaper(Long id, Boolean show) {
        paperService.updateShowPaper(id, show);
        return "redirect:/papers/" + id;
    }

    @GetMapping("/course/{courseId}")
    public String findCoursePaperById(HttpServletRequest request, ModelMap map,
                                      @PathVariable("courseId") Long courseId) {
        String pageNum = request.getParameter("pageNum");
        Integer page = (pageNum == null || "".equals(pageNum)) ? 0 : Integer.valueOf(pageNum) - 1;
        Page<Paper> papers = paperService.findAllByCourseIdAndShowPaper(courseId, true, page);
        BaseValidator.notEmpty(papers);
        map.addAttribute("pageNum", papers.getNumber() + 1);
        map.addAttribute("pageSize", papers.getSize());
        map.addAttribute("isFirstPage", papers.isFirst());
        map.addAttribute("isLastPage", papers.isLast());
        map.addAttribute("totalPages", papers.getTotalPages() == 0 ? 1 : papers.getTotalPages());
        map.addAttribute("papers", papers);

        List<Paper> papersHide = paperService.findAllByCourseIdAndShowPaper(courseId, false);

        Course course = courseService.findOne(courseId);
        BaseValidator.notEmpty(course);

        if (!Objects.equals(userUtil.getCurrentUserId(request), course.getTeacherId())) {
            map.addAttribute("isCreator", false);
        } else {
            map.addAttribute("isCreator", true);
        }
        map.addAttribute("papersHide", papersHide);
        map.addAttribute("courseId", courseId);

        return "/course/paper";
    }
}
