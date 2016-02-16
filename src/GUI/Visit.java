package GUI;

import java.awt.BorderLayout;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import main.Connect;

public class Visit extends JFrame implements ActionListener{
	JButton graph,search;
	MyTable table;
	JFrame jf;
	Connect c;
	String[][] ob;
	TextField tfs,tfg;
	JLabel name;
	String[] columnNames ;//={"نام" , "قند" , "چربی" , "سال معاینه"};
	//Font font=new Font("B Nazanin", Font.PLAIN, 25);
	DefaultTableModel model;
	JScrollPane jsp;
	String dbt;
	public Visit(JFrame jf,Connect myConnect,String dbt) {
		super(dbt);
		this.dbt=dbt;
		this.jf = jf;
		c=myConnect;
		Dimension d = getToolkit().getScreenSize();
		setSize(1280,720);
		setLocation((d.width-1280)/2,(d.height-720)/2);
		setLayout(null);
		setVisible(true);
		columnNames=myConnect.columnGetter(dbt);
		ob= myConnect.userGetter("select * from "+dbt , columnNames.length);
		jf.setVisible(false);
		
		table = new MyTable(ob, columnNames);
		model = (DefaultTableModel)table.getModel();
		//table.setSize(600, 650);
		table.setLocation(500, 50);
		table.setBackground(new Color(235,235,235));
		//table.setFont(font);
		//table.getTableHeader().setFont(font);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		
		table.setRowHeight(40);
		//getContentPane().add(table);
		TableColumn column;
		for(int i=0;i<table.getColumnCount();i++){
			column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(100);
			column.setCellRenderer(rightRenderer);
		}
			
		jsp=new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		table.setPreferredScrollableViewportSize(new Dimension(300, 200));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jsp.setSize(800, 500);
		jsp.setLocation(300, 50);
		getContentPane().add(jsp,BorderLayout.CENTER);
		
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
				int l=table.getColumnCount();
				ob=c.userGetter("select * from "+dbt+" WHERE [نام]='"+s+"'" , l);
				if(ob.length==0)
					JOptionPane.showMessageDialog(null, "cannot find!!");
				table.ChangeData(ob);
				DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
				for(int i=0;i<l;i++){
					table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
					table.getColumnModel().getColumn(i).setPreferredWidth(100);
				}
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
