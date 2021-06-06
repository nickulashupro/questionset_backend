package com.questionset.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.questionset.mapper.QuestionQuestionSetMapper;
import com.questionset.model.entity.Question;
import com.questionset.model.entity.QuestionQuestionSet;
import com.questionset.service.IQuestionQuestionSetService;
import org.springframework.stereotype.Service;

@Service
public class IQuestionQuestionSetServiceImpl extends ServiceImpl<QuestionQuestionSetMapper, QuestionQuestionSet> implements IQuestionQuestionSetService {

    @Override
    public QuestionQuestionSet create(Question question, String setId) {
        QuestionQuestionSet relation = QuestionQuestionSet.builder()
                .questionset_id(setId)
                .question_id(question.getId())
                .score(0)
                .build();
        this.baseMapper.insert(relation);
        return relation;
    }

    @Override
    public boolean removeByQuestionId(String questionId) {
        int count = this.baseMapper.removeByQuestionId(questionId);
        return count > 0;
    }
}
