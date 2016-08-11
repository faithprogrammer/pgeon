package com.lollibond.chat.repo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.lollibond.chat.domain.ChatMessage;

public class MessageThreadRepository {

	String serverIp = "127.0.0.1";
	String keyspace = "pigeon";
	Cluster cluster = Cluster.builder().addContactPoints(serverIp).withPort(9042).build();
	Session session = cluster.connect(keyspace);

	public List<String> findByU1AndU2(String fromUser, String toUser) {

		String u1;
		String u2;
		if (Integer.parseInt(fromUser) > Integer.parseInt(toUser)) {
			u1 = toUser;
			u2 = fromUser;
		} else {
			u1 = fromUser;
			u2 = toUser;
		}
		List<String> messageThread = new ArrayList<String>();
		StringBuilder builder = new StringBuilder("Select * from message_thread_121 where u1='");
		builder.append(u1).append("' and u2='").append(u2).append("'");

		for (Row row : session.execute(builder.toString())) {
			System.out.println(row.toString());
			messageThread.add(row.toString());
		}
		cluster.close();
		session.close();
		return messageThread;

	}

	public List<String> findByUIandU2Paginated(String fromUser, String toUser, String timeStamp) {

		String u1;
		String u2;
		if (Integer.parseInt(fromUser) > Integer.parseInt(toUser)) {
			u1 = toUser;
			u2 = fromUser;
		} else {
			u1 = fromUser;
			u2 = toUser;
		}
		long ts = Long.parseLong(timeStamp);

		List<String> messageThread = new ArrayList<String>();

		StringBuilder builder = new StringBuilder("Select * from message_thread_121 where u1='");

		builder.append(u1).append("' and u2='").append(u2).append("'").append(" and time < " + ts + " LIMIT 10");
		// select * from message_thread_121 where u1='u1' and u2='u2' and time <
		// toTimestamp(now()) LIMIT 10;

		/*
		 * Statement stmt = new SimpleStatement(builder.toString());
		 * stmt.setFetchSize(10);
		 */

		ResultSet rs = session.execute(builder.toString());
		Iterator<Row> iter = rs.iterator();
		while (iter.hasNext()) {
			Row row = iter.next();
			System.out.println(row);
			messageThread.add(row.toString());
		}

		return messageThread;
	}

	public boolean save(ChatMessage chatMessage) {
		Cluster cluster = Cluster.builder().addContactPoints(serverIp).withPort(9042).build();

		Session session = cluster.connect(keyspace);

		StringBuilder builder = new StringBuilder();
		builder.append("insert into message_thread_121 (u1,u2,time,body,fromuser) values('").append(chatMessage.getU1())
				.append("','").append(chatMessage.getU2()).append("',").append(System.currentTimeMillis()).append(",'")
				.append(chatMessage.getBody()).append("','").append(chatMessage.getFromUser()).append("');");
		session.execute(builder.toString());
		System.out.println(builder.toString());

		cluster.close();
		session.close();

		return true;
	}
	
	
	/*public boolean save(){
		
	}*/
	
	public List<String> getParticipantList(String TID){
		
		return null;
	}

}
