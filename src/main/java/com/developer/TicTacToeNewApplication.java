package com.developer;

import com.developer.model.TicTacToeGameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicTacToeNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicTacToeNewApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(TicTacToeGameRepository ticTacToeGameRepository)
	{
		return args -> {};
	}

}
