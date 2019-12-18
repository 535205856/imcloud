package com.awesome.cloud.common.dto;

import lombok.*;

/**
 * projectName：imcloud
 * className ：SimpleMessageRequest
 * class desc：TODO
 * createTime：2019/12/15 9:20 PM
 * creator：awesome
 * @author awesome
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SimplePushMessage {

    private  String receiveUserId;

    private  String sendUserId;

    private  String content;

    private  Long timestamp;

    private  Long messageId;

    private  String seq;

}
