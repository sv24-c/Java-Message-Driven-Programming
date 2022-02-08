package nsq;

/**
 * Created by smit on 7/2/22.
 */
public class Nsq {

    public static String getNsqdHost() {
        String hostName = System.getenv("NSQD_HOST");
        if (hostName == null) {
            hostName = "localhost";
        }
        return hostName;
    }

    public static String getNsqLookupdHost() {
        String hostName = System.getenv("NSQLOOKUPD_HOST");
        if (hostName == null) {
            hostName = "localhost";
        }
        return hostName;
    }
}
