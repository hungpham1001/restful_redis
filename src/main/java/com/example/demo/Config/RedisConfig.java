package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig{
	@Bean
	public RedisConnectionFactory redis() {
		RedisStandaloneConfiguration redisconfig = new RedisStandaloneConfiguration();
		redisconfig.setHostName("localhost");
		redisconfig.setPort(6379);
		JedisConnectionFactory jedis = new JedisConnectionFactory(redisconfig);
		return jedis;
	}

	@Bean
	public RedisTemplate<String, Object> RedisTemplate(){
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(redis());
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}
}
