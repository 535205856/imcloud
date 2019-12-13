//package com.awesome.cloud.im.client.sdk;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.ChannelPipeline;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.protobuf.ProtobufDecoder;
//import io.netty.handler.codec.protobuf.ProtobufEncoder;
//import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
//import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
//import io.netty.handler.timeout.IdleStateHandler;
//
///**
// * projectName：imcloud
// * className ：ImClient
// * class desc：TODO
// * createTime：2019/12/9 11:42 AM
// * creator：awesome
// * @author awesome
// */
//public class ImClient {
//
//    private EventLoopGroup eventLoopGroup =null;
//
//    private   Bootstrap bootstrap =null;
//
//    private SocketChannel socketChannel =null;
//
//
//    public   void connect(String  host,String port){
//        eventLoopGroup=new NioEventLoopGroup();
//
//        bootstrap = new Bootstrap();
//        bootstrap.group(eventLoopGroup)
//                .channel(NioSocketChannel.class)
//                .option(ChannelOption.SO_KEEPALIVE, true)
//                .handler(new ChannelInitializer<NioSocketChannel>() {
//
//                    @Override
//                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        /*
//                         * ProtobufEncoder：用于对Probuf类型序列化。
//                         *
//                         * ProtobufVarint32LengthFieldPrepender：用于在序列化的字节数组前加上一个简单的包头，只包含序列化的字节长度。
//                         *
//                         * ProtobufVarint32FrameDecoder：用于decode前解决半包和粘包问题（利用包头中的包含数组长度来识别半包粘包）
//                         *
//                         * ProtobufDecoder：反序列化指定的Probuf字节数组为protobuf类型。
//                         */
//                        ChannelPipeline pipeline = ch.pipeline();
//                        pipeline.addLast(new ProtobufVarint32FrameDecoder());
//
//                        pipeline.addLast(new IdleStateHandler(
//                                0,0,5));
////                        pipeline.addLast(new ProtobufDecoder(com.awesome.im.proto.ImCommunicationProto.CommonMessage.getDefaultInstance()));
//
//                        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
//                        pipeline.addLast(new ProtobufEncoder());
//
//                        pipeline.addLast(new ImClientHandler(  messageProcessor));
//                    }
//                });
//
//    }
//}
