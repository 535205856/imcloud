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
    Integer REQUESTTYPEAUTHREQUEST=1;
    /**
     * 用户认证返回
     */
    Integer REQUESTTYPEAUTHREPONSE=2;


    /**
     * 发送单聊消息
     */
    Integer REQUESTTYPESEND_MESSAGE_CHAT21=3;

    /**
     * 推送消息返回
     */
    Integer REPONSE_TYPE_MESSAGE_PUSH=4;


    /**
     * 发送群聊消息
     */
    Integer REQUESTTYPESEND_MESSAGE_GROUP=5;




}
