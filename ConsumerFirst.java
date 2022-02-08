package nsqtwoconsumer;

import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
import  com.github.brainlag.nsq.*;

/**
 * Created by smit on 8/2/22.
 */
public class ConsumerFirst {

    public static void main(String[] args) {

        NSQLookup nsqLookup = new DefaultNSQLookup();

        nsqLookup.addLookupAddress("localhost", 4161);

         NSQConsumer nsqConsumer = new NSQConsumer(nsqLookup, "TestTopic", "dustin", (message)->{

            System.out.println("received: "+new String(message.getMessage()));
            message.finished();
        });

         nsqConsumer.start();
    }
}
