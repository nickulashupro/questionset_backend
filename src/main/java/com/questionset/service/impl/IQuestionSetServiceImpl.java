package com.questionset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.questionset.mapper.QuestionSetMapper;
import com.questionset.model.dto.QuestionSetDTO;
import com.questionset.model.entity.*;
import com.questionset.model.vo.ProfileVO;
import com.questionset.model.vo.QuestionSetVO;
import com.questionset.model.vo.QuestionVO;
import com.questionset.service.IQuestionService;
import com.questionset.service.IQuestionSetService;
import com.questionset.service.IUmsUserService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

@Service
public class IQuestionSetServiceImpl extends ServiceImpl<QuestionSetMapper, QuestionSet> implements IQuestionSetService {

    @Autowired
    private IUmsUserService iUmsUserService;

    @Autowired
    private IQuestionService iQuestionService;

    @Override
    public QuestionSet create(QuestionSetDTO dto, UmsUser user) {
        QuestionSet set = QuestionSet.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .createUserId(user.getId())
                .createTime(new Date())
                .build();
        this.baseMapper.insert(set);
        return set;
    }

    @Override
    public Page<QuestionSetVO> getList(Page<QuestionSetVO> page, String userid) {
        Page<QuestionSetVO> iPage = this.baseMapper.selectListAndPage(page, userid);

        return iPage;
    }

    @Override
    public Map<String, Object> getInfoById(Page<QuestionVO> page, String id) {
        Map<String, Object> map = new HashMap<>(16);
        QuestionSet questionSet = this.baseMapper.selectById(id);
        if(questionSet==null)return null;
//        Assert.notNull(questionSet, "当前题集不存在,或已被删除");
        // 查询话题详情
        //topic.setView(topic.getView() + 1);
        this.baseMapper.updateById(questionSet);
        // emoji转码
        questionSet.setDescription(EmojiParser.parseToUnicode(questionSet.getDescription()));
        map.put("questionSet", questionSet);
        // 错题集合
        Page<QuestionVO> questions = iQuestionService.getQuestionListBySetId(page,id,null);
        map.put("questions", questions);
        // 作者
        ProfileVO user = iUmsUserService.getUserProfile(questionSet.getCreateUserId());
        map.put("user", user);

        return map;
    }
}
