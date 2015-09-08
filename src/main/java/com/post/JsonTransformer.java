package com.post;

import com.google.gson.Gson;
import spark.Response;
import spark.ResponseTransformer;

import java.util.HashMap;

/**
 * JsonTransformer
 */
public class JsonTransformer implements ResponseTransformer {
	/** Gson */
	private static final Gson GSON = new Gson();

	/**
	 * Render the model
	 */
	@Override
	public String render(Object model) {
		if (model instanceof Response) {
			return GSON.toJson(new HashMap<>());
		}
		return GSON.toJson(model);
	}
}