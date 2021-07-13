package com.dkf.springcloud.Send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class)   // 定义消息推送通道
public class IMessageImpl implements IMessageProvider {

//    @Qualifier("output")
//    @Autowired
//    private MessageChannel messageChannel;  // 消息发送管道

    @Resource
    private MessageChannel output;  // 消息发送管道

//    private MessageChannel output;
//
//    @Autowired
//    public IMessageImpl(MessageChannel output) {
//        this.output = output;
//    }


    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());  // 包装消息，发送
        System.out.println("**********serial:"+serial);
        return null;
    }
}
