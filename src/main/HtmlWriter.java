package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Vector;

import javax.swing.JOptionPane;

public class HtmlWriter {
	File f;
	FileOutputStream fos=null;
	OutputStreamWriter osw=null;
	BufferedWriter bw=null;
	//DataOutputStream bw=null;
	public HtmlWriter() {
		f=new File("report.html");
		if(f.isFile())
			f.delete();
		try {
			f.createNewFile();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, e1.toString());
		}
		
		/*try {
			fos =new FileOutputStream(f);
			osw=new OutputStreamWriter(fos);
			bw=new BufferedWriter(osw);
			
			bw.write(p);
				
			bw.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}*/
	}
	
	public void addHtml(String[] tname,Vector<Vector<String>> columns, Vector<Vector<String>> records){
		
		try {
			
			fos =new FileOutputStream(f);
			osw =new OutputStreamWriter(fos, "UTF-8");
			bw=new BufferedWriter(osw);
			bw.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!DOCTYPE html>\n<html>\n<head>"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n<title>ê“«—‘ ÿ» ò«—</title>"
					+ "<style>table{padding: 15px;border-style: solid;text-align: right;}\n.sumary{text-align: center;}</style>\n</head><body>");
			
			for(int j=0;j<tname.length ;j++){
				bw.write("<p class=\"sumary\">"+tname[j]+"<table style=\"width:100%\">");

				for(int i=0;i<columns.elementAt(j).size();i++){
					if(i%3==0){
						try{
							bw.write("<tr><td>"+columns.elementAt(j).elementAt(i)+":"+records.elementAt(j).elementAt(i)+"</td>");
						} catch(NullPointerException ee){
							bw.write("<tr><td>"+columns.elementAt(j).elementAt(i)+":</td>");
						}
						catch(ArrayIndexOutOfBoundsException ee){
							bw.write("<tr><td>"+columns.elementAt(j).elementAt(i)+":</td>");
						}
					}
					else if(i%3==1){
						try{
							bw.write("<td>"+columns.elementAt(j).elementAt(i)+":"+records.elementAt(j).elementAt(i)+"</td>");
						} catch(NullPointerException ee){
							bw.write("<td>"+columns.elementAt(j).elementAt(i)+":</td>");
						}
						catch(ArrayIndexOutOfBoundsException ee){
							bw.write("<td>"+columns.elementAt(j).elementAt(i)+":</td>");
						}

					}
					else if(i%3==2){
						try{
							bw.write("<td>"+columns.elementAt(j).elementAt(i)+":"+records.elementAt(j).elementAt(i)+"</td></tr>");
						} catch(NullPointerException ee){
							//ee.printStackTrace();
							bw.write("<td>"+columns.elementAt(j).elementAt(i)+":</td></tr>");
						}
						catch(ArrayIndexOutOfBoundsException ee){
							bw.write("<td>"+columns.elementAt(j).elementAt(i)+":</td></tr>");
						}

					}
				}
				bw.write("</table></p>");
			}
			bw.write("</body></html>");
				
			bw.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

}
