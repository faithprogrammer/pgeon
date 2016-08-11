package com.lollibond.chat.webservice;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lollibond.chat.repo.RedisRepository;

@RestController
public class OTPWebService {

	@RequestMapping("/notification/getToken")
	public String getAuthenticationToken(@RequestParam String uid){
		UUID uuid=UUID.randomUUID();
		RedisRepository.getInstance().save(uuid.toString(), uid, 30);
		return uuid.toString();
	}
	
	@RequestMapping("/notification/updateStatus")
	public String setUserStatus(@RequestParam String status, @RequestParam String uid){
		RedisRepository.getInstance().save(uid, status);
		return status;
	}
	
	@RequestMapping("/notification/getStatus")
	public String getUserStatus(@RequestParam String uid){
		String status=RedisRepository.getInstance().retrieve(uid);
		return status;
	}
	
}
