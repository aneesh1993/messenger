package com.chatterbox.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.chatterbox.messenger.model.Message;
import com.chatterbox.messenger.service.MessageService;


@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	private MessageService MService = new MessageService();
	
	@GET
	public List<Message> getMessages(@QueryParam("year") int year){
		if(year > 0)
			return MService.getMessagesWithYear(year);
		else
			return MService.getAllMessages();
	}
	
	@POST
	public Message addMessage(Message message){
		
		return MService.addMessage(message);
	}
	
	@PUT
	@Path("{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, 
			Message message){
		
		message.setId(id);
		return MService.updateMessage(message);
	}
	
	
	@DELETE
	@Path("{messageId}")
	public void deleteMessage(@PathParam("messageId") long id){
		MService.removeMessage(id);
	}
	
	
	
	@GET
	@Path("{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId){
		return MService.getMessage(messageId);
	
	}
	
}
