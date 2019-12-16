package com.awesome.cloud.common.base;

/**
 * projectName：im-common
 * className ：CommonConstant
 * class desc：TODO
 * createTime：2019/11/12 11:25 AM
 * creator：awesome
 * @author awesome
 *
 */
public interface CommonConstant {

    /**
     * 用户认证请求
     */
    int REQUESTTYPEAUTHREQUEST=1;
    /**
     * 用户认证返回
     */
    int REQUESTTYPEAUTHREPONSE=2;


    /**
     * 发送单聊消息 请求
     */
    int REQUESTTYPESEND_MESSAGE_CHAT21=3;

    /**
     * 发送单聊消息 返回
     */
    int REPONSE_TYPESEND_MESSAGE_CHAT21=6;

    /**
     * 推送消息返回
     */
    int REPONSE_TYPE_MESSAGE_PUSH=4;


    /**
     * 发送群聊消息
     */
    int REQUESTTYPESEND_MESSAGE_GROUP=5;




}
