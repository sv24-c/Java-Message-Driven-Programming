package nsqtwoconsumer;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;

/**
 * Created by smit on 8/2/22.
 */
public class ConsumerSecond {

    public static void main(String[] args) {

        NSQLookup nsqLookup = new DefaultNSQLookup();

        nsqLookup.addLookupAddress("localhost", 4161);

        NSQConsumer nsqConsumer = new NSQConsumer(nsqLookup,"TestTopic","dustin",(message) ->{

            System.out.println("received "+ new String(message.getMessage()));
            message.finished();
        });
        nsqConsumer.start();
    }
}
