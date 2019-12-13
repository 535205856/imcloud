import com.awesome.cloud.im.gateway.server.ClientServer;

/**
 * projectName：imcloud
 * className ：TestCientServer
 * class desc：TODO
 * createTime：2019/12/12 3:20 PM
 * creator：awesome
 */
public class TestCientServer {
    public static void main(String[] args) {


        ClientServer clientServer=new ClientServer();
        try {
            clientServer.bind(8001);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
