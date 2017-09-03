package com.chong.controller;

import com.chong.entity.Carousel;
import com.chong.properties.RoleProperties;
import com.chong.service.CarouselService;
import com.chong.service.GlobalService;
import com.chong.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 页面跳转
 * Created by LXChild on 19/03/2017.
 */
@Controller
public class GlobalController {

    private final GlobalService globalService;

    private final CarouselService carouselService;

    private final UserUtil userUtil;

    @Value("${course.courseware.request-path}")
    private String requestPath;

    @Autowired
    public GlobalController(GlobalService globalService, UserUtil userUtil, CarouselService carouselService) {
        this.globalService = globalService;
        this.userUtil = userUtil;
        this.carouselService = carouselService;
    }

    @GetMapping({"/", "/index"})
    public String welcome(ModelMap map) {
        List<Carousel> carousels = carouselService.findAll();

        for (Carousel carousel : carousels) {
            map.addAttribute("carouselImg" + carousel.getPage(), carousel);
            map.addAttribute("carouselURL" + carousel.getPage(),
                    requestPath + carousel.getImgName());
        }
        map.addAttribute("hotPostDOs", globalService.findAllHotPosts());
        map.addAttribute("hotCourseDOs", globalService.findAllHotCourses());
        map.addAttribute("recentPapers", globalService.findRecentPaper());
        map.addAttribute("recentPapers", globalService.findRecentPaper());
        return "/index";
    }

    @PostMapping("/carousel")
    @PreAuthorize("hasRole('" + RoleProperties.SYS_ADMIN +"')")
    public String saveCarousel(HttpServletRequest request, @RequestParam("page") Integer page,
                                 @RequestParam("title") String title, @RequestParam("content") String content,
                                 @RequestParam("carouselImg")MultipartFile carouselImg,
                                 @RequestParam("pageUrl") String pageUrl) throws Exception {

        Carousel carousel = new Carousel();
        carousel.setTitle(title);
        carousel.setContent(content);
        carousel.setCreatorId(userUtil.getCurrentUserId(request));
        carousel.setPageUrl(pageUrl);
        carousel.setPage(page);
        Carousel carouselInRepository = carouselService.findByPage(page);
        if (carouselInRepository != null) {
            carouselService.update(page, carousel, carouselImg);
        } else {
            carouselService.save(carousel, carouselImg);
        }
        return "redirect:/";
    }

    @GetMapping("/test")
    public String test() throws Exception {
        throw new Exception("发生错误");
    }
}
