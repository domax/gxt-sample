package com.domax.gwt.server.api;

import com.domax.gwt.shared.HomeInfo;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Date;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
@Slf4j
@Path("/home")
public class HomeResource {

    @GET
    @Path("/lines")
    @Produces(MediaType.APPLICATION_JSON)
    public HomeInfo getLines() {
        log.info("Get home lines");
        return new HomeInfo(1L, Arrays.asList("Home page text", "Line 1", "Line 2", "Line 3"), new Date());
    }
}
