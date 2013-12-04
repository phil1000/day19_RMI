import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.registry.*;
import java.rmi.Naming;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

public class EchoServerClient {

	public void launch(String myString) {
		// 1. if no security manager running, launch one
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		try {
			// 2. find a reference to remote server object
			Remote myService = Naming.lookup("//127.0.0.1:1099/echo");
			EchoService echoService = (EchoService) myService; // in order to use methods, need to downcast them to the right type
			//3. Now call the remote method
			
			String receivedEcho = echoService.echo(myString);
			System.out.println("Message Received was: " + receivedEcho);
			
			String currentDateTime = echoService.dateTime();
			System.out.println("Message Received was: " + currentDateTime);
			
			int result = echoService.calculator(2, 3, CalculationTypes.ADD); // testing out use of enums
			System.out.println("result of calculation was " + result);
			
			String fileName = "testfile.txt";
			String fileContents = echoService.findFile(fileName);
			if (fileContents.equals("")) {
				System.out.println("File:" + fileName + " not found");
			} else {
				System.out.println("File: " + fileName + "contains: " + fileContents);
			}
			
			fileName = "dummy.txt";
			fileContents = echoService.findFile(fileName);
			if (fileContents.equals("")) {
				System.out.println("File:" + fileName + " not found");
			} else {
				System.out.println("File: " + fileName + "contains: " + fileContents);
			}
			
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		} catch (NotBoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String myString="";
		if (args.length>0) { 
			for (int i=0;i<args.length;i++) {
				myString = myString + args[i]; 
			}
		} else {
			myString = "Dummy Text";
		}
		
		EchoServerClient script = new EchoServerClient();
		script.launch(myString);
	}
}
