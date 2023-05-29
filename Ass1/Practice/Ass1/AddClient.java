import java.rmi.Naming;

public class AddClient {
    public static void main(String[] args) {

        try {
            String addServerURL = "rmi://" + args[0] + "/AddServer";
            AddServerInterface intf = (AddServerInterface) Naming.lookup(addServerURL);

            double d1 = Double.valueOf(args[1]).doubleValue();
            double d2 = Double.valueOf(args[2]).doubleValue();
            System.out.println("First No : " + d1);
            System.out.println("Second No : " + d2);

            double sum = intf.add(d1, d2);
            System.out.println("Sum : " + sum);

        } catch (Exception e) {
            System.out.println("Exception " + e);
        }

    }
}
