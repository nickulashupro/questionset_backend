package com.questionset.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.questionset.mapper.BmsBillboardMapper;
import com.questionset.model.entity.BmsBillboard;
import com.questionset.service.IBmsBillboardService;
import org.springframework.stereotype.Service;

@Service
public class IBmsBillboardServiceImpl extends ServiceImpl<BmsBillboardMapper
        , BmsBillboard> implements IBmsBillboardService {

}
