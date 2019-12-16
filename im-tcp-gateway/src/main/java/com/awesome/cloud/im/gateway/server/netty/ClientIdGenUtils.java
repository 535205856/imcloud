package com.awesome.cloud.im.gateway.server.netty;

import io.netty.channel.Channel;

/**
 * projectName：imcloud
 * className ：ClientIdGenUtils
 * class desc：TODO
 * createTime：2019/12/12 10:46 PM
 * creator：awesome
 */
public class ClientIdGenUtils {

    public   static String getId(Channel channel){
           return channel.id().asShortText();
    }
}

