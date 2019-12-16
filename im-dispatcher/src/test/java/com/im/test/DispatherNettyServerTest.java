package com.im.test;

import com.awesome.im.cloud.dispather.server.netty.DispatherNettyServer;

/**
 * projectName：imcloud
 * className ：DispatherNettyServerTest
 * class desc：TODO
 * createTime：2019/12/14 9:50 PM
 * creator：awesome
 */
public class DispatherNettyServerTest {

    public static void main(String[] args) {

        DispatherNettyServer dispatherNettyServer=new DispatherNettyServer();
        try {
            dispatherNettyServer.bind(8002);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
