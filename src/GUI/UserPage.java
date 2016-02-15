package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import main.Connect;

public class UserPage extends JFrame implements ActionListener{
	JButton graph,search;
	MyTable table;
	JFrame jf;
	Connect c;
	String[][] ob;
	TextField tfs,tfg;
	JLabel name;
	String[] columnNames ={"نام" , "قند" , "چربی" , "سال معاینه"};
	Font font=new Font("B Nazanin", Font.PLAIN, 25);
	DefaultTableModel model;
	JScrollPane jsp;
	public UserPage(JFrame jf,Connect myConnect) {
		super("اعضا");
		this.jf = jf;
		c=myConnect;
		Dimension d = getToolkit().getScreenSize();
		setSize(1280,720);
		setLocation((d.width-1280)/2,(d.height-720)/2);
		setLayout(null);
		setVisible(true);
		ob= myConnect.userGetter("select * from User ORDER BY [سال معاینه] ASC");
		jf.setVisible(false);

		table = new MyTable(ob, columnNames);
		model = (DefaultTableModel)table.getModel();
		table.setSize(400, 650);
		table.setLocation(700, 50);
		table.setBackground(new Color(235,235,235));
		table.setFont(font);
		table.getTableHeader().setFont(font);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		
		table.setRowHeight(40);
		getContentPane().add(table);
		
		jsp=new JScrollPane(table);
		jsp.setSize(400, 650);
		jsp.setLocation(700, 50);
		getContentPane().add(jsp);
		
		tfs=new TextField();
		tfs.setSize(100, 30);
		tfs.setLocation(50, 50);
		getContentPane().add(tfs);
		
		name =new JLabel("نام :");
		name.setSize(60, 30);
		name.setLocation(170, 50);
		getContentPane().add(name);
		
		search=new JButton("search");
		search.setSize(100, 30);
		search.setLocation(50, 90);
		search.addActionListener(this);
		getContentPane().add(search);
		
		JLabel cn =new JLabel("شماره ستون :");
		cn.setSize(60, 30);
		cn.setLocation(170, 400);
		getContentPane().add(cn);
		
		tfg=new TextField();
		tfg.setSize(100, 30);
		tfg.setLocation(50, 400);
		getContentPane().add(tfg);
		
		graph=new JButton("graph");
		graph.setSize(100, 30);
		graph.setLocation(50, 450);
		graph.addActionListener(this);
		getContentPane().add(graph);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		jf.setVisible(true);
	};
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(b==search){
			String s=tfs.getText();
			if(!s.isEmpty()){
				//ob=null;
				
				ob=c.userGetter("select * from User WHERE [نام]='"+s+"' ORDER BY [سال معاینه] ASC");
				if(ob.length==0)
					JOptionPane.showMessageDialog(null, "cannot find!!");
				table.ChangeData(ob);
				DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
				table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
			}
			else
				JOptionPane.showMessageDialog(null, "the text field is empty!");
		}
		else if(b==graph){
			String s=tfg.getText();
			if(s.isEmpty())
				JOptionPane.showMessageDialog(null, "text field is empty!!");
			else{
				int k=Integer.parseInt(s);
				List<Double> scores=new ArrayList<Double>();
				for(int i=0;i<ob.length;i++){
					scores.add(Double.parseDouble(ob[i][k]));
				}
				GraphPanel.createAndShowGui(scores);
			}
		}
		
	}

}
