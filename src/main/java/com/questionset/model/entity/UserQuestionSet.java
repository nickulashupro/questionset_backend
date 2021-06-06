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
@TableName("i_user_questionset")
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestionSet implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 题集ID
     */
    @TableField("questionset_id")
    private String questionset_id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;
}
