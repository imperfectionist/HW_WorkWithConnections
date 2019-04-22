package org.itstep.service;

import java.util.ArrayList;
import java.util.List;

import org.itstep.connection.Connection;
import org.itstep.util.Randomizer;

public class MultithreadingService extends Thread {

	@Override
	public void run() {

		List<Connection> connections = new ArrayList<Connection>();

		for (int i = 0; i < 10; i++) {
			Connection c = new Connection();
			c.setSessionID(Randomizer.getRandomInt(10000000, 99999999));
			c.setConnectionTime(System.currentTimeMillis());
			c.setIP(Randomizer.getRandomInt(0, 255) + "." + Randomizer.getRandomInt(0, 255) + "."
					+ Randomizer.getRandomInt(0, 255) + "." + Randomizer.getRandomInt(0, 255));
			connections.add(c);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		ConnectionManager cm = new ConnectionManager();

		for (Connection connection : connections) {
			cm.writeToFile(connection, true);
		}
	}

}
