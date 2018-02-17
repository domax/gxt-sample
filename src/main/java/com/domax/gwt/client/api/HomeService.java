package com.domax.gwt.client.api;

import com.domax.gwt.shared.HomeInfo;
import com.google.inject.Singleton;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.domax.gwt.client.api.RestClient.SRK_CUSTOM_API;
import static com.domax.gwt.shared.Constants.API_HOME;
import static com.domax.gwt.shared.Constants.API_LINES;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
@SuppressWarnings("VoidMethodAnnotatedWithGET")
@Options(serviceRootKey = SRK_CUSTOM_API)
@Singleton
@Path(API_HOME)
public interface HomeService extends RestService {

    @GET
    @Path(API_LINES)
    @Produces(MediaType.APPLICATION_JSON)
    void getLines(MethodCallback<HomeInfo> callback);
}
