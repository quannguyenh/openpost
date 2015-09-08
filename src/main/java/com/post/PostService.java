package com.post;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * PostService
 */
public class PostService {

	/** the db */
	private final DB db;

	/** the dbcollection */
	private final DBCollection postCollection;

	
	/**
	 * Constructor
	 * 
	 * @param db
	 */
	public PostService(DB db, PostServiceConfig postServiceConfig) {
		this.db = db;
		this.postCollection = db.getCollection(postServiceConfig.getDb());
	}
	
	/**
	 * Constructor
	 * 
	 * @param db
	 * @throws UnknownHostException 
	 */
	public PostService(PostServiceConfig postServiceConfig) throws UnknownHostException {
		this(mongo(postServiceConfig), postServiceConfig);
	}

	private static DB mongo(PostServiceConfig config) throws UnknownHostException{
		if (config != null) {
			MongoClient mongoClient = new MongoClient(config.getHost());
			return mongoClient.getDB(config.getDb());
		}
		return null;
	}
	
	/**
	 * Finds all posts
	 * 
	 * @return
	 */
	public List<Post> findAll() {
		List<Post> posts = new ArrayList<>();
		DBCursor dbObjects = this.postCollection.find();
		while (dbObjects.hasNext()) {
			DBObject dbObject = dbObjects.next();
			posts.add(new Post((BasicDBObject) dbObject));
		}
		return posts;
	}

	/**
	 * 
	 * @param body
	 */
	public void createNewPost(String body) {
		Post aPost = new Gson().fromJson(body, Post.class);
		BasicDBObject dbObject = new BasicDBObject(Post.TITLE_KEY,
				aPost.getTitle()).append(Post.DONE_KEY, aPost.isDone()).append(
				Post.CREATED_KEY, new Date());
		this.postCollection.insert(dbObject);
	}

	/**
	 * Finds post with specified id
	 * @param id
	 * @return
	 */
	public Post find(String id) {
		return new Post(
				(BasicDBObject) this.postCollection.findOne(new BasicDBObject(
						Post.ID_KEY, new ObjectId(id))));
	}

	/**
	 * Update the post with new body
	 * 
	 * @param postId
	 * @param body
	 * @return
	 */
	public Post update(String postId, String body) {
		Post aPost = new Gson().fromJson(body, Post.class);
		this.postCollection.update(new BasicDBObject(Post.ID_KEY, new ObjectId(
				postId)), new BasicDBObject("$set", new BasicDBObject(
				Post.DONE_KEY, aPost.isDone())));
		return this.find(postId);
	}

	/**
	 * Update done
	 * @param aPost
	 * @return
	 */
	public Post updateDone(Post aPost) {
		this.postCollection.update(new BasicDBObject(Post.ID_KEY, new ObjectId(
				aPost.getId())), new BasicDBObject("$set", new BasicDBObject(
				Post.DONE_KEY, aPost.isDone())));
		return this.find(aPost.getId());
	}
}
