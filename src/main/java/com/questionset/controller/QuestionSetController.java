package com.questionset.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.questionset.common.api.ApiResult;
import com.questionset.jwt.JwtUtil;
import com.questionset.model.dto.QuestionSetDTO;
import com.questionset.model.entity.QuestionSet;
import com.questionset.model.entity.UmsUser;
import com.questionset.model.vo.QuestionSetVO;
import com.questionset.service.IQuestionSetService;
import com.questionset.service.IUmsUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/questionset")
public class QuestionSetController extends BaseController {

    @Resource
    private IQuestionSetService questionSetService;

    @Resource
    private IUmsUserService umsUserService;

    @GetMapping("/list")
    public ApiResult<Page<QuestionSetVO>> getNotices(@RequestParam(value = "userid") String userid,
                                                   @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                                   @RequestParam(value = "size", defaultValue = "10") Integer pageSize){
        Page<QuestionSetVO> questionSetslist = questionSetService.getList(new Page<>(pageNo, pageSize), userid);
        return ApiResult.success(questionSetslist);
    }

    @PostMapping("/add_questionset")
    public ApiResult<QuestionSet> add_questionset(@RequestHeader(value = JwtUtil.USER_NAME) String userName, @RequestBody QuestionSetDTO dto){
        UmsUser user = umsUserService.getUserByUsername(userName);
        QuestionSet questionset = questionSetService.create(dto, user);
        return ApiResult.success(questionset);
    }

    @PostMapping("/update")
    public ApiResult<QuestionSet> update_questionset( @Valid @RequestBody QuestionSet set){
//        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        questionSetService.updateById(set);
        return ApiResult.success(set);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete_questionset(@PathVariable("id") String id){
//        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        QuestionSet set = questionSetService.getById(id);
        Assert.notNull(set, "这个错题集已不存在");
        questionSetService.removeById(id);
        return ApiResult.success(null, "删除成功");
    }

    @PostMapping("/create")
    public ApiResult<QuestionSet> create_questionset(@RequestHeader(value = JwtUtil.USER_NAME) String userName, @RequestBody QuestionSetDTO dto){
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        QuestionSet set = questionSetService.create(dto, umsUser);
        return ApiResult.success(set);
    }

    @GetMapping("/getInfoById")
    public ApiResult<Map<String, Object>> getInfoById(@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                                                      @RequestParam("id") String id) {
        Map<String, Object> map = questionSetService.getInfoById(new Page<>(pageNo, pageSize), id);
        return ApiResult.success(map);
    }
}
