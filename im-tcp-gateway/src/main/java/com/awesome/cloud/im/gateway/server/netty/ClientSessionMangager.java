package com.awesome.cloud.im.gateway.server.netty;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * projectName：imcloud
 * className ：ClientSessionMangager
 * class desc：TODO
 * createTime：2019/12/12 10:18 PM
 * creator：awesome
 * @author awesome
 */
public class ClientSessionMangager {

    private Logger logger= Logger.getLogger(this.getClass().getName());

    private      ConcurrentHashMap<String, ClientInstanceInfo > clientSessionManager;

    private   ClientSessionMangager(){
        clientSessionManager=
                new ConcurrentHashMap<>();

    }

   private   static  class SingletonHandler{
       private  static  ClientSessionMangager clientSessionMangager=new ClientSessionMangager();

    }

    public  void add(String channleId, ClientInstanceInfo clientInstanceInfo ){
        clientSessionManager.put(channleId, clientInstanceInfo);
        logger.info("add success "+ channleId + " current size "+clientSessionManager.size());

    }


    public  void remove(String channleId ){
        clientSessionManager.remove(channleId);
        logger.info("remove  success "+ channleId + " current size "+clientSessionManager.size());
    }

    public static ClientSessionMangager getInstance(){
        return SingletonHandler.clientSessionMangager;
    }


}
