package com.questionset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.questionset.model.entity.QuestionQuestionSet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionQuestionSetMapper extends BaseMapper<QuestionQuestionSet> {

    Page<QuestionQuestionSet> selectQuestionQuestionSetList(@Param("page") Page<QuestionQuestionSet> page,@Param("id") String id);

    int removeByQuestionId(@Param("questionId") String questionId);
}
