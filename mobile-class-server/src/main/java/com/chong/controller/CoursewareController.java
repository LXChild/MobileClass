package com.chong.controller;

import com.chong.dataobject.Result;
import com.chong.exception.StorageException;
import com.chong.service.CoursewareService;
import com.chong.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 课件管理接口
 * Created by LXChild on 07/04/2017.
 */
@RestController
@RequestMapping("/api/v1/coursewares")
@Api(value = "课件管理接口", protocols = "JSON")
public class CoursewareController {

    private final CoursewareService coursewareService;

    @Autowired
    public CoursewareController(CoursewareService coursewareService) {
        this.coursewareService = coursewareService;
    }

    @GetMapping("/{id}")
    @ApiParam(required = true, name = "id", value = "课件 ID")
    @ApiOperation(value = "根据 ID 获取课件信息", httpMethod = "GET", response = Result.class, notes = "根据 ID 获取课件信息")
    public Result findOne(HttpServletRequest request, @PathVariable("id") Long id, HttpServletResponse response)
            throws StorageException.StorageFileException {
        return ResultUtils.success(String.valueOf(request.getRequestURL()), coursewareService.findOne(id, response));
    }

    @GetMapping
    @ApiParam(required = true, name = "name", value = "课件名称")
    @ApiOperation(value = "根据名称获取课件信息", httpMethod = "GET", response = Result.class, notes = "根据名称获取课件信息")
    public Result findByName(HttpServletRequest request, @RequestParam("name") String name, HttpServletResponse response)
            throws StorageException.StorageFileException {
        return ResultUtils.success(String.valueOf(request.getRequestURL()), coursewareService.findByName(name, response));
    }

    @GetMapping("/creator/{creatorId}")
    @ApiParam(required = true, name = "creatorId", value = "创建者 ID")
    @ApiOperation(value = "根据创建者 ID 获取课件信息", httpMethod = "GET", response = Result.class, notes = "根据创建者 ID 获取课件信息")
    public Result findByCreatorId(HttpServletRequest request, @PathVariable("creatorId") Long creatorId) {
        return ResultUtils.success(request.getRequestURI(), coursewareService.findByCreatorId(creatorId));
    }

    @GetMapping("/course/{courseId}")
    @ApiParam(required = true, name = "courseId", value = "课程 ID")
    @ApiOperation(value = "根据课程 ID 获取课件信息", httpMethod = "GET", response = Result.class, notes = "根据课程 ID 获取课件信息")
    public Result findByCourseId(HttpServletRequest request, @PathVariable("courseId") Long courseId) {
        return ResultUtils.success(request.getRequestURI(), coursewareService.findByCourseId(courseId));
    }

    @PostMapping
    @ApiParam(required = true, name = "file", value = "课件文件")
    @ApiOperation(value = "上传课件信息", httpMethod = "POST", response = Result.class, notes = "上传课件信息")
    public Result save(HttpServletRequest request, @RequestParam("file") MultipartFile file,
                       @RequestParam("creatorId") Long creatorId, @RequestParam("courseId") Long courseId) throws Exception {
        return ResultUtils.success(String.valueOf(request.getRequestURL()), coursewareService.save(creatorId, courseId, file));
    }
}
