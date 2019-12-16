/**
 * projectName：imcloud
 * className ：VolatileDemo
 * class desc：TODO
 * createTime：2019/12/14 10:42 AM
 * creator：awesome
 */
public class VolatileDemo {

    private  volatile    static  int  i=0;


    public static void main(String[] args) {



        new Thread(() -> {

            int tempi=i;
            while (true){
                if (tempi!=i){
                    System.out.println("变量改变了值 i=" + i);
                }

            }


        }).start();


        new Thread(()->{

            for (int i1 = 0; i1 < 10; i1++) {
                i=i1;
                System.out.println("变量值 i=" + i);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

}
