package com.awesome.cloud.im.gateway.server.initialize;

import com.awesome.cloud.im.gateway.server.netty.ClientServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * projectName：imcloud
 * className ：Applicationinitalize
 * class desc：TODO
 * createTime：2019/12/15 10:24 PM
 * creator：awesome
 */
@Component
@Slf4j
public class Applicationinitalize implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {

        ClientServer clientServer=new ClientServer();
        try {
            clientServer.bind(8001);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
