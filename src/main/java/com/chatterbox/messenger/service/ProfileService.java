package com.chatterbox.messenger.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chatterbox.messenger.database.DBConnection;
import com.chatterbox.messenger.model.Profile;

public class ProfileService {
	private Connection conn;
	private DBConnection db = new DBConnection();
	
	public ProfileService(){}
	
	
	
	public List<Profile> getAllProfiles(){
		
		try {
			if(conn == null || conn.isClosed())
				conn = db.getDBConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<Profile> list = new ArrayList<>();
		
		//int id, String profileName, String firstName, String lastName
		try {
			String sql = "SELECT * FROM profiles;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Profile temp;
			while(rs.next()){
				temp = new Profile(rs.getInt("id"), rs.getString("profile_name"), 
						rs.getString("first_name"), rs.getString("last_name"));
				list.add(temp);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	
	public Profile getProfile(String profileName){
		Profile p = null;
		try {
			if(conn == null || conn.isClosed())
				conn = db.getDBConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			String sql = "SELECT * FROM profiles WHERE "
					+ "profile_name='" + profileName + "';";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				p = new Profile(rs.getInt("id"), rs.getString("profile_name"), 
						rs.getString("first_name"), rs.getString("last_name"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return p;
	}
	
	
	public Profile addProfile(Profile profile){
		try {
			if(conn == null || conn.isClosed())
				conn = db.getDBConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String profileName = profile.getProfileName();
		String firstName = profile.getFirstName();
		String lastName = profile.getLastName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String created = sdf.format(new Date());
		String pass = profileName;
		
		String cols = "(profile_name, first_name, last_name, created, password)";
		String vals = "('" + profileName + "', '" + firstName + "', '" + lastName + "', '" + created + "', '" + pass + "')";
		
		try {
			String sql = "INSERT INTO profiles " + cols + " VALUES " + vals + ";";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return profile;
		
	}
	
	public Profile updateProfile(Profile profile){

		try {
			if(conn == null || conn.isClosed())
				conn = db.getDBConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		//pn,fn,ln,cr,pass
		String profileName = profile.getProfileName();
		String firstName = profile.getFirstName();
		String lastName = profile.getLastName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String created = sdf.format(new Date());
		String pass = profileName;
				
	
		try {
			String sql = "UPDATE profiles SET profile_name='" + profileName 
					+ "', first_name='" + firstName + "', last_name='" + lastName 
					+"', created='" + created + "', password='" + pass 
					+ "' WHERE profile_name='" + profileName + "';";
			System.out.println(sql);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return profile;			
	}
	
	
	public void removeProfile(String profileName){
		
		try {
			if(conn == null || conn.isClosed())
				conn = db.getDBConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			String sql = "DELETE FROM profiles WHERE profile_name='" + profileName + "';";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
