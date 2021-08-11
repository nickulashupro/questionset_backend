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
@TableName("i_question")
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 问题
     */
    @TableField("question")
    private String question;

    /**
     * 答案
     */
    @TableField("answer")
    private String answer;

    /**
     * 选项
     */
    @TableField("options")
    private String options;

    /**
     * 提示
     */
    @TableField("remind")
    private String remind;

    /**
     * 知识类型id
     */
    @TableField(value = "type_id")
    private String typeId;

    /**
     * 题型
     */
    @TableField(value = "question_type")
    private Integer questionType;

    /**
     * 创建人id
     */
    @TableField(value = "creat_user_id")
    private String creatUserId;

    /**
     * 创建人时间
     */
    @TableField(value = "creat_time")
    private Date creatTime;
}
