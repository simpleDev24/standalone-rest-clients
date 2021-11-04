package com.simpledev.standalone.rest.clients.feign.jaxrs;

import java.util.Collection;

import feign.Feign;
import feign.Logger.Level;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestUserApi {
	
	public static void main(String[] args) {
		UserApi userApi = Feign.builder()
				.logger(new Slf4jLogger())
				.logLevel(Level.FULL)
				.contract(new JAXRSContract())
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.errorDecoder(new UserResponseErrorDecoder())
				.target(UserApi.class, "http://localhost:9704");
		
		log.info("calling get all user");
		Collection<User> userColl = userApi.getAllUser();
		userColl.stream().forEach(user->System.out.println(user.getName()));
		
		log.info("calling getUserById for correct id");
		User user = userApi.getUserById(2L);
		log.info(user.getName());
		
		log.info("calling getUserById for incorrect id");
		try {
			user = userApi.getUserById(7L);
		}catch (Exception e) {
			log.error("Exception occurred",e);
			
		}
		
	}

}
