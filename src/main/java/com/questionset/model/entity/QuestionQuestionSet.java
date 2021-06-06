package com.questionset.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@Accessors(chain = true)
@TableName("i_question_questionset")
@NoArgsConstructor
@AllArgsConstructor
public class QuestionQuestionSet implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 题集ID
     */
    @TableField("questionset_id")
    private String questionset_id;

    /**
     * 题ID
     */
    @TableField("question_id")
    private String question_id;

    /**
     * 得分,题目出现的判断权重
     */
    @TableField("score")
    private Integer score;

}
