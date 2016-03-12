package ua.pt.snake;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HighscoreManager {
	private static String fileName = "history.txt";
	
	HighscoreManager(){
		String text;
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException {

	}	
	
	
	public static String read() throws FileNotFoundException {
	    //���� ����. ������ ��� ���������� ������
	    StringBuilder sb = new StringBuilder();
	    File file = new File(fileName);
	    try {
	        //������ ��� ������ ����� � �����
	        BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
	        try {
	            //� ����� ��������� ��������� ����
	            String s;
	            while ((s = in.readLine()) != null) {
	                sb.append(s);
	        	    sb.append("\r\n");
	            }
	        } finally {
	            //������� ����
	            in.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
	    return sb.toString();
	}
	
	
	
	public static void write(String text) {
	    //���������� ����
	    File file = new File(fileName);
	    try {
	        //���������, ��� ���� ���� �� ���������� �� ������� ���
	        if(!file.exists()){
	            file.createNewFile();
	        }
	        //PrintWriter ��������� ����������� ������ � ����
	        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
	        try {
	            //���������� ����� � ����
	            out.print(text);
	        } finally {
	            out.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	
	
	public static void update(String newText) throws FileNotFoundException {
	    StringBuilder sb = new StringBuilder();
	    String oldFile = read();
	    sb.append(oldFile);
	    sb.append(newText);
	    write(sb.toString());
	}

}
