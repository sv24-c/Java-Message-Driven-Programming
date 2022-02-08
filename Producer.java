package nsqtwoconsumer;

import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.exceptions.NSQException;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;

import java.util.concurrent.TimeoutException;

/**
 * Created by smit on 8/2/22.
 */
public class Producer {

    public static void main(String[] args) throws NSQException, TimeoutException {

        NSQProducer nsqProducer = new NSQProducer().addAddress("localhost", 4150).start();

        nsqProducer.produce("TestTopic",("This message from producer to consumers").getBytes());

    }
}
