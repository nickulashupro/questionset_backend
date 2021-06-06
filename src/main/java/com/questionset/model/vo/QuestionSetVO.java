package com.questionset.model.vo;

import com.questionset.model.entity.BmsTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSetVO implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 名称
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 用户昵称
     */
    private String createUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户昵称
     */
    private String alias;
    /**
     * 账号
     */
    private String username;
}
