package com.questionset.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.questionset.mapper.QuestionMapper;
import com.questionset.mapper.QuestionQuestionSetMapper;
import com.questionset.model.dto.QuestionDTO;
import com.questionset.model.entity.Question;
import com.questionset.model.entity.QuestionQuestionSet;
import com.questionset.model.entity.UmsUser;
import com.questionset.model.vo.QuestionVO;
import com.questionset.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class IQuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Autowired
    private QuestionQuestionSetMapper questionQuestionSetMapper;

    @Override
    public Question create(QuestionDTO dto, UmsUser user) {
        String optionsstr ="";
        if(dto.getOptions().length()>0){
            optionsstr = generateJsonStr(dto.getOptions());
        }
        Question question = Question.builder()
                .question(dto.getQuestion())
                .answer(dto.getAnswer())
                .options(optionsstr)
                .remind(dto.getRemind())
                .typeId(dto.getTypeId())
                .questionType(dto.getQuestionType())
                .creatTime(new Date())
                .creatUserId(user.getId())
                .build();
        this.baseMapper.insert(question);
        return question;
    }

    @Override
    public Page<QuestionVO> getQuestionListBySetId(Page<QuestionVO> page, String id,String type) {
        Page<QuestionQuestionSet> relationPage = new Page<>(page.getCurrent(), page.getSize());
        relationPage.setTotal(page.getTotal());

        return questionQuestionSetMapper.selectQuestionQuestionSetList(relationPage, id, type);
    }

    @Override
    public String generateJsonStr(String normalStr){
        StringBuilder result = new StringBuilder("[");
        Pattern itemPattern = Pattern.compile("[ABCDEFG][\\.|、]\\s*[^ABCDEFG]*");
        Matcher itemMatcher = itemPattern.matcher(normalStr);
        Pattern subPattern = Pattern.compile("[^ABCDEFG\\s.、]{1}.+");
        while (itemMatcher.find()) {
            String strTemp=itemMatcher.group(0);
            Matcher subMatcher = subPattern.matcher(strTemp);
            boolean isMatch = subMatcher.find();
            String key = strTemp.substring(0,1);
            result.append("{\"key\":\"");
            result.append(key);
            result.append("\",");

            String value = "";
            if(isMatch){
                value = subMatcher.group(0);

                result.append("\"value\":\"");
                result.append(value);
                result.append("\"},");
            }
        }
        result.deleteCharAt(result.length() - 1);
        result.append("]");
        return result.toString().replace("\t"," ");
    }
}
