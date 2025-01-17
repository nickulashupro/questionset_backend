package com.questionset.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.questionset.model.dto.CommentDTO;
import com.questionset.model.entity.BmsComment;
import com.questionset.model.entity.UmsUser;
import com.questionset.model.vo.CommentVO;

import java.util.List;


public interface IBmsCommentService extends IService<BmsComment> {
    /**
     *
     *
     * @param topicid
     * @return {@link BmsComment}
     */
    List<CommentVO> getCommentsByTopicID(String topicid);

    BmsComment create(CommentDTO dto, UmsUser principal);
}
