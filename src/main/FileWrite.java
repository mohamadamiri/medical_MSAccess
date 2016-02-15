package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JOptionPane;

public class FileWrite {

	public FileWrite(String p) {
		File f=new File("dbpath.txt");
		if(f.isFile())
			f.delete();
		try {
			f.createNewFile();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, e1.toString());
		}
		FileOutputStream fos=null;
		OutputStreamWriter osw=null;
		BufferedWriter bw=null;
		try {
			fos =new FileOutputStream(f);
			osw=new OutputStreamWriter(fos);
			bw=new BufferedWriter(osw);
			
			bw.write(p);
				
			bw.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

}
