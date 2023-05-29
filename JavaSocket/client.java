import java.net.*;
import java.util.Scanner;
import java.io.*;

public class client {
    public static void main(String[] args) {
        try {
            Socket ss = new Socket("localhost", 5000);

            DataInputStream din = new DataInputStream(ss.getInputStream());
            DataOutputStream dout = new DataOutputStream(ss.getOutputStream());

            Scanner s = new Scanner(System.in);

            String str = "" , str2 = "";

            while(!str.equals("stop")){
                str2 = s.nextLine();
                dout.writeUTF(str2);
                dout.flush();

                str = din.readUTF();
                System.out.println("Server says " + str);
            }


            s.close();
            ss.close();

        } catch (Exception e) {
            System.out.println(e);
        }
       
    }
}