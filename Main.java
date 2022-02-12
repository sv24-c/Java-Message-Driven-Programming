package chatgroup;

import com.github.brainlag.nsq.exceptions.NSQException;

import java.util.concurrent.TimeoutException;

/**
 * Created by smit on 11/2/22.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, NSQException, TimeoutException {

        Group group = new Group("Group1");

        Member member1 = new Member("smit", group);
        Member member2 = new Member("pavan", group);
        Member member3 = new Member("rahil", group);

        member1.write("I am Smit");
        member2.write("I am Pavan");
        member3.write("I am Rahil");

        System.out.println("Group 1");
        /*member1.read();
        member2.read();
        member3.read();
*/
         new Thread(()->{
            member1.read();
         }).start();
         new Thread(()->{
            member2.read();
        }).start();new Thread(()->{
            member3.read();
        }).start();


        //Thread.sleep(5000);
    }
}
