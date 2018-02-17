package com.domax.gwt.client.api;

import com.google.gwt.core.client.GWT;
import com.google.inject.Singleton;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.ServiceRoots;
import ru.finam.slf4jgwt.logging.util.Log;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
@Singleton
public class RestClient {

    static final String SRK_CUSTOM_API = "custom-api";

    public RestClient() {
        String serviceRoot = GWT.getHostPageBaseURL() + "api";
        Defaults.setServiceRoot(serviceRoot);
        ServiceRoots.add(SRK_CUSTOM_API, serviceRoot);
//        ServiceRoots.add("google-places-api", "https://maps.googleapis.com/maps/api");

        Log.i("REST service root is '{}'", serviceRoot);
    }
}
