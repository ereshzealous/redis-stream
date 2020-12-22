package com.spring.redis.streams.config;



import com.spring.redis.streams.dto.MovieDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;

/**
 * Created on 21/December/2020 By Author Eresh, Gorantla
 **/
@Configuration
public class RedisConfig {

	@Value("${stream.key}")
	private String streamKey;

	@Autowired
	private StreamListener<String, ObjectRecord<String, MovieDetails>> streamListener;


	@Bean
	public Subscription subscription(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
		StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, ObjectRecord<String, MovieDetails>> options = StreamMessageListenerContainer
				.StreamMessageListenerContainerOptions
				.builder()
				.pollTimeout(Duration.ofSeconds(1))
				.targetType(MovieDetails.class)
				.build();
		StreamMessageListenerContainer<String, ObjectRecord<String, MovieDetails>>  listenerContainer = StreamMessageListenerContainer
				.create(redisConnectionFactory, options);
		Subscription subscription = listenerContainer.receive(
				Consumer.from(streamKey, InetAddress.getLocalHost()
				                                    .getHostName()),
				StreamOffset.create(streamKey, ReadOffset.lastConsumed()),
				streamListener);
		listenerContainer.start();
		return subscription;
	}
}