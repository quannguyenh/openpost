package com.post;

import static spark.SparkBase.setIpAddress;
import static spark.SparkBase.setPort;
import static spark.SparkBase.staticFileLocation;

/**
 * Main class for PostApp
 */
public class PostApp {
	/**
	 * Main method
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		PostServiceConfig defaultConfig = PostServiceConfig.getDefaultConfig();
		setIpAddress(defaultConfig.getIpAddress());
		setPort(defaultConfig.getPort());
		staticFileLocation("/public");
		new PostResource(new PostService(defaultConfig));
	}
}