import com.awesome.cloud.im.gateway.server.zookeeper.ZookeeperClientCreater;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

import static com.awesome.cloud.im.gateway.server.netty.ClientServer.ZOOKEEPERSTRING;

/**
 * projectName：imcloud
 * className ：TestWatch
 * class desc：TODO
 * createTime：2019/12/13 12:23 PM
 * creator：awesome
 */
public class TestWatch {
    public static void main(String[] args) {


        CuratorFramework curatorFramework  = ZookeeperClientCreater.createSimple(ZOOKEEPERSTRING);
        curatorFramework.start();
        PathChildrenCache cache = new PathChildrenCache(curatorFramework, "/im-tcp-gateway", false);
        try {
            cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            cache.getListenable().addListener(new PathChildrenCacheListener() {

               public   void childEvent(CuratorFramework var1, PathChildrenCacheEvent event) throws Exception{
                   switch (event.getType()) {
                       case CHILD_ADDED:
                           System.out.println("CHILD_ADDED :" + event.getData().getPath());
                           System.out.println("add data : " + new String(event.getData().getData()));
                           break;
                       case CHILD_UPDATED:
                           System.out.println("CHILD_UPDATED :" + event.getData().getPath());
                           System.out.println("update data : " + new String(event.getData().getData()));
                           break;
                       case CHILD_REMOVED:
                           System.out.println("CHILD_REMOVED :" + event.getData().getPath());
                           System.out.println("remove data : " + new String(event.getData().getData()));
                           break;
                       default:
                           break;
                   }

               }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
