package com.questionset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.questionset.model.entity.Question;
import com.questionset.model.vo.QuestionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionMapper extends BaseMapper<Question> {

//    Page<QuestionVO> getQuestionListBySetId(@Param("page") Page<QuestionVO> page,@Param("id") String id);
}
