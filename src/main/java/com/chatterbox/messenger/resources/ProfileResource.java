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
import javax.ws.rs.core.MediaType;

import com.chatterbox.messenger.model.Profile;
import com.chatterbox.messenger.service.ProfileService;

@Path("profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	private ProfileService PService = new ProfileService();
	
	
	@GET
	public List<Profile> getAllProfiles(){
		return PService.getAllProfiles();
	}
	
	@POST
	public Profile addProfile(Profile profile){
		return PService.addProfile(profile);
	}
	
	@PUT
	@Path("{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, 
			Profile profile){
		
		profile.setProfileName(profileName);
		return PService.updateProfile(profile);
		
	}
	
	@DELETE
	@Path("{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName,
			Profile profile){
		
		PService.removeProfile(profileName);
	}
	
	@GET
	@Path("{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName){
		return PService.getProfile(profileName);
	}
}
