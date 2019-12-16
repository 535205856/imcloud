package com.awesome.cloud.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class SimpleMessageRequest {

    private  String receiveUserId;

    private  String sendUserId;

    private  String content;

    private  Long timestamp;

}
