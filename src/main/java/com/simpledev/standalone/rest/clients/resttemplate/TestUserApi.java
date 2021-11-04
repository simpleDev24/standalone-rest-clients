package com.simpledev.standalone.rest.clients.resttemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestUserApi {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:9704/users", String.class);
		log.info(response.getBody());

	}

}
