package com.awesome.cloud.im.gateway.server.dispatcher;

import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * projectName：im-gateway-tcp
 * className ：DispatcherInstance
 * class desc：分发系统实列
 * createTime：2019/11/8 1:21 PM
 * creator：awesome
 * @author awesome
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DispatcherInstance {


    /**
     *  于dispathcer通讯到 socketChannel
     */
    private Channel channel;




}
