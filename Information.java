//Adriani Furtado 
//W1389795
import java.io.*;

//Information class which will be Serialized and sent over for the data logger to store
public class Information implements Serializable{

	//Variables used to store information
	String name;
	String surname;
	int age;
	int ID;

	//Set and get methods for each variable to allow
	//for data storage and retrival
	public String getName(){
	
		return name;
	}

	public void setName(String name){

		this.name = name;

	}

	public String getSurname(){

                return surname;
        }

        public void setSurname(String surname){

                this.surname = surname;

        }

	public int getAge(){

                return age;
        }

        public void setAge(int age){

                this.age = age;

        }

	public int getID(){

                return ID;
        }

        public void setID(int ID){

                this.ID = ID;

        }
}
