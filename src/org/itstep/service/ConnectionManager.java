package org.itstep.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.itstep.connection.Connection;

public class ConnectionManager {
	
	private final static String FILE_PATH = System.getProperty("user.dir") + System.getProperty("file.separator") + "Files" + System.getProperty("file.separator") + "log.txt";

	public ConnectionManager() {
	}
	
	public void writeToFile (Connection Con, boolean append) {
		try (FileWriter fw = new FileWriter(FILE_PATH, append);
				BufferedWriter bw = new BufferedWriter(fw);) {
			bw.write(Con.getConnectionTime() + " " + Con.getSessionID() + " " + Con.getIP());
			bw.newLine();
			
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Connection> readFromFile(){
		
		List<Connection> list = new ArrayList<Connection>();
		
		
		try (FileReader fr= new FileReader(FILE_PATH);
				BufferedReader br = new BufferedReader(fr);) {
			String buffer = br.readLine();
			
			while(buffer != null) {
				Connection c = new Connection();
				int counter = 1;
				
				for (String string : buffer.split(" ")) {
					if(counter == 1) {
						c.setConnectionTime(Long.parseLong(string));
					}
					else if (counter == 2) {
						c.setSessionID(Integer.parseInt(string));
					}
					else if (counter == 3) {
						c.setIP(string);
					}
					else {
						throw new IOException();
					}
					counter++;
				}
				
				list.add(c);
				buffer = br.readLine();
			}
			
			br.close();
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void deleteFromFile (long from, long to) {
		
		ConnectionManager manager = new ConnectionManager();
		List<Connection> history = new ArrayList<Connection>(manager.readFromFile());
		
		try (FileWriter fw = new FileWriter(FILE_PATH, false);
				BufferedWriter bw = new BufferedWriter(fw);) {
			
			bw.write("");
			
			for (Connection connection : history) {
				if (connection.getConnectionTime() >= from && connection.getConnectionTime() <= to) { 
					continue;
				}
				else {
					bw.write(connection.getConnectionTime() + " " + connection.getSessionID() + " " + connection.getIP());
					bw.newLine();
				}
			}
			
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
