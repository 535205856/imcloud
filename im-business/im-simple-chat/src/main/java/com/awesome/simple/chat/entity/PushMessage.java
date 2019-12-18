package com.awesome.simple.chat.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 消息发送表
 * </p>
 *
 * @author awesome.li
 * @since 2019-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PushMessage implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
     * 接受用户ID
     */
    private String receiveUserId;

    /**
     * 发送用户ID
     */
    private String sendUserId;

    /**
     * 群组ID
     */
    private Integer groupId;

    /**
     * 序列号
     */
    private byte[] seq;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 是否传达(0 未传达 1已经传达)
     */
    private Integer delivered;


}
