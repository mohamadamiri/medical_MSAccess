package main;

import java.sql.*;
import java.util.Vector;

import javax.swing.JOptionPane;
public class Connect{

     Connection  con = null;
     ResultSet rs;
     public String dbpath="";
     // Constructor:
     public Connect(){
    	 
     }

     public void closeConnection(){
          try{
               if(con != null)
                    con.close();
               con = null;
          }catch(Exception ex){
        	  JOptionPane.showMessageDialog(null, ex.toString());
          }
     }
     
     public  synchronized String[][] companyGetter(String sql){
//    	 CreateConnection();
    	 Vector<String[]> str=new Vector<String[]>();
    	 try
         {
             //con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/ASUS/Documents/testdb.accdb", "", "");
             Statement stmt = con.createStatement();
             rs = stmt.executeQuery(sql);
             while (rs.next())
             {
            	 String[] s=new String[2];
                 //System.out.println(rs.getString("نام")+" "+ rs.getString("نام کارفرما"));
                 s[0]=rs.getString("نام");
                 s[1]=rs.getString("نام کارفرما");
                 str.add(s);
             }
           
         }
         catch (SQLException ex)
         {
        	 JOptionPane.showMessageDialog(null, ex.toString());
         }
       String[][] obj=new String[str.size()][2];
    	 for(int i=0;i<str.size();i++){
    		 obj[i][0]=str.elementAt(i)[0];
    		 obj[i][1]=str.elementAt(i)[1];

    	 }
    	 return obj;
     }
     
     public  synchronized String[][] userGetter(String sql , int l){
//    	 CreateConnection();
    	 Vector<String[]> str=new Vector<String[]>();
    	 try
         {
             //con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/ASUS/Documents/testdb.accdb", "", "");
             Statement stmt = con.createStatement();
             rs = stmt.executeQuery(sql);
             while (rs.next())
             {
            	 String[] s=new String[l-1];
                 //System.out.println(rs.getString("نام")+" "+ rs.getString("نام کارفرما"));
                 /*s[0]=rs.getString("نام");
                 s[1]=rs.getString("قند");
                 s[2]=rs.getString("چربی");
                 s[3]=rs.getString("سال معاینه");*/
            	 for(int h=1;h<l;h++){
            		 s[h-1]=rs.getString(h+1);
            	 }
                 str.add(s);
             }
           
         }
         catch (SQLException ex)
         {
             ex.printStackTrace();
         }
       String[][] obj=new String[str.size()][l-1];
    	 for(int i=0;i<str.size();i++)
    		 for(int h=0;h<l-1;h++)
    		 obj[i][h]=str.elementAt(i)[h];

    	 return obj;
     }
     
     public  synchronized void companySetter(String sql){

    	 try
         {
             Statement stmt = con.createStatement();
             stmt.executeUpdate(sql);
           
         }
         catch (SQLException ex)
         {
             ex.printStackTrace();
         }
      
    	 
     }
     
     public String[] columnGetter(String table){
    	 String[] str = null;
    	 try
         {
             Statement stmt = con.createStatement();
             
             rs=stmt.executeQuery("SELECT * FROM "+table);
             //select column_name from information_schema.columns where [table_name]='Company'
             ResultSetMetaData metadata = rs.getMetaData();
           
             int columnCount = metadata.getColumnCount();
             str=new String[columnCount-1];
           
             // Get the column names; column indices start from 1
             
             for (int i=2; i<=columnCount; i++) {
            	 str[i-2] = metadata.getColumnName(i);
            	 //System.out.println(str[i-2]+" ");
             }

           //System.out.println(str.length);
         }
         catch (SQLException ex)
         {
             ex.printStackTrace();
         }
    	 return str;
     }

	public void CreateConnection() {
		if(con == null)
		{
			try
	        {
				if(dbpath.isEmpty())
					JOptionPane.showMessageDialog(null, "you didn't choose a database");
				else
	           con = DriverManager.getConnection("jdbc:ucanaccess://"+dbpath, "", "");
	            /*Statement stmt = con.createStatement();
	            System.out.println(System.currentTimeMillis() - time);
	            rs = stmt.executeQuery("select * from User");
	            
	            while (rs.next())
	            {
	                //Column names: 
	                System.out.println(rs.getString("نام")+" "+ rs.getInt("چربی")+" "+ rs.getInt("سال معاینه"));
	            }*/
	            /*if(con != null)
	                con.close();
	            con = null;*/
	        }
	        catch (SQLException ex)
	        {
	        	JOptionPane.showMessageDialog(null, ex.toString());
	        }
		}
	}
    

}