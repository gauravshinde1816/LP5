import java.rmi.*;

public class AddServer  {
    public static void main(String[] args) {
        try {
            
            AddServerImplementation impl = new AddServerImplementation();
            Naming.bind("AddServer", impl);

        } catch (Exception e) {
            System.out.println(e);
        }
      
    }
}
