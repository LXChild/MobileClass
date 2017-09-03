package com.chong.controller;

import com.chong.dataobject.Result;
import com.chong.entity.Course;
import com.chong.enums.ResultCode;
import com.chong.service.CourseService;
import com.chong.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 课程管理接口
 * Created by LXChild on 07/04/2017.
 */
@RestController
@RequestMapping("/api/v1/courses")
@Api(value = "课程管理接口", protocols = "JSON")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    @ApiOperation(value = "获取所有课程信息", httpMethod = "GET", response = Result.class, notes = "获取所有课程信息")
    public Result findAll(HttpServletRequest request) {
        return ResultUtils.success(String.valueOf(request.getRequestURL()), courseService.findAll());
    }

    @GetMapping("/{id}")
    @ApiParam(required = true, name = "id", value = "课程 ID")
    @ApiOperation(value = "根据 ID 获取课程信息", httpMethod = "GET", response = Result.class, notes = "根据 ID 获取课程信息")
    public Result findOne(HttpServletRequest request, @PathVariable("id") Long id) {
        return ResultUtils.success(String.valueOf(request.getRequestURL()), courseService.findOne(id));
    }

    @GetMapping("/teacher/{teacherId}")
    @ApiParam(required = true, name = "teacherId", value = "授课老师 ID")
    @ApiOperation(value = "根据授课老师 ID 获取课程信息", httpMethod = "GET", response = Result.class,
            notes = "根据授课老师 ID 获取课程信息")
    public Result findByTeacherId(HttpServletRequest request, @PathVariable("teacherId") Long teacherId) {
        return ResultUtils.success(String.valueOf(request.getRequestURL()), courseService.findByTeacherId(teacherId));
    }

    @GetMapping("/name/{name}")
    @ApiParam(required = true, name = "name", value = "课程名称")
    @ApiOperation(value = "根据课程名称获取课程信息", httpMethod = "GET", response = Result.class, notes = "根据课程名称获取课程信息")
    public Result findByName(HttpServletRequest request, @PathVariable("name") String name) {
        return ResultUtils.success(String.valueOf(request.getRequestURL()), courseService.findByName(name));
    }

    @PostMapping
    @ApiParam(required = true, name = "Course", value = "课程实体类")
    @ApiOperation(value = "创建课程", httpMethod = "POST", response = Result.class, notes = "创建课程")
    public Result save(HttpServletRequest request, @RequestBody @Valid Course course, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultCode.FAILED, request.getRequestURI(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(String.valueOf(request.getRequestURL()), courseService.save(course));
    }
}
