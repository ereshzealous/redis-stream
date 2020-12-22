package com.spring.redis.streams.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 21/December/2020 By Author Eresh, Gorantla
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetails {
	private Movie movie;
	private Boolean likes = false;
	private Boolean disLike = false;
	private Double rating;
}