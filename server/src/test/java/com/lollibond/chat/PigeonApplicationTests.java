package com.lollibond.chat;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lollibond.chat.repo.RedisRepository;
import com.lollibond.chat.server.PigeonApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PigeonApplication.class)
public class PigeonApplicationTests {

	String sampleuid = "1234";
	String token = UUID.randomUUID().toString();

/*	@Test
	public void verifyAuthentication() throws InterruptedException {

		testOTP();
		verifyAuthentication2();
		verifyAuthentication2();
	}*/

	@Test
	public void testOTP() {
		RedisRepository.getInstance().save(token, sampleuid, 30);
	}

	@Test
	public void verifyAuthentication2() {

		assertTrue(RedisRepository.getInstance().verifyToken(token, sampleuid));
	}

}