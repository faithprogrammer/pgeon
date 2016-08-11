package com.lollibond.chat.repo;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.lollibond.chat.domain.ThreadList;

public class ThreadListRepository {

	String serverIp = "127.0.0.1";
	String keyspace = "pigeon";
	Cluster cluster = Cluster.builder().addContactPoints(serverIp).withPort(9042).build();
	Session session = cluster.connect(keyspace);

	public boolean save(ThreadList threadList) {

		StringBuilder builder = new StringBuilder();
		builder.append("insert into thread_list (UID,TID,lastread_time,firstmsg_time, hidden, hidefrom_time) values('")
				.append(threadList.getUid()).append("','").append(threadList.getTid()).append("',")
				.append(System.currentTimeMillis()).append(",").append(System.currentTimeMillis()).append(",")
				.append(threadList.isHidden()).append(",").append(System.currentTimeMillis()).append(");");
		session.execute(builder.toString());
		System.out.println(builder.toString());
		return true;
	}

	public ThreadList getDelteSetting(String uid, String tid) {
		StringBuilder builder = new StringBuilder("select hidden,hidefrom_time from thread_list where uid='");
		builder.append(uid).append("' and tid='").append(tid).append("'");
		ThreadList thread = new ThreadList();
		for (Row row : session.execute(builder.toString())) {
			System.out.println(row.toString());
			thread.setHidden(row.getBool("hidden"));
			thread.setHidefrom_date(row.getDate("hidefrom_time"));
			
		}

		return thread;
	}

	public List<ThreadList> getThreadListByUID(String uid, int limit) {
		StringBuilder builder = new StringBuilder("select hidden,hidefrom_time from thread_list where uid='");
		builder.append(uid).append("' limit ").append(limit).append(")");
		List<ThreadList> threadList = new ArrayList<ThreadList>();
		for (Row row : session.execute(builder.toString())) {
			ThreadList thread = new ThreadList();
			System.out.println(row.toString());
			thread.setHidden(row.getBool("hidden"));
			thread.setHidefrom_date(row.getDate("hidefrom_time"));
			threadList.add(thread);
		}

		return threadList;
	}

	public boolean updateRecentRead(String tid) {
		StringBuilder builder = new StringBuilder();
		builder.append("update thread_list set lastread_time=").append(System.currentTimeMillis()).append(" where tid='").append(tid).append("');");
		session.execute(builder.toString());
		System.out.println(builder.toString());
		return true;
	}

	public boolean deleteChatThread(String tid, String uid) {
		StringBuilder builder = new StringBuilder();
		builder.append("update thread_list set hidden=").append(true).append(" AND hiddenfrom_time=").append(System.currentTimeMillis())
		.append(" where tid='").append(tid).append("' and uid='").append(uid).append("');");
		session.execute(builder.toString());
		System.out.println(builder.toString());
		return true;
	}

}
