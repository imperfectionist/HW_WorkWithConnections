package org.itstep;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.itstep.connection.Connection;
import org.itstep.service.ConnectionManager;
import org.itstep.service.MultithreadingService;

public class ApplicationMain {
	
	private static Logger logger = Logger.getLogger(ApplicationMain.class.getName());
	
	public static void main(String[] args) {
		
		MultithreadingService multithreadingService = new MultithreadingService();
		multithreadingService.run();
		
		ConnectionManager cm = new ConnectionManager();
		
		List<Connection> history = new ArrayList<Connection>(cm.readFromFile());
		
		for (Connection connection : history) {
			logger.info("IP: " + connection.getIP() + " " + "ConnectionTime: " + connection.getConnectionTime() + " " + "SessionID: " + connection.getSessionID() + "\n");
		}
		
		cm.deleteFromFile(1555875693255L, 1555875702258L);
	}

}
