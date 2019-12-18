package com.awesome.simple.chat.listener;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.awesome.cloud.common.RabbitMQConstant;
import com.awesome.cloud.common.dto.SimpleMessageRequest;
import com.awesome.cloud.common.dto.SimplePushMessage;
import com.awesome.simple.chat.entity.DispatchMessage;
import com.awesome.simple.chat.entity.PushMessage;
import com.awesome.simple.chat.mapper.DispatchMessageMapper;
import com.awesome.simple.chat.mapper.PushMessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * projectName：imcloud
 * className ：SimpleMessageListener
 * class desc：TODO
 * createTime：2019/12/15 9:04 PM
 * creator：awesome
 * @author awesome
 */
@Component
@Slf4j
public class SimpleMessageListener {


    @Autowired
    DispatchMessageMapper dispatchMessageMapper;
    @Autowired
    PushMessageMapper pushMessageMapper;


    @Autowired
    RabbitTemplate rabbitTemplate;


    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = RabbitMQConstant.IM_SIMPLE_MESSAGE_SEND_QUEUE_NAME),
                    exchange = @Exchange(value = RabbitMQConstant.PROGRAMMATICALLY_EXCHANGE),
                    key = RabbitMQConstant.IM_SIMPLE_MESSAGE_SEND_KEY),
            autoStartup = "true"
    )
    public void handleMessage(Message message){
        String json = new String(message.getBody());
        log.info("消费消息 message"+message+"====");

        SimpleMessageRequest simpleMessageRequest=JSONUtil.toBean(json,SimpleMessageRequest.class);
       log.info("消费消息 simpleMessageRequest"+JSONUtil.toJsonStr(simpleMessageRequest));
///       long id= IdUtil.createSnowflake(10,10).nextId();
        DispatchMessage dispatchMessage = DispatchMessage.builder().messageContent(simpleMessageRequest.getContent()).messageType("1").sendTime(new Date(simpleMessageRequest.getTimestamp()))
                .receiveUserId(simpleMessageRequest.getReceiveUserId()).sendUserId(simpleMessageRequest.getSendUserId()).build();
        int messageId = dispatchMessageMapper.insert(dispatchMessage);
        log.info("持久化mysql成功 messageId {} "+messageId);

        SimplePushMessage simplePushMessage=new SimplePushMessage();
        simplePushMessage.setContent(simpleMessageRequest.getContent());
        simplePushMessage.setReceiveUserId(simpleMessageRequest.getReceiveUserId());
        simplePushMessage.setSendUserId(simpleMessageRequest.getSendUserId());
        simplePushMessage.setTimestamp(simplePushMessage.getTimestamp());
        simplePushMessage.setMessageId(Long.valueOf(messageId));



        PushMessage pushMessage=new PushMessage();
        BeanUtil.copyProperties(dispatchMessage,pushMessage);
        pushMessage.setDelivered(0);
        pushMessageMapper.insert(pushMessage);


        //判断用户是否在线 和离线
        rabbitTemplate.convertAndSend(RabbitMQConstant.PROGRAMMATICALLY_EXCHANGE,RabbitMQConstant.IM_SIMPLE_MESSAGE_PUSH_KEY,JSONUtil.toJsonStr(simplePushMessage));

//        1。如果在线，推送到  im-dispatcher 按照推送链路返回到client


//        2。如果不在线 推送到离线消息服务---redis zsortset
    }
}
