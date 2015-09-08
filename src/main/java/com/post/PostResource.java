package com.post;

import static spark.Spark.*;
/**
 * Post resource
 */
public class PostResource implements PostResourceConstants {

	private final PostService postService;
	
	/**
	 * Constructor
	 * @param postService
	 */
	public PostResource(PostService postService) {
		this.postService = postService;
		setupEndpoints();
	}

	/**
	 * Sets up end points
	 */
	private void setupEndpoints() {
		
		post(API_CONTEXT_ROOT, API_JSON_TYPE, (request, response) -> {
			this.postService.createNewPost(request.body());
			response.status(201);
			return response;
		}, new JsonTransformer());

		get(API_CONTEXT_ROOT_ID, API_JSON_TYPE, (request, response)
		-> this.postService.find(request.params(PARAM_ID)), new JsonTransformer());

		get(API_CONTEXT_ROOT, API_JSON_TYPE, (request, response)
		-> this.postService.findAll(), new JsonTransformer());

		put(API_CONTEXT_ROOT_ID, API_JSON_TYPE, (request, response)
		-> this.postService.update(request.params(PARAM_ID), request.body()),
				new JsonTransformer());
	}
}
