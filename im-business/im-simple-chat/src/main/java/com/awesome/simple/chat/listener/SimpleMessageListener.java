package com.awesome.simple.chat.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * projectName：imcloud
 * className ：SimpleMessageListener
 * class desc：TODO
 * createTime：2019/12/15 9:04 PM
 * creator：awesome
 * @author awesome
 */
@Component
public class SimpleMessageListener {


    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "im-simple-message"),
                    exchange = @Exchange(value = "im",
                            type = ExchangeTypes.DIRECT),

                    key = "simple-message"),
            autoStartup = "true"
    )
    public void handleMessage(byte[] message){
        System.out.println("消费消息");
        System.out.println(new String(message));
    }
}
