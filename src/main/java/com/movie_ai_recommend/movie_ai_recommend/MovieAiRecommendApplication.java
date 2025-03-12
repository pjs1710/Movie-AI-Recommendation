package com.movie_ai_recommend.movie_ai_recommend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MovieAiRecommendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieAiRecommendApplication.class, args);
	}

}
