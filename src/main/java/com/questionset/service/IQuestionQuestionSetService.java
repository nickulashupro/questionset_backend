package com.questionset.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.questionset.model.entity.Question;
import com.questionset.model.entity.QuestionQuestionSet;

public interface IQuestionQuestionSetService extends IService<QuestionQuestionSet> {

    QuestionQuestionSet create(Question question, String setId);

    boolean removeByQuestionId(String questionId);
}
