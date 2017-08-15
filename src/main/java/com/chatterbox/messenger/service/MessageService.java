package com.chatterbox.messenger.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chatterbox.messenger.database.DBConnection;
import com.chatterbox.messenger.database.Database;
import com.chatterbox.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = Database.getMessages();
	private Connection conn;
	private DBConnection db = new DBConnection();
	
	
	public MessageService(){}
	
	
	// TODO getAllMessages
	public List<Message> getAllMessages(){
		
		try {
			if(conn == null || conn.isClosed())
				conn = db.getDBConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<Message> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Message msg;
		
		try {
			String sql = "SELECT * FROM messages;";;
		
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				msg = new Message((long)rs.getInt("id"), rs.getString("message"), rs.getString("author"));
				
				String createdStr = rs.getString("created");
				Date d = sdf.parse(createdStr);
				
				msg.setCreated(d);
				list.add(msg);
				
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	//TODO getMessagesWithYear
	public List<Message> getMessagesWithYear(int year){
		
		try {
			if(conn == null || conn.isClosed())
				conn = db.getDBConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<Message> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Message msg;
		
		try {
			String sql = "SELECT * FROM messages;";;
		
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				String createdStr = rs.getString("created");
				Date d = sdf.parse(createdStr);
				String yr = year + "";
				
				if(year > 0 && createdStr.startsWith(yr)){
					msg = new Message((long)rs.getInt("id"), rs.getString("message"), rs.getString("author"));
		
					msg.setCreated(d);
					list.add(msg);
				}
					
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	// TODO getMessage
	public Message getMessage(Long id){
		
		try {
			if(conn == null || conn.isClosed())
				conn = db.getDBConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		int iid = Math.toIntExact(id);
		Message msg = null;
		try {
			String sql = "SELECT * FROM messages WHERE id=" + iid + ";";
			Statement stmt;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){				
				msg = new Message((long)rs.getInt("id"), rs.getString("message"), rs.getString("author"));
				Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("created"));
				msg.setCreated(d);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
	
	// TODO addMessage
	public Message addMessage(Message message){
		
		try {
			if(conn == null || conn.isClosed())
				conn = db.getDBConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String msg = message.getMessage();
		String author = message.getAuthor();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String created = sdf.format(new Date());
		
		String cols = "(message, created, author, author_id)";
		 
		
		try {
			String sql = "SELECT id FROM profiles WHERE profile_name='" + author + "';";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int id = rs.getInt("id");
			String vals = "('" + msg + "', '" + created + "', '" + author + "', " + id + ")";
			
			sql = "INSERT INTO messages " + cols + " VALUES " + vals + ";";
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
		
		return message;
	}
	
	
	// TODO updateMessage
	public Message updateMessage(Message message){
		try {
			if(conn == null || conn.isClosed())
				conn = db.getDBConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		int id = Math.toIntExact(message.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String created = sdf.format(new Date());
		
		try {
			String sql = "UPDATE messages SET message='" + message.getMessage() 
				+ "', created='" + created + "' WHERE id=" + id +";";
			
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
		
		return message;
	}
	
	
	// TODO removeMessage
	public void removeMessage(Long id){
		try {
			if(conn == null || conn.isClosed())
				conn = db.getDBConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		int iid = Math.toIntExact(id);
		
		try {
			String sql = "DELETE FROM messages WHERE id=" + iid + ";";
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
