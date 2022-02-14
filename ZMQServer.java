package jeromq;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * Created by smit on 14/2/22.
 */
public class ZMQServer {

    public static void main(String[] args) {

        try(ZContext context = new ZContext())
        {
            //socket that talk with client,, here we make
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            socket.bind("tcp://*:5555");

            while (!Thread.currentThread().isInterrupted())
            {
                byte[] reply = socket.recv(0);
                System.out.println("Received "+":["+new String(reply,ZMQ.CHARSET)+"]");

                String response = "hello";
                socket.send(response.getBytes(ZMQ.CHARSET),0);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
