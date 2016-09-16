//Adriani Furtado
//W1389795
import java.rmi.*;
import java.rmi.Naming;
import java.io.*;

//Client to test the server
//thread to prove the server is thread safe
class testThread extends Thread{

	private Thread t;
	private String threadname;

	//give new thread a name
	testThread(String name){
	
		threadname = name;
		System.out.println("Creating thread " + threadname);

	}

	//run method to connect the thread to the server
	//synchronized to prevent threads calling function at the same time
	public synchronized void run(){

		try{
		connect();
		}catch(Exception e){}
	}

	//connect method that requests a serialized object from the server
	//and prints the values from the object 
	public void connect() throws Exception{

        DataLog log = (DataLog) Naming.lookup("LogServer");
	Information info = null;
	info = log.read();
	System.out.println(info.getID() + "\n" + info.getName() + "\n" + info.getSurname() + "\n");

	}

	//start method
	public void start(){

		if(t == null){

			t = new Thread(this, threadname);
			t.start();			

		}
	}
}

public class tclient{

	public static void main(String[] args){

		//create new threads accorfing to user input
                int num = Integer.parseInt(args[0]);		
		testThread[] t = new testThread[num];

		for(int i = 0; i < num; i++){
		
		t[i] = new testThread("Thread " + i);
		t[i].start();

		}

	}

}
