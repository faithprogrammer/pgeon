package com.lollibond.chat.data;

import java.util.Arrays;
import java.util.List;

public class GroupPayload {

	String tid;
	String name;
	String participant;
	String admin;
	String participantsUids;
	String adminUids;
	String message;
	String fromuid;
	boolean delete;
	boolean lastreadflag;
	String type;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getParticipants() {
		List<String> participantList = Arrays.asList(getParticipantsUids());
		return participantList;
	}

	public String getParticipantsUids() {
		return participantsUids;
	}

	public void setParticipantsUids(String participantsUids) {
		this.participantsUids = participantsUids;
	}

	public List<String> getAdmins() {
		List<String> participantList = Arrays.asList(getAdminUids());
		return participantList;
	}

	public String getAdminUids() {
		return adminUids;
	}

	public void setAdminUids(String adminUids) {
		this.adminUids = adminUids;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFromuid() {
		return fromuid;
	}

	public void setFromuid(String fromuid) {
		this.fromuid = fromuid;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public boolean isLastreadflag() {
		return lastreadflag;
	}

	public void setLastreadflag(boolean lastreadflag) {
		this.lastreadflag = lastreadflag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	

}
