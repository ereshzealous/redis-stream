package com.spring.redis.streams.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created on 21/December/2020 By Author Eresh, Gorantla
 **/
@Data
@AllArgsConstructor
public class Movie {
	private Integer id;
	private String name;
	private String productionHouse;
}