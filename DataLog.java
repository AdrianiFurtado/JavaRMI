//Adriani Furtado
//W1389795
import java.io.Serializable;
import java.rmi.*;

// Data Logger interface
//remote interface containing the methods the client will be able to access
public interface DataLog extends Remote{

	//method that reads a serialized object in and returns a String
	//of confirmation
	public String store(Information info ) throws RemoteException;
	
	//method to send the user a serialized object back
	public Information read() throws RemoteException;
	
	//method to return a message to the user
	public String msg() throws RemoteException;

}
