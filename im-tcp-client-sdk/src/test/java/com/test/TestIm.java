package com.test;

import com.awesome.cloud.im.client.sdk.base.ImClient;

/**
 * projectName：imcloud
 * className ：TestIm
 * class desc：TODO
 * createTime：2019/12/11 3:46 PM
 * creator：awesome
 */
public class TestIm {
    public static void main(String[] args) {
        ImClient imClient=new ImClient();
        imClient.connect("127.0.0.1",8001);
        imClient.auth();
//        for (int i = 0; i < 10000; i++) {
            imClient.sendSimpleMessage();

//        }



//        ImClient imClient1=new ImClient();
//        imClient1.connect("127.0.0.1",8001);
//        imClient1.auth();


//        ImClient imClient3=new ImClient();
//        imClient3.connect("127.0.0.1",8001);
//        imClient3.auth();

    }
}
