package com.post;

/**
 * Post service config
 */
public class PostServiceConfig {
	
	private String db;
	private String host;
	private String ipAddress;
	private int port;
	
	/**
	 * COnstructor
	 * @param db
	 * @param host
	 * @param ipAddress
	 * @param port
	 */
	public PostServiceConfig(String db, String host, String ipAddress, int port) {
		this.db = db;
		this.host = host;
		this.ipAddress = ipAddress;
		this.port = port;
	}

	public String getHost()
	{
		return this.host;
	}
	public String getDb() {
		return this.db;
	}
	public String getIpAddress() {
		return this.ipAddress;
	}
	public int getPort() {
		return this.port;
	}
	
	/**
	 * Gets default config
	 * @return
	 */
	public static PostServiceConfig getDefaultConfig()
	{
		return new PostServiceConfig("postapp", "localhost", "localhost", 8080);
	}
}
