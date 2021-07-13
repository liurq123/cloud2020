package com.dkf.springcloud.Controller;

import com.dkf.springcloud.Send.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {
    @Resource   // spring cloud stream 将接口自动注入容器
    private IMessageProvider messageProvider;


    @GetMapping(value = "/sendMessage")
    public String sendMessage(){
        return messageProvider.send();
    }

}
