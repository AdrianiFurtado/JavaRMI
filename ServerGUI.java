import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class ServerGUI{

	public static void main(String args[]){

		new SGUI();

	}
}

class SGUI{

	SGUI(){

		GridLayout grid = new GridLayout(2,1);		
		JFrame frame = new JFrame();
		frame.setLayout(grid);

		//add Logo to frame
		JPanel imgpanel = new JPanel(new FlowLayout());		
		JLabel img = new JLabel();
		ImageIcon logo = new ImageIcon(getClass().getResource("UoW.png"));
		img.setIcon(logo);
		imgpanel.add(img);
		frame.add(imgpanel);

		//add Text area
		String greeting = "Welcome, all actions performe will be presented here";
		JPanel textpanel = new JPanel();
		JTextArea textarea = new JTextArea(greeting);
		JScrollPane scroll = new JScrollPane(textarea);
		scroll.setPreferredSize(new Dimension(690,230));
		textpanel.add(scroll);
		frame.add(textpanel);


		frameSetup(frame); 
	
	}

	void frameSetup(JFrame frame){

		frame.setTitle("Datalog Server");
		frame.setVisible(true);
		frame.setLocation(250,250);
		frame.setResizable(false);
		frame.setSize(700,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
