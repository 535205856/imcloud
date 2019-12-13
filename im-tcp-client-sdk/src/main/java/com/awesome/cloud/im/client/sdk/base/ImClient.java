package com.awesome.cloud.im.client.sdk.base;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.awesome.cloud.common.base.CommonConstant;
import com.awesome.cloud.common.base.CommonRequest;
import com.awesome.cloud.common.dto.UserAuthDTO;
import com.awesome.im.cloud.proto.ImCommunicationProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.Data;

import java.util.UUID;

/**
 * projectName：imcloud
 * className ：ImClient
 * createTime：2019/12/9 11:42 AM
 * creator：awesome
 * @author awesome
 */
public class ImClient {

    private EventLoopGroup eventLoopGroup =null;

    private   Bootstrap bootstrap =null;

    private SocketChannel socketChannel =null;



    public   void connect(String  host,int port){
        eventLoopGroup=new NioEventLoopGroup();

        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        /*
                         * ProtobufEncoder：用于对Probuf类型序列化。
                         *
                         * ProtobufVarint32LengthFieldPrepender：用于在序列化的字节数组前加上一个简单的包头，只包含序列化的字节长度。
                         *
                         * ProtobufVarint32FrameDecoder：用于decode前解决半包和粘包问题（利用包头中的包含数组长度来识别半包粘包）
                         *
                         * ProtobufDecoder：反序列化指定的Probuf字节数组为protobuf类型。
                         */
                        ChannelPipeline pipeline = ch.pipeline();
//
                        pipeline.addLast(new ProtobufVarint32FrameDecoder());
                        pipeline.addLast(new ProtobufDecoder(
                                ImCommunicationProto.CommonMessage.getDefaultInstance()

                        ));
                        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                        pipeline.addLast(new ProtobufEncoder());
                        pipeline.addLast(new ImHandler());
                    }
                });


        ChannelFuture connect = bootstrap.connect(host, port);
        connect.addListener((ChannelFutureListener) future -> {

            if (future.isSuccess()) {
                socketChannel= (SocketChannel) future.channel();
                System.out.println("跟TCP接入系统完成长连接的建立");
//                socketChannel.writeAndFlush("1111我是");

            } else {
                future.channel().close();
                eventLoopGroup.shutdownGracefully();
                future.cause().printStackTrace();
            }

        } );
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        connect.sync();


    }

    public  void auth(){
//        System.out.println(1);
        UserAuthDTO userAuthDTO =new UserAuthDTO ();
        userAuthDTO.setTimestamp(System.currentTimeMillis());
        userAuthDTO.setUid("11122L");
        userAuthDTO.setToken(IdUtil.randomUUID());
        String toJsonStr = JSONUtil.toJsonStr(userAuthDTO);

        ImCommunicationProto.CommonMessage commonMessage = CommonRequest.buildRequest("1.0", toJsonStr.getBytes(), System.currentTimeMillis(), "", CommonConstant.REQUESTTYPEAUTHREQUEST);
        GatewaySessionManger.getInstance().getAvailableChannel().writeAndFlush(commonMessage);
    }






}
