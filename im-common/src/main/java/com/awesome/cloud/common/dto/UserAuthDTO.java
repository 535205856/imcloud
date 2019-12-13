package com.awesome.cloud.common.dto;

import lombok.Data;

/**
 * projectName：imcloud
 * className ：UserAuthDTO
 * class desc：TODO
 * createTime：2019/12/12 5:12 PM
 * creator：awesome
 */
@Data
public class UserAuthDTO {

    private String uid;

    private String token;

    private Long timestamp;
}
