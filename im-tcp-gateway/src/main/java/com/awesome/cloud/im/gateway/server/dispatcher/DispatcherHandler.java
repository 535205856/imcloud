package com.awesome.cloud.im.gateway.server.dispatcher;

import cn.hutool.json.JSONUtil;
import com.awesome.cloud.common.base.CommonConstant;
import com.awesome.cloud.common.dto.UserAuthDTO;
import com.awesome.cloud.common.dto.UserAuthResDTO;
import com.awesome.cloud.im.gateway.server.session.ClientSdkSessionManager;
import com.awesome.im.cloud.proto.ImCommunicationProto;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Logger;

/**
 * projectName：im-gateway-tcp
 * className ：DispatcherHandler
 * class desc：TODO
 * createTime：2019/11/12 11:47 AM
 * creator：awesome
 * @author awesome
 */
public class DispatcherHandler extends SimpleChannelInboundHandler<ImCommunicationProto.CommonMessage> {

    private static Logger logger= Logger.getLogger(DispatcherProccesor.class.getName());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ImCommunicationProto.CommonMessage commonMessage) throws Exception {
        logger.info("收到分发系统发过来的消息"+commonMessage);


        //接收到dispather客户端发过来的信息
        ImCommunicationProto.CommonMessage.DataTye dateType = commonMessage.getDateType();
        switch (dateType){
            case ReponseType:
                ImCommunicationProto.CommonMessage.Reponse reponse = commonMessage.getReponse();
                if (CommonConstant.REQUESTTYPEAUTHREPONSE==reponse.getReponseType()){
                    String reponseStrBody = reponse.getStrBody();
                    UserAuthResDTO userAuthDTO = JSONUtil.toBean(reponseStrBody, UserAuthResDTO.class);
                    Channel clientSession = ClientSdkSessionManager.getIntance().getSession(userAuthDTO.getUid());
                    clientSession.writeAndFlush(commonMessage);
                    logger.info("返回sdk success ---");
                }

                if (CommonConstant.REPONSE_TYPESEND_MESSAGE_CHAT21==reponse.getReponseType()){
                    String reponseStrBody = reponse.getStrBody();
                    UserAuthResDTO userAuthDTO = JSONUtil.toBean(reponseStrBody, UserAuthResDTO.class);
                    Channel clientSession = ClientSdkSessionManager.getIntance().getSession(userAuthDTO.getUid());
                    clientSession.writeAndFlush(commonMessage);
                    logger.info("单聊消息的返回到client success ---");
                }


                break;
            case RequestType:


                break;

                default:

                    break;

        }


    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {


    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {



    }
}
