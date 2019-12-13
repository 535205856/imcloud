package com.awesome.cloud.common.base;

import com.awesome.im.cloud.proto.ImCommunicationProto;
import com.google.protobuf.ByteString;

import java.io.IOException;
import java.io.InputStream;

/**
 * projectName：im-common
 * className ：CommonReponse
 * class desc：TODO
 * createTime：2019/11/7 8:30 PM
 * creator：awesome
 *
 * @author awesome
 */
public class CommonReponse {

    /**
     * 构建相应
     *
     * @param code      code
     * @param body      内容
     * @param timestamp 时间
     * @return
     */
    public static ImCommunicationProto.CommonMessage buildReponse(
            int code, String body, Long timestamp,Integer reponseType
    ) {
        ImCommunicationProto.CommonMessage.Reponse build = ImCommunicationProto.CommonMessage.Reponse.newBuilder().setCode(code).setStrBody(body)
                .setTimestamp(timestamp).setReponseType(reponseType).build();

        return ImCommunicationProto.CommonMessage.newBuilder().
                setDateType(ImCommunicationProto.CommonMessage.DataTye.ReponseType).setReponse(build).build();
    }


    /**
     * 构建相应
     *
     * @param code      code
     * @param body      内容
     * @param timestamp 时间
     * @return
     */
    public static ImCommunicationProto.CommonMessage buildReponse(
            int code, byte[] body, Long timestamp,Integer reponseType
    ) {
        ImCommunicationProto.CommonMessage.Reponse build = ImCommunicationProto.CommonMessage.Reponse.newBuilder().setCode(code).setStrBodyBytes(ByteString.copyFrom(body)).setReponseType(reponseType).setTimestamp(timestamp).build();

        return ImCommunicationProto.CommonMessage.newBuilder().
                setDateType(ImCommunicationProto.CommonMessage.DataTye.ReponseType).setReponse(build).build();
    }


    /**
     * 构建相应
     *
     * @param code        code
     * @param inputStream 内容
     * @param timestamp   时间
     * @return
     */
    public static ImCommunicationProto.CommonMessage buildReponse(
            int code, InputStream inputStream, Long timestamp,Integer reponseType
    ) throws IOException {
        ImCommunicationProto.CommonMessage.Reponse build = ImCommunicationProto.CommonMessage.Reponse.newBuilder().setReponseType(reponseType).setCode(code).setStrBodyBytes(ByteString.readFrom(inputStream)).setTimestamp(timestamp).build();

        return ImCommunicationProto.CommonMessage.newBuilder().
                setDateType(ImCommunicationProto.CommonMessage.DataTye.ReponseType).setReponse(build).build();
    }


}
