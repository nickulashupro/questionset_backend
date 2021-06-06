package com.questionset.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.questionset.common.api.ApiResult;
import com.questionset.model.dto.QuestionDTO;
import com.questionset.model.entity.Question;
import com.questionset.model.entity.QuestionQuestionSet;
import com.questionset.service.IQuestionQuestionSetService;
import com.questionset.service.IQuestionService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/question")
public class QuestionController extends BaseController {

    @Resource
    private IQuestionService questionService;

    @Resource
    private IQuestionQuestionSetService questionQuestionSetService;

    @GetMapping("/list")
    public ApiResult<Page<Question>> getQuestions(@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                                                  @RequestParam(value = "setId") String setId){
        Page<Question> questions = questionService.getQuestionListBySetId(new Page<>(pageNo, pageSize), setId);
        return ApiResult.success(questions);
    }

    @PostMapping("/create")
    public ApiResult<Question> add_question(@RequestBody QuestionDTO dto){
        Question question;
        if(StringUtils.hasLength(dto.getId())){
            question = questionService.getById(dto.getId());
            question.setQuestion(dto.getQuestion());
            question.setQuestionType(dto.getQuestionType());
            question.setAnswer(dto.getAnswer());
            question.setOptions(dto.getOptions());
            question.setRemind(dto.getRemind());
            question.setTypeId(dto.getTypeId());
            questionService.updateById(question);
        } else {
            question = questionService.create(dto);
            QuestionQuestionSet questionQuestionSet = questionQuestionSetService.create(question, dto.getSetId());
            Assert.notNull(questionQuestionSet, "创建失败，请重新操作");
        }

        return ApiResult.success(question);
    }
//
//    @PostMapping("/update")
//    public ApiResult<Question> update_question(@RequestBody QuestionDTO dto){
//        Assert.notNull(dto.getId(), "题目不存在，请检查操作是否错误");
//        Question question = questionService.getById(dto.getId());
//        question.setQuestion(dto.getQuestion());
//        question.setQuestionType(dto.getQuestionType());
//        question.setAnswer(dto.getAnswer());
//        question.setOptions(dto.getOptions());
//        question.setRemind(dto.getRemind());
//        question.setTypeId(dto.getTypeId());
//
//        questionService.updateById(question);
//        return ApiResult.success(question);
//    }

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
