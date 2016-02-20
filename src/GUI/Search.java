package GUI;

import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.Connect;
import main.HtmlWriter;

public class Search extends JDialog implements ActionListener{
	JButton search;	
	Connect c;
	String[] obj;
	TextField tfs;
	JLabel name;
	//String[] columnNames ;//={"نام" , "قند" , "چربی" , "سال معاینه"};
	
	//Font font=new Font("B Nazanin", Font.PLAIN, 25);
	String[] dbt={"ارجاعات","آزمایش پارا کلینیکی","پاراکلینیک","تست تنفس","سابقه شخصی","سوابق شغلی","شنوایی سنجی","محدودیت کاری","مشخصات فردی","معاینه پزشکی"};
	public Search(Connect myConnect) {
		this.setModal(true);
		c=myConnect;
		Dimension d = getToolkit().getScreenSize();
		setSize(400,400);
		setLocation((d.width-400)/2,(d.height-400)/2);
		setLayout(null);
		//columnNames=myConnect.columnGetter(dbt);
		//ob= myConnect.userGetter("select * from "+dbt , columnNames.length);
		
		tfs=new TextField();
		tfs.setSize(100, 30);
		tfs.setLocation(100, 50);
		getContentPane().add(tfs);
		
		name =new JLabel("نام :");
		name.setSize(60, 30);
		name.setLocation(220, 50);
		getContentPane().add(name);
		
		search=new JButton("search");
		search.setSize(100, 30);
		search.setLocation(100, 90);
		search.addActionListener(this);
		getContentPane().add(search);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(b==search){
			Vector<Vector<String>> strs=new Vector<Vector<String>>();
			Vector<Vector<String>> columnNames =new Vector<Vector<String>>();
			String s=tfs.getText();
			if(!s.isEmpty()){
				for(int i=0;i<dbt.length;i++){
					//int count=c.columnCount(dbt[i]);
					columnNames.add(c.columnGetterAll(dbt[i]));
					//obj=c.searchGetter("select * from "+dbt[i]+" WHERE [نام]='"+s+"'", columnNames.length);
					strs.add(c.searchGetter("select * from "+dbt[i]+" WHERE [شماره پذیرش/پرسنلی]='"+s+"'", columnNames.size()));
					//if(strs.size()==0)
						//JOptionPane.showMessageDialog(null, "cannot find "+dbt[i]+"!!");
				}
				HtmlWriter hw=new HtmlWriter();
				hw.addHtml(dbt, columnNames, strs);
			}
			else
				JOptionPane.showMessageDialog(null, "the text field is empty!");
			//TODO 3 ta td 1 tr
		}
		
	}

}
