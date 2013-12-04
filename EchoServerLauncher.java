import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.registry.*;
import java.rmi.Naming;
import java.net.MalformedURLException;

public class EchoServerLauncher {

	public void launch() {
		// 1. if no security manager running, launch one
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		try {
			// 2. Create the registry if there isn't one
			LocateRegistry.createRegistry(1099);
			// 3. Create the server object
			EchoServer myServer = new EchoServer();
			// 4. Register/bind the server object on the registry
			// though the registry might be on a different machine
			String registryHost = "//localhost/"; // local host always refers to this 'local' machine
			String serviceName = "echo";
			Naming.rebind(registryHost+serviceName, myServer);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EchoServerLauncher script = new EchoServerLauncher();
		script.launch();
	}
}
