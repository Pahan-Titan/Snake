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
	    //Этот спец. объект для построения строки
	    StringBuilder sb = new StringBuilder();
	    File file = new File(fileName);
	    try {
	        //Объект для чтения файла в буфер
	        BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
	        try {
	            //В цикле построчно считываем файл
	            String s;
	            while ((s = in.readLine()) != null) {
	                sb.append(s);
	        	    sb.append("\r\n");
	            }
	        } finally {
	            //закрыть файл
	            in.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
	    return sb.toString();
	}
	
	
	
	public static void write(String text) {
	    //Определяем файл
	    File file = new File(fileName);
	    try {
	        //проверяем, что если файл не существует то создаем его
	        if(!file.exists()){
	            file.createNewFile();
	        }
	        //PrintWriter обеспечит возможности записи в файл
	        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
	        try {
	            //Записываем текст у файл
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
