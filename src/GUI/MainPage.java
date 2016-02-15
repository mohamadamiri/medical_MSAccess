package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.*;

import main.Connect;
import main.FileRead;
import main.FileWrite;

public class MainPage extends JFrame implements ActionListener{
	JButton companies,employeeInformation,employeemedical;
	int pageLength=960;
	int pageHeight=365;
	Connect myConnect;
	JFrame cp;
	public MainPage(String arg0) {
		super(arg0);
		myConnect = new Connect();
		FileRead fr=new FileRead();
		if(!fr.p.isEmpty()){
			myConnect.dbpath=fr.p;
			myConnect.CreateConnection();
		}
		else
			JOptionPane.showMessageDialog(null, "please choose a database!");
		Dimension d = getToolkit().getScreenSize();
		setSize(pageLength,pageHeight);
		setLocation((d.width-pageLength)/2,(d.height-pageHeight)/2);
		setLayout(null);
		
		ImageIcon img=new ImageIcon("company-icon-300.png");
		companies=new JButton("شرکت ها",img);
		companies.setBackground(new Color(235, 235, 235));
		companies.setBorderPainted(false);
		companies.setSize(300, 300);
		companies.setLocation(10, 10);
		companies.addActionListener(this);
		getContentPane().add(companies);
		
		ImageIcon img1=new ImageIcon("information-icon-hi.png");
		employeeInformation=new JButton("اطلاعات فردی شاغلین",img1);
		employeeInformation.setBackground(new Color(235, 235, 235));
		employeeInformation.setBorderPainted(false);
		employeeInformation.setSize(300, 300);
		employeeInformation.setLocation(320, 10);
		employeeInformation.addActionListener(this);
		getContentPane().add(employeeInformation);
		
		ImageIcon img2=new ImageIcon("File-icon.png");
		employeemedical=new JButton("پرونده پزشکی شاغل",img2);
		employeemedical.setBackground(new Color(235, 235, 235));
		employeemedical.setBorderPainted(false);
		employeemedical.setSize(300, 300);
		employeemedical.setLocation(630, 10);
		employeemedical.addActionListener(this);
		getContentPane().add(employeemedical);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		myConnect.closeConnection();
		System.exit(0);
	}
	public static void main(String[] args) {
		
		new MainPage("طب کار");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(b==companies){
			cp = new CompanyPage(this,myConnect);
			this.setVisible(false);
		}
		else if(b==employeeInformation){
			new UserPage(this, myConnect);

		}
		else if(b==employeemedical){
			JFileChooser chooser = new JFileChooser();
		     chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		     int option = chooser.showOpenDialog(this);
		     if (option == JFileChooser.APPROVE_OPTION) {
		    	 String path=chooser.getSelectedFile().getAbsolutePath();
		    	 myConnect = new Connect();
		       myConnect.dbpath=path;
		       new FileWrite(path);
		       myConnect.CreateConnection();
		     }
		     else {
		    	 JOptionPane.showMessageDialog(null, "you canceled");
		     }

		}
	}

}
