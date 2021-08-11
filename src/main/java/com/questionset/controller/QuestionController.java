package com.questionset.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.questionset.common.api.ApiResult;
import com.questionset.jwt.JwtUtil;
import com.questionset.model.dto.QuestionDTO;
import com.questionset.model.entity.Question;
import com.questionset.model.entity.QuestionQuestionSet;
import com.questionset.model.entity.UmsUser;
import com.questionset.model.vo.QuestionVO;
import com.questionset.service.IQuestionQuestionSetService;
import com.questionset.service.IQuestionService;
import com.questionset.service.IUmsUserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/question")
public class QuestionController extends BaseController {
    @Resource
    private IUmsUserService umsUserService;

    @Resource
    private IQuestionService questionService;

    @Resource
    private IQuestionQuestionSetService questionQuestionSetService;

    @GetMapping("/list")
    public ApiResult<Page<QuestionVO>> getQuestions(@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                                                  @RequestParam(value = "setId") String setId){
        Page<QuestionVO> questions = questionService.getQuestionListBySetId(new Page<>(pageNo, pageSize), setId,null);
        return ApiResult.success(questions);
    }

    @GetMapping("/getQuestionList")
    public ApiResult<Page<QuestionVO>> getQuestionListWithType(@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                                    @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                                                    @RequestParam(value = "setId") String setId,
                                                    @RequestParam(value = "type") String type){
        Page<QuestionVO> questions = questionService.getQuestionListBySetId(new Page<>(pageNo, pageSize), setId,type);
        return ApiResult.success(questions);
    }

    @PostMapping("/create")
    public ApiResult<Question> add_question(@RequestHeader(value = JwtUtil.USER_NAME) String userName,@RequestBody QuestionDTO dto){
        UmsUser user = umsUserService.getUserByUsername(userName);
        Question question;
        if(StringUtils.hasLength(dto.getId())){
            String optionsstr ="";
            if(dto.getOptions().length()>0){
                optionsstr = questionService.generateJsonStr(dto.getOptions());
            }

            question = questionService.getById(dto.getId());
            question.setQuestion(dto.getQuestion());
            question.setQuestionType(dto.getQuestionType());
            question.setAnswer(dto.getAnswer());
            question.setOptions(optionsstr);
            question.setRemind(dto.getRemind());
            question.setTypeId(dto.getTypeId());
            questionService.updateById(question);
        } else {
            question = questionService.create(dto,user);
            QuestionQuestionSet questionQuestionSet = questionQuestionSetService.create(question, dto.getSetId());
            Assert.notNull(questionQuestionSet, "创建失败，请重新操作");
        }

        return ApiResult.success(question);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete_question(@PathVariable("id") String id){
        Question question = questionService.getById(id);
        Assert.notNull(question, "这个错题已不存在");
        questionService.removeById(id);
        //同时删除关联关系
        boolean result = questionQuestionSetService.removeByQuestionId(id);
        return ApiResult.success(null, "删除成功");
    }
}
