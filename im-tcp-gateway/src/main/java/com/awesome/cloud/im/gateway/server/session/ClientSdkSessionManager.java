package com.awesome.cloud.im.gateway.server.session;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * projectName：imcloud
 * className ：GateWaySessionManager
 * class desc：TODO
 * createTime：2019/12/14 10:21 PM
 * creator：awesome
 * @author awesome
 */
public class ClientSdkSessionManager {

    private Logger logger= Logger.getLogger(this.getClass().getName());


    private ConcurrentHashMap<String, Channel>  gateWaySession=new ConcurrentHashMap();



    private ClientSdkSessionManager(){

    }


    private  static  class Singleton{
        private  static final  ClientSdkSessionManager gateWaySessionManager=new ClientSdkSessionManager();

    }


    public static ClientSdkSessionManager getIntance(){
        return  ClientSdkSessionManager.Singleton.gateWaySessionManager;
    }




    public  void addSession(String uid,Channel channel){
        gateWaySession.put(uid,channel);
        logger.info("client session  add session success   uid "+uid+"  current size" +gateWaySession.size());
    }
    public  void removeSession(String uid){
        gateWaySession.remove(uid);
        logger.info("client session  remove success session  uid "+uid+"  current size" +gateWaySession.size());
    }
    public  Channel getSession(String uid){
        logger.info("client session get  success session  uid "+uid+"  current size" +gateWaySession.size());
        return  gateWaySession.get(uid);
    }




}
