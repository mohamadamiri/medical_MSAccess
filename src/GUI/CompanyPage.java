package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.print.*;
import java.util.ArrayList;
import java.util.List;

import main.Connect;

public class CompanyPage extends JFrame implements ActionListener , Printable {

	int pageLength=1280;
	int pageHeight=720;
	JButton newCompany,edit,deleteCompany,returnHome,print,search,graph,addCompany;
	MyTable table;
	JFrame jf;
	Connect c;
	String[][] ob;
	TextField tfs;
	JLabel name;
	String[] columnNames ;
	Font font=new Font("B Nazanin", Font.PLAIN, 25);
	DefaultTableModel model;
	JScrollPane jsp;
	public CompanyPage(JFrame jf,Connect myConnect) {
		super("شرکت ها");
		Dimension d = getToolkit().getScreenSize();
		setSize(pageLength,pageHeight);
		setLocation((d.width-pageLength)/2,(d.height-pageHeight)/2);
		setLayout(null);
		setVisible(true);
		this.jf = jf;
		c=myConnect;
		
		
		columnNames=c.columnGetter("Company");
		ob= myConnect.companyGetter("select * from Company");
		jf.setVisible(false);
		
		table = new MyTable(ob, columnNames);
		model = (DefaultTableModel)table.getModel();
		//table.setSize(250, 650);
		table.setLocation(1000, 50);
		table.setBackground(new Color(235,235,235));
		table.setFont(font);
		table.getTableHeader().setFont(font);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		
		table.setRowHeight(40);
		TableColumn column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(240);
		column = table.getColumnModel().getColumn(1);
		column.setPreferredWidth(240);
		
		jsp=new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		table.setPreferredScrollableViewportSize(new Dimension(300, 200));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		jsp.setSize(500, 600);
		jsp.setLocation(500, 50);
		getContentPane().add(jsp,BorderLayout.CENTER);
		
		print=new JButton("print");
		print.setSize(100,50);
		print.setLocation(50, 500);
		print.addActionListener(this);
		getContentPane().add(print);
		
		tfs=new TextField();
		tfs.setSize(100, 30);
		tfs.setLocation(50, 50);
		getContentPane().add(tfs);
		
		name =new JLabel("نام شرکت :");
		name.setSize(60, 30);
		name.setLocation(170, 50);
		getContentPane().add(name);
		
		search=new JButton("search");
		search.setSize(100, 30);
		search.setLocation(50, 90);
		search.addActionListener(this);
		getContentPane().add(search);
		
		graph=new JButton("graph");
		graph.setSize(100, 30);
		graph.setLocation(50, 450);
		graph.addActionListener(this);
		getContentPane().add(graph);
		
		addCompany=new JButton("addCompany");
		addCompany.setSize(150, 30);
		addCompany.setLocation(50, 400);
		addCompany.addActionListener(this);
		getContentPane().add(addCompany);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		jf.setVisible(true);
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(b==print){
			/*PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintable(this);
			boolean doPrint = job.printDialog();
			if (doPrint) {
			    try {
			        job.print();
			    } catch (PrinterException ee) {
			        // The job did not successfully
			        // complete
			    }
			}*/ 	//https://docs.oracle.com/javase/tutorial/uiswing/misc/printtable.html
			try {
				table.print();
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(b==search){
			String s=tfs.getText();
			if(!s.isEmpty()){
				//ob=null;
				
				ob=c.companyGetter("select * from Company WHERE [نام]='"+s+"'");
				if(ob.length==0)
					JOptionPane.showMessageDialog(null, "cannot find!!");
				table.ChangeData(ob);
				DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
				table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
			}
			else
				JOptionPane.showMessageDialog(null, "the text field is empty!");
			
		}
		else if(b==graph){
			//new Visit(this, c);
		}
		else if(b==addCompany){
			new AddCompany(c,table);
		}
		
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		//System.out.println("sla,");
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		if (pageIndex > 0) {
	         return NO_SUCH_PAGE;
	    }

	    // User (0,0) is typically outside the
	    // imageable area, so we must translate
	    // by the X and Y values in the PageFormat
	    // to avoid clipping.
	    Graphics2D g2d = (Graphics2D)graphics;
	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

	    // Now we perform our rendering
	    int x=100;
	    int y=100;
	    //System.out.println(ob.length+" "+(String)ob[0][0]);
	    for(int i=0;i<ob.length;i++){
	    	graphics.drawString((String)ob[i][0]+ " "+ (String)ob[i][1], x, y);
	    	y+=50;
	    }
	    

	    // tell the caller that this page is part
	    // of the printed document
	    return PAGE_EXISTS;
		
	}

}
