package com.chong.controller;

import com.chong.dataobject.PaperDO;
import com.chong.dataobject.Result;
import com.chong.entity.*;
import com.chong.enums.ResultCode;
import com.chong.service.*;
import com.chong.utils.ResultUtils;
import com.chong.validator.BaseValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 试卷管理接口
 * Created by LXChild on 05/04/2017.
 */
@RestController
@RequestMapping("/api/v1/papers")
@Api(value = "试卷管理接口", protocols = "JSON")
public class PaperController {

    private final PaperService paperService;

    private final SingleSelService singleSelService;

    private final MultiSelService multiSelService;

    private final RightWrongService rightWrongService;

    private final FillingBlankService fillingBlankService;

    private final EssayService essayService;

    private final AnswerService answerService;

    @Autowired
    public PaperController(PaperService paperService, SingleSelService singleSelService,
                           MultiSelService multiSelService, RightWrongService rightWrongService,
                           FillingBlankService fillingBlankService, EssayService essayService,
                           AnswerService answerService) {
        this.paperService = paperService;
        this.singleSelService = singleSelService;
        this.multiSelService = multiSelService;
        this.rightWrongService = rightWrongService;
        this.fillingBlankService = fillingBlankService;
        this.essayService = essayService;
        this.answerService = answerService;
    }

    @GetMapping("/{id}")
    @ApiParam(required = true, name = "id", value = "试卷 ID")
    @ApiOperation(value = "根据 ID 获取试卷信息", httpMethod = "GET", response = Result.class, notes = "根据 ID 获取试卷信息")
    public Result get(HttpServletRequest request, @PathVariable("id") Long id) {
        Paper paper = paperService.findOne(id);
        BaseValidator.notEmpty(paper, String.format("id{%d}在数据库中查询不到结果", id));

        List<SingleSel> singleSels = singleSelService.findByPaperId(paper.getId());
        List<MultiSel> multiSels = multiSelService.findByPaperId(paper.getId());
        List<RightWrong> rightWrongs = rightWrongService.findByPaperId(paper.getId());
        List<FillingBlank> fillingBlanks = fillingBlankService.findByPaperId(paper.getId());
        List<Essay> essays = essayService.findByPaperId(paper.getId());
        PaperDO paperDO = new PaperDO(paper, singleSels, multiSels, rightWrongs, fillingBlanks, essays);

        return ResultUtils.success(request.getRequestURI(), paperDO);
    }

    @PostMapping
    @ApiParam(required = true, name = "Paper", value = "试卷实体类")
    @ApiOperation(value = "添加试卷信息", httpMethod = "POST", response = Result.class, notes = "添加试卷信息")
    public Result savePaper(HttpServletRequest request, @RequestBody @Valid Paper paper, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultCode.FAILED, String.valueOf(request.getRequestURL()),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(request.getRequestURI(), paperService.save(paper));
    }

    @PostMapping("/singleSel")
    @ApiParam(required = true, name = "SingleSel", value = "单选题实体类")
    @ApiOperation(value = "添加单选题", httpMethod = "POST", response = Result.class, notes = "添加单选题")
    public Result saveSingleSel(HttpServletRequest request, @RequestBody @Valid SingleSel singleSel,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultCode.FAILED, String.valueOf(request.getRequestURL()),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(request.getRequestURI(), singleSelService.save(singleSel));
    }

    @PostMapping("/multiSel")
    @ApiParam(required = true, name = "MultiSel", value = "多选题实体类")
    @ApiOperation(value = "添加多选题", httpMethod = "POST", response = Result.class, notes = "添加多选题")
    public Result saveMultiSel(HttpServletRequest request, @RequestBody @Valid MultiSel multiSel,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultCode.FAILED, String.valueOf(request.getRequestURL()),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(request.getRequestURI(), multiSelService.save(multiSel));
    }

    @PostMapping("/rightWrong")
    @ApiParam(required = true, name = "RightWrong", value = "对错题实体类")
    @ApiOperation(value = "添加对错题", httpMethod = "POST", response = Result.class, notes = "添加对错题")
    public Result saveRightWrong(HttpServletRequest request, @RequestBody @Valid RightWrong rightWrong,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultCode.FAILED, String.valueOf(request.getRequestURL()),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(request.getRequestURI(), rightWrongService.save(rightWrong));
    }

    @PostMapping("/fillingBlank")
    @ApiParam(required = true, name = "FillingBlank", value = "填空题实体类")
    @ApiOperation(value = "添加填空题", httpMethod = "POST", response = Result.class, notes = "添加填空题")
    public Result saveFillingBlank(HttpServletRequest request, @RequestBody @Valid FillingBlank fillingBlank,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultCode.FAILED, String.valueOf(request.getRequestURL()),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(request.getRequestURI(), fillingBlankService.save(fillingBlank));
    }

    @PostMapping("/essay")
    @ApiParam(required = true, name = "Essay", value = "简答题实体类")
    @ApiOperation(value = "添加简答题", httpMethod = "POST", response = Result.class, notes = "添加简答题")
    public Result saveEssay(HttpServletRequest request, @RequestBody @Valid Essay essay,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultCode.FAILED, String.valueOf(request.getRequestURL()),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(request.getRequestURI(), essayService.save(essay));
    }

    @PostMapping("/answer")
    @ApiParam(required = true, name = "answerInfo", value = "答案详情")
    @ApiOperation(value = "上传答案", httpMethod = "POST", response = Result.class, notes = "上传答案")
    public Result save(HttpServletRequest request, @RequestBody @Valid AnswerInfo answerInfo, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultCode.FAILED, String.valueOf(request.getRequestURL()),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(request.getRequestURI(), answerService.save(answerInfo));
    }
}
