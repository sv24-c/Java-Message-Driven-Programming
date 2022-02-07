package nsqconsumerproducer;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;

/**
 * Created by smit on 7/2/22.
 */
public class Consumer {

    public static void main(String[] args) {

        NSQLookup lookup = new DefaultNSQLookup();
        lookup.addLookupAddress("localhost", 4161);
        NSQConsumer nsqConsumer = new NSQConsumer(lookup, "speedtest", "dustin", (message)->{

            System.out.println("received: "+message);
            message.finished();
        });

        nsqConsumer.start();

    }
}
