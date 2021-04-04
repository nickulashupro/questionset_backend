package com.questionset.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.questionset.model.entity.BmsTip;

public interface IBmsTipService extends IService<BmsTip> {
    BmsTip getRandomTip();
}
