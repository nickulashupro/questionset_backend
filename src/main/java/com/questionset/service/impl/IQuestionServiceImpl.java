package com.questionset.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.questionset.mapper.QuestionMapper;
import com.questionset.mapper.QuestionQuestionSetMapper;
import com.questionset.model.dto.QuestionDTO;
import com.questionset.model.entity.Question;
import com.questionset.model.entity.QuestionQuestionSet;
import com.questionset.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class IQuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private QuestionQuestionSetMapper questionQuestionSetMapper;

    @Override
    public Question create(QuestionDTO dto) {
        Question question = Question.builder()
                .question(dto.getQuestion())
                .answer(dto.getAnswer())
                .options(dto.getOptions())
                .remind(dto.getRemind())
                .typeId(dto.getTypeId())
                .questionType(dto.getQuestionType())
                .build();
        this.baseMapper.insert(question);
        return question;
    }

    @Override
    public Page<Question> getQuestionListBySetId(Page<Question> page, String id) {
        Page<QuestionQuestionSet> relationPage = new Page<>(page.getCurrent(), page.getSize());
        relationPage.setTotal(page.getTotal());
        Page<QuestionQuestionSet> questionQuestionSetPage = questionQuestionSetMapper.selectQuestionQuestionSetList(relationPage, id);
        if(questionQuestionSetPage.getRecords().size() == 0) return null;

        Set<String> set = new HashSet<>();
        for (QuestionQuestionSet item : questionQuestionSetPage.getRecords()) {
            set.add(item.getQuestion_id());
        }

        List<Question> questions = questionService.listByIds(set);
        Page<Question> QuestionPage = new Page<>(questionQuestionSetPage.getCurrent(), questionQuestionSetPage.getSize());
        QuestionPage.setTotal(questionQuestionSetPage.getTotal());
        QuestionPage.setRecords(questions);
        return QuestionPage;
    }
}
