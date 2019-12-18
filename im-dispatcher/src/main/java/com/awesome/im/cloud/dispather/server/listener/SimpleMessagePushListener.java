package com.awesome.im.cloud.dispather.server.listener;

import cn.hutool.json.JSONUtil;
import com.awesome.cloud.common.RabbitMQConstant;
import com.awesome.cloud.common.base.CommonConstant;
import com.awesome.cloud.common.base.CommonReponse;
import com.awesome.cloud.common.dto.SimplePushMessage;
import com.awesome.im.cloud.dispather.server.netty.GateWaySessionManager;
import com.awesome.im.cloud.proto.ImCommunicationProto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * projectName：imcloud
 * className ：SimpleMessagePushListener
 * class desc：TODO
 * createTime：2019/12/15 9:04 PM
 * creator：awesome
 * @author awesome
 */
@Component
@Slf4j
public class SimpleMessagePushListener {




    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = RabbitMQConstant.IM_SIMPLE_MESSAGE_PUSH_QUEUE_NAME),
                    exchange = @Exchange(value = RabbitMQConstant.PROGRAMMATICALLY_EXCHANGE),
                    key = RabbitMQConstant.IM_SIMPLE_MESSAGE_PUSH_KEY),
            autoStartup = "true"
    )
    public void handleMessage(Message message){

        String json =new String(message.getBody());
        SimplePushMessage simpleMessageRequest= JSONUtil.toBean(json,SimplePushMessage.class);
        log.info("消费消息 message"+simpleMessageRequest+"====");
        ImCommunicationProto.CommonMessage commonMessage = CommonReponse.buildReponse(200, JSONUtil.toJsonStr(simpleMessageRequest), System.currentTimeMillis(), CommonConstant.REPONSE_TYPE_MESSAGE_PUSH);
        GateWaySessionManager.getIntance().getSession(simpleMessageRequest.getSendUserId()).writeAndFlush(commonMessage);
        log.info("推送返回 gateway 成功-");
    }
}
