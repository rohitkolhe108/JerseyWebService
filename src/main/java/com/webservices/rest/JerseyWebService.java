package com.webservices.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/show-on-screen")
public class JerseyWebService
{
	@GET
	@Path("/{message}")
	public Response getMsg(@PathParam("message") String msg)
	{
		String output = "Message requested : " + msg;
		//Simply return the parameter passed as message
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/test")
	public Response testUrl()
	{
		return Response.status(200).entity("hi").build();
	}
	
	@GET
	@Path("/add")
	public Response getAddition(
		@QueryParam("value1") int value1,
		@QueryParam("value2") int value2) {

		return Response
		   .status(200)
		   .entity("getAddition is called, total : " + (value1+value2)).build();

	}
	
	

}
