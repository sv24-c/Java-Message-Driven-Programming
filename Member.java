package chatgroup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.exceptions.NSQException;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
import nsq.Nsq;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

/**
 * Created by smit on 11/2/22.
 */
public class Member {

    Group group;
    String consumerName;
    NSQConsumer consumer;
    ObjectMapper mapper = new ObjectMapper();
    NSQLookup lookup = new DefaultNSQLookup();

    Member(String consumerName, Group group)
    {
        this.consumerName = consumerName;
        this.group = group;
        lookup.addLookupAddress("localhost", 4161);
    }

    void read()
    {
        consumer = new NSQConsumer(lookup, group.groupname, consumerName, (message) -> {

            String m = new String(message.getMessage());
            System.out.println(consumerName+"'s message: "+m);
            message.finished();
        });
        consumer.setExecutor(Executors.newSingleThreadExecutor());
        consumer.start();
    }

    void write(String message) throws NSQException, TimeoutException
    {
        group.producer.produce(group.groupname, message.getBytes());
    }
}
