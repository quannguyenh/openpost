package com.post;

import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

/**
 * A Post
 */
public class Post {
	/** id */
	private String id;
	/** title */
	private String title;
	/** body */
	private String body;	
	/** title */
	private boolean done;
	/** created on */
	private Date createdOn = new Date();

	public static final String ID_KEY = "_id";
	public static final String TITLE_KEY = "title";
	public static final String BODY_KEY = "body";
	public static final String DONE_KEY = "done";
	public static final String CREATED_KEY = "createdOn";

	/**
	 * Constructor
	 * @param dbObject
	 */
	public Post(BasicDBObject dbObject) {
		this.id = ((ObjectId) dbObject.get("_id")).toString();
		this.title = dbObject.getString(TITLE_KEY);
		this.body = dbObject.getString(BODY_KEY);
		this.done = dbObject.getBoolean(DONE_KEY);
		this.createdOn = dbObject.getDate(CREATED_KEY);
	}

	/**
	 * Gets title
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDone() {
		return this.done;
	}

	/**
	 * Created on
	 * @return
	 */
	public Date getCreatedOn() {
		return this.createdOn;
	}

	/**
	 * get id
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * get Id
	 * @return
	 */
	public String getBody() {
		return body;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
}
