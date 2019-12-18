package com.awesome.im.cloud.dispather.server.config;

import com.awesome.cloud.common.RabbitMQConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * projectName：imcloud
 * className ：RabbitmqConfig
 * class desc：TODO
 * createTime：2019/12/18 14:23
 * creator：awesome
 * @author awesome
 */
@Configuration
public class RabbitmqConfig {


    /**
     * 队列
     * @return
     */
    @Bean
    Queue queue() {
        return new Queue(RabbitMQConstant.IM_SIMPLE_MESSAGE_PUSH_QUEUE_NAME, true);
    }


    /**
     * 交换器
     * @return
     */
    @Bean
    DirectExchange exchange() {
        return new DirectExchange(RabbitMQConstant.PROGRAMMATICALLY_EXCHANGE);
    }
    /**
     * 声明绑定关系
     * @return
     */
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(queue()).to(exchange()).with(RabbitMQConstant.IM_SIMPLE_MESSAGE_PUSH_KEY);
    }




    /**
     * 声明绑定关系
     * @return
     */
    @Bean
    Binding bindingDirect1() {
        return BindingBuilder.bind(queue1()).to(exchange()).with(RabbitMQConstant.IM_SIMPLE_MESSAGE_PUSH_KEY);
    }

    /**
     * 队列
     * @return
     */
    @Bean
    Queue queue1() {
        return new Queue(RabbitMQConstant.IM_SIMPLE_MESSAGE_PUSH_QUEUE_NAME, true);
    }




    @Bean
    RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        rabbitListenerContainerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        rabbitListenerContainerFactory.setDefaultRequeueRejected(Boolean.FALSE);
        return rabbitListenerContainerFactory;
    }

}
