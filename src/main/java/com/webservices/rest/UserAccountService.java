package com.webservices.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@Path("/users")
public class UserAccountService {
	
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> collection;
	
	
	public void setup(){
		//mongoClient = new MongoClient("localhost", 27017);
		mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		database = mongoClient.getDatabase("User");
		collection = database.getCollection("User_Login");
	}
	

	@GET
	@Produces("application/json")
	public Response getUser(){
		setup();
		Document myDoc = collection.find(Filters.eq("name", "rohit")).first();
		//Document myDoc = collection.find().first();
		System.out.println(myDoc.toJson());
		
		if(myDoc != null)
			return Response.status(200).entity(myDoc.toJson()).build();
		   // return Response.status(200).entity(cursor).build();
		else
			return Response.status(404).entity("NOT FOUND").build();
	}
	
	@POST
	@Path("/addUser")
	//@Consumes("application/json")
	public Response getMsg(@QueryParam("name") String name, @QueryParam("count") int count)
	{	setup();
	 	Document doc = new Document("name", name)
             .append("count", count);
		collection.insertOne(doc);
		
		return Response.status(200).entity(doc.toJson()).build();
	}

}
