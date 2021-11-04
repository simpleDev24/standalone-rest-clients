package com.simpledev.standalone.rest.clients.feign.jaxrs;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserResponseErrorDecoder implements ErrorDecoder {

	private final ErrorDecoder errorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {
		log.info("In custom feign error decoder. MethodKey: {}", methodKey);
		if (response.status() == 404) {
			log.info("404 response");
		} else {
			log.info("500 response");
		}
		log.info(response.body().toString());
		return errorDecoder.decode(methodKey, response);
	}

}
