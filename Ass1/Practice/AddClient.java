import java.rmi.*;

public class AddClient {
    public static void main(String[] args) {
        try {
           String addUrl = "rmi://" + args[0] + "/AddServer";
           AddServerInterface intf  = (AddServerInterface) Naming.lookup(addUrl);
           System.out.println("First Number : "  );
           double d1 = (double) Double.valueOf(args[1]).doubleValue();

           System.out.println("Second Number : " );
           double d2 = (double) Double.valueOf(args[2]).doubleValue();

           double sum = intf.add(d1, d2);
           System.out.println("Sum of two numbers "  + sum );

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
