package com.awesome.im.cloud.dispather.server.netty;

import com.awesome.im.cloud.proto.ImCommunicationProto;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * projectName：imcloud
 * className ：DispatherNettyServer
 * class desc：TODO
 * createTime：2019/12/13 6:11 PM
 * creator：awesome
 * @author jorden.li
 */
public class DispatherNettyServer {

    public static final String ZOOKEEPERSTRING = "127.0.0.1:2181";

    public void bind(int port) throws Exception {

        //bossGroup就是parentGroup，是负责处理TCP/IP连接的
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        ///workerGroup就是childGroup,是负责处理Channel(通道)的I/O事件
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        ServerBootstrap sb = new ServerBootstrap();
        sb.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                ///初始化服务端可连接队列,指定了队列的大小128
                .option(ChannelOption.SO_BACKLOG, 128)
                ///保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                /// 绑定客户端连接时候触发操作
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sh) throws Exception {
                        ChannelPipeline pipeline = sh.pipeline();
                        pipeline.addLast(new ProtobufVarint32FrameDecoder());
                        pipeline.addLast(new ProtobufDecoder(
                                ImCommunicationProto.CommonMessage.getDefaultInstance()

                        ));
                        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                        pipeline.addLast(new ProtobufEncoder());
                        ////使用ServerHandler类来处理接收到的消息
                        //使用ServerHandler类来处理接收到的消息
                        pipeline  .addLast(new GateWayHandler());
                    }
                });
        ///绑定监听端口，调用sync同步阻塞方法等待绑定操作完
        ChannelFuture future = sb.bind(port).sync();

        if (future.isSuccess()) {
            System.out.println("dispatcher 服务端启动成功");

//            //注册zookeeper
//            CuratorFramework curatorFramework  = ZookeeperClientCreater.createSimple(ZOOKEEPERSTRING);
//            curatorFramework.start();
//            ZookeeperUtils.create(curatorFramework,"/im-dispatcher/127.0.0.1:8002",
//                    "127.0.0.1:8002".getBytes());

        } else {
            System.out.println("服务端启动失败");
            future.cause().printStackTrace();
            bossGroup.shutdownGracefully(); //关闭线程组
            workerGroup.shutdownGracefully();
        }

        ///成功绑定到端口之后,给channel增加一个 管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程。
        future.channel().closeFuture().sync();



    }

}
