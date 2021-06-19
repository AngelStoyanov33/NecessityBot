package com.angelstoyanov.necessitybot;

import com.angelstoyanov.necessitybot.listeners.SayListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class NecessityBotApplication {

	@Autowired
	private Environment env;

	@Autowired
	private SayListener sayListener;

	@Bean
	@ConfigurationProperties(value = "discord-api")
	public DiscordApi discordApi(){
		String token = env.getProperty("token");
		DiscordApi api = new DiscordApiBuilder().setToken(token)
				.setAllNonPrivilegedIntents()
				.login()
				.join();
		api.addMessageCreateListener(sayListener);
		return api;
	}

	public static void main(String[] args) {
		SpringApplication.run(NecessityBotApplication.class, args);
	}



}
