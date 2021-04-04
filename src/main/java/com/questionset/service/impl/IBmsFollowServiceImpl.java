package com.questionset.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.questionset.mapper.BmsFollowMapper;
import com.questionset.model.entity.BmsFollow;
import com.questionset.service.IBmsFollowService;
import org.springframework.stereotype.Service;


@Service
public class IBmsFollowServiceImpl extends ServiceImpl<BmsFollowMapper, BmsFollow> implements IBmsFollowService {
}
