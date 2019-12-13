package com.awesome.cloud.im.client.sdk.base;

import com.awesome.im.cloud.proto.ImCommunicationProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * projectName：imcloud
 * className ：ImHandler
 * class desc：TODO
 * createTime：2019/12/9 11:54 AM
 * creator：awesome
 * @author awesome
 */
public class ImHandler extends SimpleChannelInboundHandler<ImCommunicationProto.CommonMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ImCommunicationProto.CommonMessage commonMessage)  {
        System.out.println("客户端 ：收到" +commonMessage.getReponse());

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx)  {
        System.out.println(  "和服务器失去链接---- id:" +ctx.channel().id().asShortText());
        //需要重新链接
       GatewaySessionManger.getInstance().remove(ctx.channel().id().asShortText());

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)  {
        GatewaySessionManger.getInstance().add(ctx.channel().id().asShortText(),ctx.channel());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)  {
        ctx.channel().closeFuture();
        cause.printStackTrace();
    }
}
