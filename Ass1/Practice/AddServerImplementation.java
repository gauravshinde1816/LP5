import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class AddServerImplementation extends UnicastRemoteObject implements AddServerInterface {

    AddServerImplementation() throws RemoteException {
        super();
    }

    @Override
    public double add(double d1, double d2) throws RemoteException {
        return d1 + d2;
    }

}
