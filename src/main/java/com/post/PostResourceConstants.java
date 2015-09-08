package com.post;

/**
 * Post resource constants
 */
public interface PostResourceConstants {

	static final String API_CONTEXT = "/api/ctx";
	
	static final String API_CONTEXT_ROOT = "/api/ctx" + "/posts";
	
	static final String PARAM_ID = ":id";
	
	static final String API_CONTEXT_ROOT_ID = "/api/ctx" + "/posts/:id";
	
	static final String API_JSON_TYPE = "application/json";
}
