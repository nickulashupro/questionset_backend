package com.questionset.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.questionset.model.dto.QuestionDTO;
import com.questionset.model.entity.Question;

public interface IQuestionService extends IService<Question> {

    Question create(QuestionDTO dto);

    Page<Question> getQuestionListBySetId(Page<Question> page, String id);
}
