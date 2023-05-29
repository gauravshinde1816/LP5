import ReverseModule.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.PortableServer.*;

public class ReverseServer {
    public static void main(String[] args) {

            try {
            ORB orb = ORB.init(args , null);

            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();


            System.out.println("Step1");
            Reverse h_ref = ReverseHelper.narrow(
                rootPOA.servant_to_reference(new ReverseImpl())
            );

            System.out.println("Step2");

            NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
            
            NameComponent path[] = ncRef.to_name("Reverse");

            ncRef.rebind(path , h_ref);

            System.out.println("Step3");
            orb.run();

            System.out.println("Reverse Server reading and watting....");

                
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
            }
            
    }    
}
