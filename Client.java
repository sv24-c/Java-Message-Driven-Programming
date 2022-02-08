package SSL;


import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;

/**
 * Created by smit on 7/2/22.
 */
public class Client {

    public static void main(String[] args) {

        try {
            //System.out.println("javax.net.ssl.trustStore", );

            System.setProperty("javax.net.ssl.trustStore", "C:/Users/Martin/sample.pfx");
            System.setProperty("javax.net.ssl.trustStorePassword", "sample");

            SSLSocketFactory sslf = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket ssls = (SSLSocket) sslf.createSocket("127.0.0.1", 4444);

            SSLParameters parameters = ssls.getSSLParameters();
            ssls.setSSLParameters(parameters);

            PrintWriter pw = new PrintWriter(ssls.getOutputStream(), true);
            pw.write("Hi Server,, this is from Client");

            BufferedReader reader = new BufferedReader(new InputStreamReader(ssls.getInputStream()));

            String str = reader.readLine();
            System.out.println(str);
            System.out.println("Protocol use of : "+ssls.getApplicationProtocol());

            pw.close();
            reader.close();
            ssls.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
