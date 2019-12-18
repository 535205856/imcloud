package com.awesome.im.cloud.dispather.server.netty;

import cn.hutool.json.JSONUtil;
import com.awesome.cloud.common.RabbitMQConstant;
import com.awesome.cloud.common.base.CommonConstant;
import com.awesome.cloud.common.base.CommonReponse;
import com.awesome.cloud.common.dto.UserAuthDTO;
import com.awesome.cloud.common.dto.UserAuthResDTO;
import com.awesome.im.cloud.dispather.server.utils.SpringContextHolder;
import com.awesome.im.cloud.proto.ImCommunicationProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.logging.Logger;

import static com.awesome.cloud.common.base.CommonConstant.REQUESTTYPEAUTHREQUEST;
import static com.awesome.cloud.common.base.CommonConstant.REQUESTTYPESEND_MESSAGE_CHAT21;

/**
 * projectName：imcloud
 * className ：GateWayHandler
 * class desc：TODO
 * createTime：2019/12/14 9:46 PM
 * creator：awesome
 *
 * @author jorden.li
 */
public class GateWayHandler extends SimpleChannelInboundHandler<ImCommunicationProto.CommonMessage> {


    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                ImCommunicationProto.CommonMessage commonMessage) throws Exception {


        //接受服务认证请求

        ImCommunicationProto.CommonMessage.Request request = commonMessage.getRequest();

        logger.info("channelRead0  ：" + commonMessage);

        String strBody = request.getStrBody();
        switch (request.getRequesType()) {
            case REQUESTTYPEAUTHREQUEST:
                logger.info("收到授权请求：" + request);

                UserAuthDTO userAuthDTO = JSONUtil.toBean(strBody, UserAuthDTO.class);
                if (userAuthDTO.getUid().length() >= 4) {

                    UserAuthResDTO userAuthResDTO = new UserAuthResDTO();
                    userAuthResDTO.setUid(userAuthDTO.getUid());
                    userAuthResDTO.setTimestamp(System.currentTimeMillis());
                    ImCommunicationProto.CommonMessage commonMessage1 = CommonReponse.buildReponse(
                            200, JSONUtil.toJsonStr(userAuthResDTO), System.currentTimeMillis(),
                            CommonConstant.REQUESTTYPEAUTHREPONSE);
                    GateWaySessionManager.getIntance().addSession(userAuthDTO.getUid(), channelHandlerContext.channel());
                    channelHandlerContext.channel().writeAndFlush(commonMessage1);
                    break;


                } else {
                    UserAuthResDTO userAuthResDTO = new UserAuthResDTO();
                    ImCommunicationProto.CommonMessage commonMessage1 = CommonReponse.buildReponse(
                            201, JSONUtil.toJsonStr(userAuthResDTO), System.currentTimeMillis(),
                            CommonConstant.REQUESTTYPEAUTHREPONSE);
                    channelHandlerContext.channel().writeAndFlush(commonMessage1);

                }

                break;


            case REQUESTTYPESEND_MESSAGE_CHAT21:
                    logger.info("im-dispatcher   收到单聊消息 -----"+strBody);

                RabbitTemplate rabbitTemplate = SpringContextHolder.getBean(RabbitTemplate.class);
                rabbitTemplate.convertAndSend(RabbitMQConstant.PROGRAMMATICALLY_EXCHANGE,RabbitMQConstant.IM_SIMPLE_MESSAGE_SEND_KEY,strBody);
                logger.info("---消息发送成功---"+strBody);

                break;

            default:
        }


    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("dispather channelActive ");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().closeFuture();
        cause.printStackTrace();

    }
}
