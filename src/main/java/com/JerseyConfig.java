package com;

import com.controller.CORSResponseFilter;
import com.controller.UserController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;


@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(CORSResponseFilter.class);
        register(UserController.class);
       

    }

}
