package br.com.tcc.utils;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class FreeResourceConfig extends ResourceConfig {

	public FreeResourceConfig() {
		packages("br.com.tcc.service");
	}	

}
