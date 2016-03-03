package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class FileRead {
	public String p="";
	public FileRead() {
		File f=new File("dbpath.txt");
		if(!f.isFile()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		FileInputStream fis=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		String str="";
		try {
			fis =new FileInputStream(f);
			isr=new InputStreamReader(fis);
			br=new BufferedReader(isr);
			
			str=br.readLine();
			if(str!=null && !str.isEmpty())	
				p=str;
			br.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
		}
	}

}
