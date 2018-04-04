package com.domax.gwt.client.api;

import com.google.gwt.core.client.GWT;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.ServiceRoots;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
@Slf4j
@Singleton
public class RestClient {

    static final String SRK_CUSTOM_API = "custom-api";

    public RestClient() {
        String serviceRoot = GWT.getHostPageBaseURL() + "api";
        Defaults.setServiceRoot(serviceRoot);
        ServiceRoots.add(SRK_CUSTOM_API, serviceRoot);
//        ServiceRoots.add("google-places-api", "https://maps.googleapis.com/maps/api");

        log.info("REST service root is '{}'", serviceRoot);
    }
}
