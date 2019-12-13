package com.awesome.cloud.im.gateway.server;

/**
 * projectName：imcloud
 * className ：ServerHandler
 * class desc：TODO
 * createTime：2019/12/11 4:10 PM
 * creator：awesome
 * @author: jorden.li
 */

import com.awesome.cloud.common.base.CommonConstant;
import com.awesome.cloud.common.base.CommonReponse;
import com.awesome.im.cloud.proto.ImCommunicationProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<ImCommunicationProto.CommonMessage> {

    /**
     * 接受client发送的消息
     * @param ctx
     * @param commonMessage
     * @throws Exception
     */

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,ImCommunicationProto.CommonMessage commonMessage) throws Exception {

        //返回的数据结构
        System.out.println( "接收到ID: "+ctx.channel().id().asShortText()+" 发来的数据：msg ：" +commonMessage.getRequest());

        ImCommunicationProto.CommonMessage commonMessage1 = CommonReponse.buildReponse(200, "ok".getBytes(), System.currentTimeMillis(), CommonConstant.REQUESTTYPEAUTHREPONSE);
        ctx.channel().writeAndFlush(commonMessage1);
    }

    /**
     * 通知处理器最后的channelRead()是当前批处理中的最后一条消息时调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端接收数据完毕..");
        ctx.flush();
    }

    /**
     * 读操作时捕获到异常时调用
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    /**
     * 客户端去和服务端连接成功时触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("add  channel : "+ ctx.channel().id().asLongText());
        ClientInstanceInfo clientInstanceInfo=new ClientInstanceInfo();
        clientInstanceInfo.setChannleId(ClientIdGenUtils.getId(ctx.channel()));
        clientInstanceInfo.setChannel(ctx.channel());
        clientInstanceInfo.setTimestamp(System.currentTimeMillis());
        clientInstanceInfo.setIsAuth(0);
        ClientSessionMangager.getInstance().add(ClientIdGenUtils.getId(ctx.channel()),
                clientInstanceInfo);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(" remove  channel : "+ ctx.channel().id().asLongText());
        ClientSessionMangager.getInstance().remove(ClientIdGenUtils.getId(ctx.channel()));
    }
}