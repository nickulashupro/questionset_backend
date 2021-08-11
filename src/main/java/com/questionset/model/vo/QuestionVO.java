package com.questionset.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionVO implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 问题
     */
    private String question;
    /**
     * 答案
     */
    private String answer;
    /**
     * 选项
     */
    private String options;
    /**
     * 提示
     */
    private String remind;
    /**
     * 知识类型id
     */
    private String typeId;
    /**
     * 题型
     */
    private Integer questionType;
    /**
     * 排序
     */
    private Integer orderNum;
    /**
     * 题集ID
     */
    private String questionsetId;
    /**
     * 得分
     */
    private Integer score;

    private String creatUserId;

    private Date creatTime;

}
