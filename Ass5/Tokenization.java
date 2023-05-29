import java.util.*;

public class Tokenization {

    public static void main(String[] args) {

        int ch = 1;
        // no of nodes

        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the no of Nodes: ");
            int nodes = sc.nextInt();

            int token = 0;

            // enter : sender , receiver , data
            System.out.println("Enter the sender :");
            int sender = sc.nextInt();

            System.out.println("Enter the Receiver :");
            int receiver = sc.nextInt();

            System.out.println("Enter the data :");
            int data = sc.nextInt();

            // pass token
            int j = token;
            for (j = token; (j % nodes) != sender; j = (j + 1) % nodes) {
                System.out.print(" token forwarded to " + j + "-> ");
            }
            System.out.println(" token is at node " + j );

            // pass data
            System.out.println("Sender  " + sender  + " sending data " +  data + " to " + receiver);
            
            for (int i = sender + 1; i != receiver; i = (i + 1) % nodes) {
                System.out.println("Data is forwared to -> " + i);
            }

            // print res
            System.out.println("Receiver  " + receiver + " Received the data " + data);


            System.out.println("Do you want to continue playing press 1 else 0");
            ch = sc.nextInt();

        } while (ch == 1);

    }

}
