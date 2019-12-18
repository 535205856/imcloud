package com.awesome.simple.chat.service.impl;

import com.awesome.simple.chat.entity.PushMessage;
import com.awesome.simple.chat.mapper.PushMessageMapper;
import com.awesome.simple.chat.service.IPushMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息发送表 服务实现类
 * </p>
 *
 * @author awesome.li
 * @since 2019-12-16
 */
@Service
public class PushMessageServiceImpl extends ServiceImpl<PushMessageMapper, PushMessage> implements IPushMessageService {

}
