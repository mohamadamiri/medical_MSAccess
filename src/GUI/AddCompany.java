package GUI;


import javax.swing.JFrame;

import main.Connect;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableCellRenderer;

public class AddCompany extends JDialog implements ActionListener{

	private JTextField companyName;
	private JTextField managerName;
	private JTextField companyAddress;
	private JTextField companyTel;
	JButton cancel,ok;
	Connect myconnect;
	MyTable table;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCompany window = new AddCompany();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public AddCompany(Connect myconnect,MyTable table) {
		this.myconnect=myconnect;
		this.table=table;
		setModal(true);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dimension d = getToolkit().getScreenSize();
		this.setBounds((d.width-450)/2,(d.height-300)/2, 450, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		companyName = new JTextField();
		companyName.setSize(158, 20);
		companyName.setLocation(187, 13);
		getContentPane().add(companyName);
		companyName.setColumns(10);
		
		managerName = new JTextField();
		managerName.setSize(158, 20);
		managerName.setLocation(187, 46);
		getContentPane().add(managerName);
		managerName.setColumns(10);
		
		JLabel namelabel = new JLabel("\u0646\u0627\u0645 \u0634\u0631\u06A9\u062A");
		namelabel.setBounds(357, 15, 56, 16);
		getContentPane().add(namelabel);
		
		JLabel managerlable = new JLabel("\u0646\u0627\u0645 \u06A9\u0627\u0631\u0641\u0631\u0645\u0627");
		managerlable.setBounds(357, 48, 56, 16);
		getContentPane().add(managerlable);
		
		companyAddress = new JTextField();
		companyAddress.setBounds(187, 79, 158, 22);
		getContentPane().add(companyAddress);
		companyAddress.setColumns(10);
		
		JLabel addresslabel = new JLabel("\u0622\u062F\u0631\u0633");
		addresslabel.setBounds(357, 82, 56, 16);
		getContentPane().add(addresslabel);
		
		companyTel = new JTextField();
		companyTel.setBounds(187, 114, 158, 22);
		getContentPane().add(companyTel);
		companyTel.setColumns(10);
		
		JLabel tellabel = new JLabel("\u062A\u0644\u0641\u0646");
		tellabel.setBounds(357, 117, 56, 16);
		getContentPane().add(tellabel);
		
		cancel = new JButton("\u0627\u0646\u0635\u0631\u0627\u0641");
		cancel.setBounds(93, 181, 97, 25);
		cancel.addActionListener(this);
		getContentPane().add(cancel);
		
		ok = new JButton("\u062B\u0628\u062A");
		ok.setBounds(224, 181, 97, 25);
		ok.addActionListener(this);
		getContentPane().add(ok);
		//this.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{this.getContentPane()}));
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(b==cancel){
			dispose();
		}
		else if(b==ok){
			//INSERT INTO target [(field1[, field2[, …]])]     VALUES (value1[, value2[, …])
			String s="INSERT INTO Company ([نام] , [نام کارفرما]) VALUES ('"
					+managerName.getText()+ "','"+
					companyName.getText()+ "')";
							
			myconnect.companySetter(s);
			String[][] ob= myconnect.companyGetter("select * from Company");
			table.ChangeData(ob);
			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
			table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
			table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
			dispose();
		}
		setModal(false);
		
	}
	
}
