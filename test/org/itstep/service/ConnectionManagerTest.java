package org.itstep.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.itstep.connection.Connection;
import org.itstep.util.Randomizer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ConnectionManagerTest {
	
	private static List<Connection> connections = new ArrayList<Connection>();
	private static ConnectionManager connectionManager = new ConnectionManager();
	
	private int sessionID = Randomizer.getRandomInt(10000000, 99999999);
	private long connectionTime = System.currentTimeMillis();
	private String iP = Randomizer.getRandomInt(0, 255) + "." + Randomizer.getRandomInt(0, 255) + "." + Randomizer.getRandomInt(0, 255) + "." + Randomizer.getRandomInt(0, 255);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		connections = connectionManager.readFromFile();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		boolean append = false;
		for (Connection connection : connections) {
			connectionManager.writeToFile(connection, append);
			append = true;
		}	
	}

	@Test
	void testWriteAndReadFile() {
		
		Connection testConnection = new Connection(sessionID, connectionTime, iP);
		connectionManager.writeToFile(testConnection, false);
		
		List<Connection> test = connectionManager.readFromFile();
		assertNotNull(test);
		assertEquals(1, test.size());
		for (Connection connection : test) {
			assertEquals(testConnection.getSessionID(), connection.getSessionID());
			assertEquals(testConnection.getConnectionTime(), connection.getConnectionTime());
			assertEquals(testConnection.getIP(), connection.getIP());
		}
	}


	@Test
	void testDeleteFromFile() {
		
		connectionManager.deleteFromFile(connectionTime, connectionTime);
		
		List<Connection> test = connectionManager.readFromFile();
		assertNotNull(test);
		assertEquals(0, test.size());
	}

}
