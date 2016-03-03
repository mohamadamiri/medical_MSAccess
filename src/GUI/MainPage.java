package GUI;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.Connect;
import main.FileRead;
import main.FileWrite;

public class MainPage extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	
	JButton companies,employeeInformation,employeemedical,visit,paraclinick,breathe,phistory,jhistory,ear,
	limitation,doctorVisit,search;
	int pageLength;//=9620;
	int pageHeight;//=650;
	int buttonLength, buttonHeight;
	Connect myConnect;
	JFrame cp;
	public MainPage(String arg0) {
		super(arg0);
		myConnect = new Connect();
		FileRead fr=new FileRead();
		System.out.println(fr.p);
		if(!fr.p.isEmpty()){
			myConnect.dbpath=fr.p;
			myConnect.CreateConnection();
		}
		else
			JOptionPane.showMessageDialog(null, "please choose a database!");
		Dimension d = getToolkit().getScreenSize();
		pageLength = d.width/2;
		pageHeight = d.height/3;
		
		System.out.println((double)200/960);
		System.out.println((double)50/650);
		buttonLength = (pageLength/3)-60;
		buttonHeight= (pageHeight/3)-76;
		setSize(pageLength-50,pageHeight);
		setLocation((d.width-pageLength)/2,(d.height-pageHeight)/2);
		setLayout(null);
		setResizable(false);
		//ImageIcon img=new ImageIcon("company-icon-300.png");
		companies=new JButton("مشخصات فردی");
		companies.setBackground(new Color(235, 235, 235));
		//companies.setBorderPainted(false);
		companies.setSize(buttonLength,buttonHeight);
		companies.setLocation(20, 10);
		companies.addActionListener(this);
		getContentPane().add(companies);
		
		//ImageIcon img1=new ImageIcon("information-icon-hi.png");
		employeeInformation=new JButton("آزمایش پارا کلینیکی");
		employeeInformation.setBackground(new Color(235, 235, 235));
		//employeeInformation.setBorderPainted(false);
		employeeInformation.setSize(buttonLength,buttonHeight);
		employeeInformation.setLocation(30+20+buttonLength, 10);
		employeeInformation.addActionListener(this);
		getContentPane().add(employeeInformation);
		
		//ImageIcon img2=new ImageIcon("File-icon.png");
		employeemedical=new JButton("آدرس اکسس");
		employeemedical.setBackground(new Color(235, 235, 235));
		//employeemedical.setBorderPainted(false);
		employeemedical.setSize(buttonLength,buttonHeight);
		employeemedical.setLocation(60+20+2*buttonLength, 10);
		employeemedical.addActionListener(this);
		getContentPane().add(employeemedical);
		
		visit=new JButton("ارجاعات");
		visit.setBackground(new Color(235, 235, 235));
		//visit.setBorderPainted(false);
		visit.setSize(buttonLength, buttonHeight);
		visit.setLocation(20, buttonHeight+30);
		visit.addActionListener(this);
		getContentPane().add(visit);
		
		paraclinick=new JButton("پاراکلینیک");
		paraclinick.setBackground(new Color(235, 235, 235));
		//paraclinick.setBorderPainted(false);
		paraclinick.setSize(buttonLength,buttonHeight);
		paraclinick.setLocation(30+20+buttonLength, buttonHeight+30);
		paraclinick.addActionListener(this);
		getContentPane().add(paraclinick);
		
		breathe=new JButton("تست تنفس");
		breathe.setBackground(new Color(235, 235, 235));
		//breathe.setBorderPainted(false);
		breathe.setSize(buttonLength,buttonHeight);
		breathe.setLocation(60+20+2*buttonLength, buttonHeight+30);
		breathe.addActionListener(this);
		getContentPane().add(breathe);
		
		limitation=new JButton("محدودیت کاری");
		limitation.setBackground(new Color(235, 235, 235));
		//limitation.setBorderPainted(false);
		limitation.setSize(buttonLength,buttonHeight);
		limitation.setLocation(20, 2*buttonHeight+50);
		limitation.addActionListener(this);
		getContentPane().add(limitation);
		
		phistory=new JButton("سابقه شخصی");
		phistory.setBackground(new Color(235, 235, 235));
		//phistory.setBorderPainted(false);
		phistory.setSize(buttonLength,buttonHeight);
		phistory.setLocation(30+20+buttonLength, 2*buttonHeight+50);
		phistory.addActionListener(this);
		getContentPane().add(phistory);
		
		jhistory=new JButton("سوابق شغلی");
		jhistory.setBackground(new Color(235, 235, 235));
		//jhistory.setBorderPainted(false);
		jhistory.setSize(buttonLength,buttonHeight);
		jhistory.setLocation(60+20+2*buttonLength, 2*buttonHeight+50);
		jhistory.addActionListener(this);
		getContentPane().add(jhistory);
		
		doctorVisit=new JButton("معاینه پزشکی");
		doctorVisit.setBackground(new Color(235, 235, 235));
		//doctorVisit.setBorderPainted(false);
		doctorVisit.setSize(buttonLength,buttonHeight);
		doctorVisit.setLocation(20, 3*buttonHeight+70);
		doctorVisit.addActionListener(this);
		getContentPane().add(doctorVisit);
		
		ear=new JButton("شنوایی سنجی");
		ear.setBackground(new Color(235, 235, 235));
		//ear.setBorderPainted(false);
		ear.setSize(buttonLength,buttonHeight);
		ear.setLocation(30+20+buttonLength, 3*buttonHeight+70);
		ear.addActionListener(this);
		getContentPane().add(ear);
		
		search=new JButton("جستجو");
		search.setBackground(new Color(235, 235, 235));
		//ear.setBorderPainted(false);
		search.setSize(buttonLength,buttonHeight);
		search.setLocation(60+20+2*buttonLength, 3*buttonHeight+70);
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
