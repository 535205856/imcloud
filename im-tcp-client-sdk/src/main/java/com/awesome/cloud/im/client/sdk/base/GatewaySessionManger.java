package com.awesome.cloud.im.client.sdk.base;

import cn.hutool.core.util.RandomUtil;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * projectName：imcloud
 * className ：GatewaySessionManger
 * class desc：TODO
 * createTime：2019/12/13 4:56 PM
 * creator：awesome
 */
public class GatewaySessionManger {
    private Logger logger= Logger.getLogger(this.getClass().getName());

    private ConcurrentHashMap<String, Channel> clientSessionManager;

    private   GatewaySessionManger(){
        clientSessionManager=
                new ConcurrentHashMap<>();

    }

    private   static  class SingletonHandler{
        private  static  GatewaySessionManger gatewaySessionManger=new GatewaySessionManger();

    }

    public  void add(String channleId, Channel  channel){
        clientSessionManager.put(channleId, channel);
        logger.info("add success "+ channleId + " current size "+clientSessionManager.size());

    }


    public  void remove(String channleId ){
        clientSessionManager.remove(channleId);
        logger.info("remove  success "+ channleId + " current size "+clientSessionManager.size());
    }

    public static GatewaySessionManger getInstance(){
        return SingletonHandler.gatewaySessionManger;
    }



    public  Channel getAvailableChannel(){

        List<Channel> channels=new ArrayList<>();
        clientSessionManager.values().forEach(e->{
            channels.add(e);
        });

        int i = RandomUtil.randomInt(channels.size());
        Channel channel = channels.get(i);
        System.out.println(" 随机 channel "+channel+"  i "+i);
        return channel;

    }

}
