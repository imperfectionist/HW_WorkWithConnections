package org.itstep.connection;

public class Connection {

	private int SessionID;
	private long ConnectionTime;
	private String IP;
	
	public int getSessionID() {
		return SessionID;
	}
	public void setSessionID(int sessionID) {
		SessionID = sessionID;
	}
	public long getConnectionTime() {
		return ConnectionTime;
	}
	public void setConnectionTime(long connectionTime) {
		ConnectionTime = connectionTime;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public Connection(int sessionID, long connectionTime, String iP) {
		SessionID = sessionID;
		ConnectionTime = connectionTime;
		IP = iP;
	}
	
	public Connection() {

	}
	
}
