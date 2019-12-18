package com.awesome.simple.chat.service.impl;

import com.awesome.simple.chat.entity.DispatchMessage;
import com.awesome.simple.chat.mapper.DispatchMessageMapper;
import com.awesome.simple.chat.service.IDispatchMessageService;
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
public class DispatchMessageServiceImpl extends ServiceImpl<DispatchMessageMapper, DispatchMessage> implements IDispatchMessageService {

}
