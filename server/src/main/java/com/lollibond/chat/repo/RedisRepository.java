package com.lollibond.chat.repo;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.SetArgs;
import com.lambdaworks.redis.api.StatefulRedisConnection;

/*
 * @Kaunain - Immutable redis access wrapper singleton
 */

public class RedisRepository {

	private RedisClient client = RedisClient.create("redis://localhost");
	private StatefulRedisConnection<String, String> connection;
	private static final RedisRepository cacheRepository = new RedisRepository();

	private RedisRepository() {
		connection = client.connect();
	}

	public static RedisRepository getInstance() {
		return cacheRepository;
	}

	public void save(String key, String value) {
		connection.sync().set(key, value);
	}

	public void save(String key, String value, long time) {
		connection.sync().set(key, value, SetArgs.Builder.ex(time));
	}

	public boolean verifyToken(String key, String userId) {
		if (connection.sync().get(key) != null) {
			if (connection.sync().get(key).matches(userId)) {
				connection.sync().del(key);
				return true;
			} else
				return false;

		} else {
			return false;
		}

	}

	public String retrieve(String key) {
		return connection.sync().get(key);
	}

}
