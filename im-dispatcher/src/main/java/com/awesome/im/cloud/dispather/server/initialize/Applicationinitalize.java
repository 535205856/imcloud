package com.awesome.im.cloud.dispather.server.initialize;

import com.awesome.im.cloud.dispather.server.netty.DispatherNettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
public class Applicationinitalize  implements ApplicationRunner {

//    @Value("${im.exchange}")
//    private String imExchange;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        DispatherNettyServer dispatherNettyServer=new DispatherNettyServer();
        try {
            dispatherNettyServer.bind(8002);
            log.info("im-dispatcher -服务初始化成功～ bind port 8002 ");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
