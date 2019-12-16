package com.awesome.im.cloud.dispather.server.netty;

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
public class GateWaySessionManager {

    private Logger logger= Logger.getLogger(this.getClass().getName());


    private ConcurrentHashMap<String, Channel>  gateWaySession=new ConcurrentHashMap();



    private  GateWaySessionManager(){

    }


    private  static  class Singleton{
        private  static final  GateWaySessionManager gateWaySessionManager=new GateWaySessionManager();

    }


    public static GateWaySessionManager getIntance(){
        return  GateWaySessionManager.Singleton.gateWaySessionManager;
    }




    public  void addSession(String uid,Channel channel){
        gateWaySession.put(uid,channel);
        logger.info("im-dispatcher add session success   uid "+uid+"  current size" +gateWaySession.size());
    }
    public  void removeSession(String uid){
        gateWaySession.remove(uid);
        logger.info("im-dispatcher remove success session  uid "+uid+"  current size" +gateWaySession.size());
    }





}
