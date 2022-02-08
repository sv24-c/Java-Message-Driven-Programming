package nsq;

import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.exceptions.NSQException;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertTrue;

/**
 * Created by smit on 7/2/22.
 */
public class NSQConsumer {

    private static final long NSQ_MSG_TIMEOUT = 5000;

    public NSQConsumer(NSQLookup nsqLookup, String testTopic, String s, Object o) {

    }

    /*   @Test
       public void testLongRunningConsumer() throws NSQException, TimeoutException, InterruptedException {
           AtomicInteger counter = new AtomicInteger(0);
           NSQLookup lookup = new DefaultNSQLookup();
           lookup.addLookupAddress(Nsq.getNsqLookupdHost(), 4161);

           NSQConsumer consumer = new NSQConsumer(lookup, "test1", "testconsumer", (message) -> {
               LogManager.getLogger(this).info("Processing message: " + new String(message.getMessage()));
               counter.incrementAndGet();

               long sleepTime = NSQ_MSG_TIMEOUT / 5;
               for (int i = 0; i < 5; i++) {
                   try {
                       Thread.sleep(sleepTime + 500);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   message.touch();
               }
               message.finished();
               assertTrue(message.getAttempts() == 1);
           });
           consumer.start();

           NSQProducer producer = new NSQProducer();
           producer.addAddress(Nsq.getNsqdHost(), 4150);
           producer.start();
           String msg = "test-one-message";
           producer.produce("test1", msg.getBytes());
           producer.shutdown();

           Thread.sleep(NSQ_MSG_TIMEOUT * 2);
           assertTrue(counter.get() == 1);
           consumer.shutdown();
       }
   */
    private void shutdown() {
    }

    private void start() {
    }

}
