package com.questionset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.questionset.model.entity.QuestionSet;
import com.questionset.model.vo.QuestionSetVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionSetMapper extends BaseMapper<QuestionSet> {
    Page<QuestionSetVO> selectListAndPage(@Param("page") Page<QuestionSetVO> page,@Param("userid") String userid);
}
