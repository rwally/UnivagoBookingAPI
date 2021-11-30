package com.univago.api.config;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.stereotype.Component;

import com.univago.api.controller.BookingController;
import com.univago.api.controller.v2.BookingControllerV2;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * JerseyConfig
 * 
 * @author rwally
 *
 */
@Component
@ApplicationPath("/univago")
public class JerseyConfig extends ResourceConfig {

	@PostConstruct
	public void init() {
		configureSwagger();
		configEndPoints();
	}

	private void configEndPoints() {
		register(BookingController.class);
		register(BookingControllerV2.class);
	}

	private void configureSwagger() {
		register(ApiListingResource.class);
		register(SwaggerSerializers.class);
		register(WadlResource.class);
		BeanConfig config = new BeanConfig();
		config.setTitle("Univago Booking API");
		config.setHost("localhost:8080");
		config.setVersion("v1");
		config.setContact("Reaz Wally");
		config.setSchemes(new String[] { "http", "https" });
		config.setBasePath("/univago");
		config.setResourcePackage("com.univago.api.controller");
		config.setPrettyPrint(true);
		config.setScan(true);
	}
}
