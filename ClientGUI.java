//Adriani Furtdo
//W1389795
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.*;
import java.rmi.Naming;
import java.io.*;

public class ClientGUI{


	public static void main(String[] args)throws Exception{

		//create a new GUI when excecuted
        	new GUI();
        }
}

class GUI implements ActionListener{
	
	//variables used through the code
	Information info = new Information();	
	JLabel[] label = new JLabel[3];
	JTextField[] textfield = new JTextField[3];
	GridBagConstraints c = new GridBagConstraints();	
	JFrame frame = new JFrame();
	JButton savebutton, uploadbutton;

	GUI(){
		//Setting up the main layout of the frame
		GridLayout grid = new GridLayout(3,1);
		frame.setLayout(grid);

		//Creation of the panel to hold the logo
		//implementation of the logo
		//Adding the panel to the frame
		JPanel imgpanel = new JPanel();
		JLabel img = new JLabel();
		ImageIcon logo = new ImageIcon(getClass().getResource("UoW.png"));
		img.setIcon(logo);
		imgpanel.add(img);
		frame.add(imgpanel);		

		//Creating the text area panel
		//A loop to create the textfields and labels automatically
		//calling a method to add the textfields and labels to the panel
		JPanel textarea = new JPanel(new GridBagLayout());
		String[] string = {"ID","Name","Surname"};

		for(int i = 0; i < 3; i++){
			
			label[i] = new JLabel(string[i]);
			textfield[i] = new JTextField(25);
			textfield[i].setColumns(9);
			addtopanel(label[i], textfield[i], i , textarea);		

		}
		
		frame.add(textarea);

		//Create the button panel
		//create the 2 buttons with action listeners 
		//add the buttons to the frame
		JPanel buttonpanel = new JPanel();
		savebutton = new JButton("Save");
		buttonpanel.add(savebutton);
		savebutton.addActionListener(this);

		uploadbutton = new JButton("Upload");
		buttonpanel.add(uploadbutton);
		frame.add(buttonpanel);
		uploadbutton.addActionListener(this);	

		//method that handles the frame
		//visibility, size, title, location and resiable
		framesetup(frame);

	}
		
	//Action listener events
	public void actionPerformed(ActionEvent e){

		//listen for a press on the save button
		if(e.getSource() == savebutton){
	
			//temporary variables
			int IDnum = 0;
			String NAME = "";
			String SURNAME = "";

			//retrieve the values stored in the textfields
			//and store it in the temporary variables
			//store the temporary variable values
			//in to the Information.java variables
                        IDnum = Integer.parseInt(textfield[0].getText());
			info.setID(IDnum);
			NAME = textfield[1].getText();
			info.setName(NAME);
			SURNAME = textfield[2].getText();		
			info.setSurname(SURNAME);

			//create a pop up box displaying the saved details
			JOptionPane.showMessageDialog(frame, IDnum+"\n"+NAME+"\n"+SURNAME);
			System.out.println(info.getName());

		}

		//listen for a press of the upload button
		if(e.getSource() == uploadbutton){

			//prompt the user with a pop asking if the data has been save
			//if yes the data will be uploaded to the server via a method call
			//and a pop up will inform the user that data has been sent
			//if not another pop up will warn the user to save the file before
			//uploading it
			try{
				//pop asking if data has been saved
	                    	int result = JOptionPane.showConfirmDialog(null, "Have you saved the data?", null, JOptionPane.YES_NO_OPTION );

			 	if(result == JOptionPane.YES_OPTION){

					//if data is save call upload method
					upload();
        				JOptionPane.showMessageDialog(frame, "Data has been uploaded");

				}else{
			
					JOptionPane.showMessageDialog(frame, "Please save before uploading!");
					
				}

	                }catch(Exception excep){}

		}

	}
	

	void addtopanel(JLabel label, JTextField textfield,int i, JPanel textarea){

		//method that will take in the JLabel, JTextField and integer and the JPAnel
		//and add both the label and textfield to the panel
		//use of grid to specify where each item goes
		c.gridx = 0 ; 
		c.gridy = i ;
		textarea.add(label,c);
		c.gridx = 1;
		textarea.add(textfield,c);		
	
	}

	void framesetup(JFrame frame){
		
		//method to set up the main frame
		//setting title, size, location
		frame.setTitle("Datalog Client");
		frame.setSize(500, 300);
		frame.setLocation(250,250);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
	}
	
	public void upload() throws Exception{

		//upload method
		//inform the user that data is being uploaded
		//look up the server
		//perform the method set by the server interface
		//and pass the serialized object
		System.out.println("Uploading data");
                DataLog log = (DataLog) Naming.lookup("LogServer");
                System.out.println(log.store(info));

        }
	
}
