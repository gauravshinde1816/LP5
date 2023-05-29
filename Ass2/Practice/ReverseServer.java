import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import ReverseModule.*;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NameComponent;
public class ReverseServer {
    public static void main(String[] args) {

        try {
            // initilize the ORB
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

            // active RootPOA
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();


            // create Reverse Object
            ReverseImpl rvr = new ReverseImpl();
            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(rvr);

            // helper ref
            Reverse h_ref = ReverseHelper.narrow(ref);


            // Naming Ref
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent path[] = ncRef.to_name("Reverse");
            ncRef.rebind(path, h_ref);

            orb.run();

        } catch (Exception e) {
            // TODO: handle exception

            System.out.println("Exception "  +  e);
        }

    }
}
