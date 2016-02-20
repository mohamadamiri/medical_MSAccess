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
	JButton companies,employeeInformation,employeemedical,visit,paraclinick,breathe,phistory,jhistory,ear,
	limitation,doctorVisit,search;
	int pageLength=960;
	int pageHeight=650;
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
		
		//ImageIcon img=new ImageIcon("company-icon-300.png");
		companies=new JButton("مشخصات فردی");
		companies.setBackground(new Color(235, 235, 235));
		//companies.setBorderPainted(false);
		companies.setSize(200, 50);
		companies.setLocation(10, 10);
		companies.addActionListener(this);
		getContentPane().add(companies);
		
		//ImageIcon img1=new ImageIcon("information-icon-hi.png");
		employeeInformation=new JButton("آزمایش پارا کلینیکی");
		employeeInformation.setBackground(new Color(235, 235, 235));
		//employeeInformation.setBorderPainted(false);
		employeeInformation.setSize(200, 50);
		employeeInformation.setLocation(320, 10);
		employeeInformation.addActionListener(this);
		getContentPane().add(employeeInformation);
		
		//ImageIcon img2=new ImageIcon("File-icon.png");
		employeemedical=new JButton("آدرس اکسس");
		employeemedical.setBackground(new Color(235, 235, 235));
		//employeemedical.setBorderPainted(false);
		employeemedical.setSize(200, 50);
		employeemedical.setLocation(630, 10);
		employeemedical.addActionListener(this);
		getContentPane().add(employeemedical);
		
		visit=new JButton("ارجاعات");
		visit.setBackground(new Color(235, 235, 235));
		//visit.setBorderPainted(false);
		visit.setSize(200, 50);
		visit.setLocation(10, 70);
		visit.addActionListener(this);
		getContentPane().add(visit);
		
		paraclinick=new JButton("پاراکلینیک");
		paraclinick.setBackground(new Color(235, 235, 235));
		//paraclinick.setBorderPainted(false);
		paraclinick.setSize(200, 50);
		paraclinick.setLocation(320, 70);
		paraclinick.addActionListener(this);
		getContentPane().add(paraclinick);
		
		breathe=new JButton("تست تنفس");
		breathe.setBackground(new Color(235, 235, 235));
		//breathe.setBorderPainted(false);
		breathe.setSize(200, 50);
		breathe.setLocation(630, 70);
		breathe.addActionListener(this);
		getContentPane().add(breathe);
		
		limitation=new JButton("محدودیت کاری");
		limitation.setBackground(new Color(235, 235, 235));
		//limitation.setBorderPainted(false);
		limitation.setSize(200, 50);
		limitation.setLocation(10, 130);
		limitation.addActionListener(this);
		getContentPane().add(limitation);
		
		phistory=new JButton("سابقه شخصی");
		phistory.setBackground(new Color(235, 235, 235));
		//phistory.setBorderPainted(false);
		phistory.setSize(200, 50);
		phistory.setLocation(320, 130);
		phistory.addActionListener(this);
		getContentPane().add(phistory);
		
		jhistory=new JButton("سوابق شغلی");
		jhistory.setBackground(new Color(235, 235, 235));
		//jhistory.setBorderPainted(false);
		jhistory.setSize(200, 50);
		jhistory.setLocation(630, 130);
		jhistory.addActionListener(this);
		getContentPane().add(jhistory);
		
		doctorVisit=new JButton("معاینه پزشکی");
		doctorVisit.setBackground(new Color(235, 235, 235));
		//doctorVisit.setBorderPainted(false);
		doctorVisit.setSize(200, 50);
		doctorVisit.setLocation(10, 190);
		doctorVisit.addActionListener(this);
		getContentPane().add(doctorVisit);
		
		ear=new JButton("شنوایی سنجی");
		ear.setBackground(new Color(235, 235, 235));
		//ear.setBorderPainted(false);
		ear.setSize(200, 50);
		ear.setLocation(320, 190);
		ear.addActionListener(this);
		getContentPane().add(ear);
		
		search=new JButton("جستجو");
		search.setBackground(new Color(235, 235, 235));
		//ear.setBorderPainted(false);
		search.setSize(200, 50);
		search.setLocation(630, 190);
		search.addActionListener(this);
		getContentPane().add(search);
		
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
			new Visit(this, myConnect,"مشخصات فردی");
			//cp = new CompanyPage(this,myConnect);
			//this.setVisible(false);
		}
		else if(b==employeeInformation){
			//new UserPage(this, myConnect);
			new Visit(this, myConnect,"آزمایش پارا کلینیکی");
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
		else if(b==visit){
			new Visit(this, myConnect,visit.getText());
		}
		else if(b==paraclinick){
			new Visit(this, myConnect,paraclinick.getText());
		}
		else if(b==breathe){
			new Visit(this, myConnect,breathe.getText());
		}
		else if(b==limitation){
			new Visit(this, myConnect,limitation.getText());
		}
		else if(b==phistory){
			new Visit(this, myConnect,phistory.getText());
		}
		else if(b==jhistory){
			new Visit(this, myConnect,jhistory.getText());
		}
		else if(b==doctorVisit){
			new Visit(this, myConnect,doctorVisit.getText());
		}
		else if(b==ear){
			new Visit(this, myConnect,ear.getText());
		}
		else if(b==search){
			new Search(myConnect);
		}
	}

}
