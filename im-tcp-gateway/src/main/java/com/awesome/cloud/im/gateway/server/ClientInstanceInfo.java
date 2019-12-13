package com.awesome.cloud.im.gateway.server;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * projectName：imcloud
 * className ：InstanceInfo
 * class desc：TODO
 * createTime：2019/12/12 10:29 PM
 * creator：awesome
 */
//@Slf4j
@Data
public class ClientInstanceInfo {

   private String channleId;

   protected Channel channel;

   private long timestamp;

   private Integer isAuth;

}
