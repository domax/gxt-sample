package com.domax.gwt.client.api;

import com.domax.gwt.shared.HomeInfo;
import com.google.inject.Singleton;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
@SuppressWarnings("VoidMethodAnnotatedWithGET")
@Singleton
@Path("/home")
public interface HomeService extends RestService {

    @GET
    @Path("/lines")
    @Produces(MediaType.APPLICATION_JSON)
    void getLines(MethodCallback<HomeInfo> callback);
}
