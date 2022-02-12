package newchatapp;

import com.github.brainlag.nsq.NSQConfig;
import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.exceptions.NSQException;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;

import java.util.concurrent.TimeoutException;

/**
 * Created by smit on 12/2/22.
 */
public class MemberChatApp {

    String memberName;
    NSQProducer producer;
    NSQConsumer consumer;
    NSQLookup lookup = new DefaultNSQLookup();

    MemberChatApp(String memberName)
    {
        this.memberName=memberName;
        lookup.addLookupAddress("localhost",4161);
        producer = new NSQProducer();
        producer.setConfig(getDeflateConfig());
        producer.addAddress("localhost",4150);
        producer.start();
    }

    void message(String receiver, String message) throws NSQException, TimeoutException {
        message = memberName + ": "+message;
        producer.produce(receiver,message.getBytes());
    }

    void read()
    {
        consumer = new NSQConsumer(lookup,memberName, "MainChatApp", (message) ->{

            String s = new String(message.getMessage());
            System.out.println(memberName+"'s message: "+s);
            message.finished();
        });

        consumer.start();
    }

    private static NSQConfig getDeflateConfig() {
        final NSQConfig config = new NSQConfig();
        config.setCompression(NSQConfig.Compression.DEFLATE);
        config.setDeflateLevel(5);
        return config;
    }
}
