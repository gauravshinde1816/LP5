import java.net.*;
import java.util.Scanner;
import java.io.*;

public class server {

    public static void main(String[] args) {
        try {

            ServerSocket ss = new ServerSocket(5000);
            Socket s  = ss.accept();


            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            Scanner sc = new Scanner(System.in);

            String str = "" , str2 = "";

            while (!str.equals("stop")) {
                str = din.readUTF();

                System.out.println("Client says " + str);

                str2 = sc.nextLine();
                dout.writeUTF(str2);
                dout.flush();
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}