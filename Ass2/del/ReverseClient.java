import ReverseModule.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

import java.util.Scanner;


public class ReverseClient {
    public static void main(String[] args) {
        try {

            ORB orb = ORB.init(args , null);

            NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
    
            Reverse impl = ReverseHelper.narrow(ncRef.resolve_str("Reverse"));
    
            System.out.println("Enter String");
    
            Scanner sc = new Scanner(System.in);
    
            String str = sc.nextLine();
    
            String res = impl.reverse_string(str);
    
            System.out.println(res);
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
       
    }

}
