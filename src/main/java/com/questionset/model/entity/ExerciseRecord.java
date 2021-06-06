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
@TableName("i_exerciserecord")
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseRecord implements Serializable {

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
    private Integer questionsetId;

    /**
     * 题ID
     */
    @TableField("question_id")
    private Integer questionId;

    /**
     * 第n次做这个题
     */
    @TableField("num")
    private Integer num;

    /**
     * 本次结果的对错
     */
    @TableField(value = "answer")
    private Boolean answer;
}
