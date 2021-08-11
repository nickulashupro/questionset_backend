package com.questionset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.questionset.mapper.QuestionQuestionSetMapper;
import com.questionset.model.entity.BmsTopicTag;
import com.questionset.model.entity.Question;
import com.questionset.model.entity.QuestionQuestionSet;
import com.questionset.service.IQuestionQuestionSetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IQuestionQuestionSetServiceImpl extends ServiceImpl<QuestionQuestionSetMapper, QuestionQuestionSet> implements IQuestionQuestionSetService {

    @Override
    public QuestionQuestionSet create(Question question, String setId) {
        //获取习题集下所有关系对象
        QueryWrapper<QuestionQuestionSet> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(QuestionQuestionSet::getQuestionset_id, setId)
                        .orderByAsc(QuestionQuestionSet::getOrderNum);
        List<QuestionQuestionSet> all = this.baseMapper.selectList(wrapper);

        int maxOrderNum =0;
        maxOrderNum = this.baseMapper.selectMaxOrderNum(setId);
        if(all==null){
            maxOrderNum=1;
        }else if(all.size()==maxOrderNum){
            maxOrderNum++;
        } else{
            //重新排序
            for (int i = 0; i < all.size() ; i++) {
                if(all.get(i).getOrderNum()>(i+1)){
                    all.get(i).setOrderNum(i+1);
                    this.baseMapper.updateById(all.get(i));
                }
            }
            maxOrderNum = all.size()+1;
        }

        QuestionQuestionSet relation = QuestionQuestionSet.builder()
                .questionset_id(setId)
                .question_id(question.getId())
                .score(0)
                .orderNum(maxOrderNum)
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
