package com.awesome.cloud.im.gateway.server.netty;

/**
 * projectName：imcloud
 * className ：ServerHandler
 * class desc：TODO
 * createTime：2019/12/11 4:10 PM
 * creator：awesome
 * @author: jorden.li
 */

import cn.hutool.json.JSONUtil;
import com.awesome.cloud.common.dto.UserAuthDTO;
import com.awesome.cloud.im.gateway.server.dispatcher.DispatcherProccesor;
import com.awesome.cloud.im.gateway.server.session.ClientSdkSessionManager;
import com.awesome.im.cloud.proto.ImCommunicationProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Logger;

import static com.awesome.cloud.common.base.CommonConstant.REQUESTTYPEAUTHREQUEST;
import static com.awesome.cloud.common.base.CommonConstant.REQUESTTYPESEND_MESSAGE_CHAT21;

public class ServerHandler extends SimpleChannelInboundHandler<ImCommunicationProto.CommonMessage> {

    private static Logger logger= Logger.getLogger(DispatcherProccesor.class.getName());

    /**
     * 接受client发送的消息
     * @param ctx
     * @param commonMessage
     * @throws Exception
     */

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,ImCommunicationProto.CommonMessage commonMessage) throws Exception {

        //返回的数据结构
        logger.info( "接收到ID: "+ctx.channel().id().asShortText()+" 发来的数据：msg ：" +commonMessage.getRequest());

        String strBody = commonMessage.getRequest().getStrBody();

        switch (commonMessage.getDateType()) {
            case RequestType:

                switch (commonMessage.getRequest().getRequesType()) {

                    case REQUESTTYPEAUTHREQUEST:
                        //发送认证请求
                        UserAuthDTO userAuthDTO = JSONUtil.toBean(strBody, UserAuthDTO.class);
                        ClientSdkSessionManager.getIntance().addSession(userAuthDTO.getUid(),ctx.channel());
                             DispatcherProccesor.authenticate(commonMessage);
                        break;
                    case REQUESTTYPESEND_MESSAGE_CHAT21:
                         DispatcherProccesor.processor(commonMessage);
                        break;

                        default:

                            break;
                }

                break;

            case ReponseType:

                break;


                default:

                    break;
        }





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