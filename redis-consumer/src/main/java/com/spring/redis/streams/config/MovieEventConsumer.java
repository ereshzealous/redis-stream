package com.spring.redis.streams.config;

import com.spring.redis.streams.dto.MovieDetails;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created on 21/December/2020 By Author Eresh, Gorantla
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class MovieEventConsumer implements StreamListener<String, ObjectRecord<String, MovieDetails>> {

	private AtomicInteger atomicInteger = new AtomicInteger(0);

	private final ReactiveRedisTemplate<String, String> redisTemplate;

	@Override
	@SneakyThrows
	public void onMessage(ObjectRecord<String, MovieDetails> record) {
		log.info(InetAddress.getLocalHost().getHostName() + " - consumed :" + record.getValue());
		if (record.getValue().getLikes()) {
			this.redisTemplate
					.opsForZSet()
					.incrementScore(record.getValue().getMovie().getName(), "Likes", 1)
					.subscribe();
		}
		if (record.getValue().getDisLike()) {
			this.redisTemplate
					.opsForZSet()
					.incrementScore(record.getValue().getMovie().getName(), "Dislikes", 1)
					.subscribe();
		}
		this.redisTemplate
				.opsForZSet()
				.incrementScore(record.getValue().getMovie().getName(), "Views", 1)
				.subscribe();
		this.redisTemplate
				.opsForZSet()
				.incrementScore(record.getValue().getMovie().getName(), "Rating", record.getValue().getRating())
				.subscribe();
		atomicInteger.incrementAndGet();
	}

	@Scheduled(fixedRate = 10000)
	public void showPublishedEventsSoFar(){
		log.info("Total Consumer :: " + atomicInteger.get());
	}

}