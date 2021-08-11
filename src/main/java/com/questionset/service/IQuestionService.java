package com.questionset.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.questionset.model.dto.QuestionDTO;
import com.questionset.model.entity.Question;
import com.questionset.model.entity.UmsUser;
import com.questionset.model.vo.QuestionVO;

public interface IQuestionService extends IService<Question> {

    Question create(QuestionDTO dto, UmsUser user);

    /*
    根据题集ID获取对应的题目集合
     */
    Page<QuestionVO> getQuestionListBySetId(Page<QuestionVO> page, String id,String type);

    String generateJsonStr(String normalStr);
}
