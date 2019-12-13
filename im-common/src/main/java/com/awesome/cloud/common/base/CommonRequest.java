package com.awesome.cloud.common.base;


import com.awesome.im.cloud.proto.ImCommunicationProto;
import com.google.protobuf.ByteString;

import java.io.IOException;
import java.io.InputStream;

/**
 * projectName：im-common
 * className ：CommonRequest
 * class desc：TODO
 * createTime：2019/11/7 8:15 PM
 * creator：awesome
 * @author awesome
 */
public class CommonRequest {



    /**
     * 构建request
     * @param clientVersion 客户端版本
     * @param body  消息内容
     * @param timestmp 时间
     * @param messgae 消息
     * @return
     */
    public static  ImCommunicationProto.CommonMessage buildRequest(String clientVersion,
                                                                   String body,
                                                                   Long timestmp,String messgae,Integer requestType
    ){


        ImCommunicationProto.CommonMessage.Request build = ImCommunicationProto.CommonMessage.Request.newBuilder().setClientVersion
                (clientVersion).setStrBody(body).setTimestamp(timestmp).setRequesType(requestType).build();

        return ImCommunicationProto.CommonMessage.newBuilder().
                setDateType(ImCommunicationProto.CommonMessage.DataTye.RequestType).setRequest(build).build();

    }

    /**
     * 构建request
     * @param clientVersion 客户端版本
     * @param body  消息内容
     * @param timestmp 时间
     * @param messgae 消息
     * @return
     */
    public static ImCommunicationProto.CommonMessage buildRequest(String clientVersion,
                                                                   byte[] body,
                                                                          Long timestmp,String messgae,Integer requestType
    ){
        ImCommunicationProto.CommonMessage.Request build = ImCommunicationProto.CommonMessage.Request.newBuilder().setClientVersion
                (clientVersion)
                .setStreamBody(ByteString.copyFrom(body)).setRequesType(requestType).
                        setTimestamp(timestmp).build();


        return ImCommunicationProto.CommonMessage.newBuilder().
                setDateType(ImCommunicationProto.CommonMessage.DataTye.RequestType).setRequest(build).build();


    }


    /**
     * 构建request
     * @param clientVersion 客户端版本
     * @param inputStream  消息内容
     * @param timestmp 时间
     * @param messgae 消息
     * @return
     */
    public  static ImCommunicationProto.CommonMessage buildRequest(String clientVersion,
                                                                   InputStream inputStream,
                                                                           Long timestmp,String messgae,Integer requestType
    ) throws IOException {
        ImCommunicationProto.CommonMessage.Request build = ImCommunicationProto.CommonMessage.Request.newBuilder().setClientVersion
                (clientVersion).setStrBody(null)
                .setStreamBody(ByteString.readFrom(inputStream)).setRequesType(requestType).
                        setTimestamp(timestmp).build();


        return ImCommunicationProto.CommonMessage.newBuilder().
                setDateType(ImCommunicationProto.CommonMessage.DataTye.RequestType).setRequest(build).build();
    }




}
