package newchatapp;

import com.github.brainlag.nsq.exceptions.NSQException;

import java.util.concurrent.TimeoutException;

/**
 * Created by smit on 12/2/22.
 */
public class MainChatApp {

    public static void main(String[] args) throws NSQException, TimeoutException {

       /* Scanner sc = new Scanner(System.in);
        String str;
        str = sc.nextLine();*/

        MemberChatApp memberChatApp = new MemberChatApp("smi");
        MemberChatApp memberChatApp1 = new MemberChatApp("pav");
        MemberChatApp memberChatApp2 = new MemberChatApp("smit");

        /*System.out.println("Enter three memberChatApp name");
        MemberChatApp memberChatApp = new MemberChatApp(str);
        MemberChatApp memberChatApp1 = new MemberChatApp(str);
        MemberChatApp memberChatApp2 = new MemberChatApp(str);*/



        memberChatApp.message("pav","Hi i am pavan");
        memberChatApp1.message("smi", "Hi i am smit");
        memberChatApp2.message("smit","hi i am smit vachhani");
/*
        System.out.println("Enter name of receiver and message three times");
        String str1;
        str1 = sc.nextLine();

        memberChatApp.message(str,str1);
        memberChatApp1.message(str, str1);
        memberChatApp2.message(str,str1);*/

        memberChatApp.read();
        memberChatApp1.read();
        memberChatApp2.read();
    }
}
