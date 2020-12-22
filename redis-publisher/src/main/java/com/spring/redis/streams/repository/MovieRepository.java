package com.spring.redis.streams.repository;

import com.spring.redis.streams.dto.Movie;
import com.spring.redis.streams.dto.MovieDetails;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on 21/December/2020 By Author Eresh, Gorantla
 **/
@Repository
public class MovieRepository {

	public static final List<Movie> MOVIE_LIST = Stream.of(
			new Movie(1, "Avengers End Game", "Marvel Studios"),
			new Movie(2, "Avengers Infinity War", "Marvel Studios"),
			new Movie(3, "Dark Knight", "Warner Bros"),
			new Movie(4, "Pulp Fiction", "MiraMax"),
			new Movie(5, "Fight Club", "Warner Bros"),
			new Movie(6, "Good Fellas", "Warner Bros"),
			new Movie(7, "Seven", "Warner Bros"),
			new Movie(8, "Cast Away", "ImageMovers Playtone"),
			new Movie(9, "Forest Gump", "The Tisch Company"),
			new Movie(10, "King Kong", "Warner Bros"),
			new Movie(11, "The Silence Of Lambs", "Strong Heart Productions"),
			new Movie(12, "Usual Suspects", "PolyGram Filmed Entertainment"),
			new Movie(13, "Green Mile", "Castle Rock Entertainment"),
			new Movie(14, "No Country For Old Men", "Scott Rudin Productions"),
			new Movie(15, "Train to Busan", "Next Entertainment World"),
			new Movie(16, "Parasite", "Barunson E&A"),
			new Movie(17, "Whiplash", "Sony Pictures"),
			new Movie(18, "The Prestige", "Warner Bros"),
			new Movie(19, "Joker", "Warner Bros"),
			new Movie(20, "Old Boy", "Show East"),
			new Movie(21, "I Saw Devil", "Peppermint and company"),
			new Movie(22, "The Perfect Murder", "Warner Bros"),
			new Movie(23, "The Chaser", "Snow Box"),
			new Movie(24, "Goodwill Hunting", "Be Gentlemen"),
			new Movie(25, "Snatch", "Columbia Pictures")
	).collect(Collectors.toList());

	public MovieDetails getRandomMovie() {
		Integer index = ThreadLocalRandom.current().nextInt(0, 25);
		Movie movie = MOVIE_LIST.get(index);
		Random random = new Random();
		Integer value = random.ints(0, 1000).findFirst().getAsInt();
		Double rating = random.doubles(1.0, 10.0).findFirst().getAsDouble();
		return new MovieDetails(movie, value % 2 == 0, value % 2 == 1,  rating);
	}


}