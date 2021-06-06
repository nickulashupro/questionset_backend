package com.questionset.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.questionset.model.dto.QuestionSetDTO;
import com.questionset.model.entity.Question;
import com.questionset.model.entity.QuestionSet;
import com.questionset.model.entity.UmsUser;
import com.questionset.model.vo.QuestionSetVO;

import java.util.List;
import java.util.Map;

public interface IQuestionSetService extends IService<QuestionSet> {

    QuestionSet create(QuestionSetDTO dto, UmsUser user);

    Page<QuestionSetVO> getList(Page<QuestionSetVO> page, String userid);

    Map<String, Object> getInfoById(Page<Question> page, String id);
}
