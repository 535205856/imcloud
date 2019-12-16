package com.awesome.cloud.im.gateway.server.dispatcher;

import com.awesome.im.cloud.proto.ImCommunicationProto;

import java.util.logging.Logger;

/**
 * projectName：imcloud
 * className ：DispatcherProccesor
 * class desc：TODO
 * createTime：2019/12/14 10:49 PM
 * creator：awesome
 */
public class DispatcherProccesor {

    private static Logger logger= Logger.getLogger(DispatcherProccesor.class.getName());

    /**
     * 向dispatcher发送认证请求
     */
    public  static void authenticate(ImCommunicationProto.CommonMessage commonMessage){
        DispatcherInstanceManager.getInstance().chooseDispatcherInstance().getChannel().writeAndFlush(commonMessage);
        logger.info("客户端发送认证请求到转发服务成功"+ commonMessage);

    }

    public  static void sendSimpleMessage(ImCommunicationProto.CommonMessage commonMessage){
        DispatcherInstanceManager.getInstance().chooseDispatcherInstance().getChannel().writeAndFlush(commonMessage);
        logger.info("客户端发送认证请求到转发服务成功"+ commonMessage);

    }

    public  static void processor(ImCommunicationProto.CommonMessage commonMessage){
        DispatcherInstanceManager.getInstance().chooseDispatcherInstance().getChannel().writeAndFlush(commonMessage);
        logger.info("客户端发送请求到转发服务成功"+ commonMessage);

    }

}
