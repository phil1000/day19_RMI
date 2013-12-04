import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * an implementation of the echo service.
 */
public class EchoServer extends UnicastRemoteObject implements EchoService {

	public EchoServer() throws RemoteException {
		// nothing to initialise for this object, but need to 
		// explicitly provide a constructor that throws Remote Exception
	}
	
	@Override
	public String echo(String s) {
			System.out.println("received and about to return the string " + s); // just to show a connection has been made
			return s;
	}
}
