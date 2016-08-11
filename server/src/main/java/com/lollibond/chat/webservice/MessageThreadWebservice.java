package com.lollibond.chat.webservice;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lollibond.chat.repo.MessageThreadRepository;

@RestController
@RequestMapping("/messaging")
public class MessageThreadWebservice {

	@RequestMapping("/getThread")
	public String getMessageThreadPaginated(@RequestParam String fromUser, @RequestParam String toUser,
			@RequestParam String timeStamp) {

		MessageThreadRepository threadRepository = new MessageThreadRepository();
		String u1;
		String u2;
		if (Integer.parseInt(fromUser) > Integer.parseInt(toUser)) {
			u1 = toUser;
			u2 = fromUser;
		} else {
			u1 = fromUser;
			u2 = toUser;
		}
		List<String> list = threadRepository.findByUIandU2Paginated(u1, u2, timeStamp);

		return list.toString();
	}

}
