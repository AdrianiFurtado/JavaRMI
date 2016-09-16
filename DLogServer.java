//Adriani Furtado
//W1389795
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
import java.util.Date;
import java.text.*;

//Datalog Server
public class DLogServer extends UnicastRemoteObject implements DataLog{

	//method for the creation of the stub and skelleton 	
	public DLogServer() throws RemoteException{

		super();
	}

	//implmentation of the msg method
	//synchronized to only allow one client to run it at a time
	public String msg(){synchronized(this){return "hello its me";}}

	//store method implementation
	public String store(Information info) throws RemoteException{

		System.out.println("Client requesting 'store' method");
	
		//create a date format for the timestamp
		//get the current time and date
		DateFormat dformat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
                Date date = new Date();
		
		//synchronized in order to make server thread save
		//preventing multiple clients from writting to 
		//server simuntaniously
		synchronized(this){
                	try{

				//open a file PrintWritter to writer information to the storage file
				PrintWriter writer = new PrintWriter(new FileWriter("Data.txt", true));
                        	writer.println();
				//creating the Object output stream to store the 
				//serializable object into a file and timestamp it
				ObjectOutputStream output = new ObjectOutputStream( new FileOutputStream("Data.txt", true));
                        	output.writeObject(info);
				writer.println("save time: " + dformat.format(date));
                        	writer.close();
				//second output stream to write the latest object in a file which
				//can be retrieved back by any user
				ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream("info.txt"));
				out.writeObject(info);

				System.out.println("Method successfully performed");
        	                return "Data Was Succesfully Stored";

                	}catch(IOException e){

				return "Store Error";

			}
		}

	}

	//method to read a serialized string from a file
	//and send it to a client requesting it
	public Information read(){

		//create the file object
		Information info = null;
		System.out.println("Client requested 'read' method");

		try{

			//create object input stream to read serialized string
			//and convert it to a object
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("Info.txt"));
			info  = (Information) input.readObject();
			input.close();		

			//return the object the client
			System.out.println("Method successfully performed");
			return info;


		}catch(IOException e){return null;}
		catch(ClassNotFoundException c){return null;}
	}
	public static void main(String args[]) throws Exception{

		//create an instance of the server object
		//rebind the server to the RMI registry
		//
		DLogServer srv = new DLogServer();
		Naming.rebind("LogServer", srv);
		System.out.println("Server is online");
		System.out.println("Waiting for client...");

	}
}
