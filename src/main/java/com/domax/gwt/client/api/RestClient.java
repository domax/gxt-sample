package com.domax.gwt.client.api;

import com.google.gwt.core.client.GWT;
import com.google.inject.Singleton;
import org.fusesource.restygwt.client.Defaults;
import ru.finam.slf4jgwt.logging.util.Log;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
@Singleton
public class RestClient {

    public RestClient() {
        String serviceRoot = GWT.getHostPageBaseURL() + "api/";
        Defaults.setServiceRoot(serviceRoot);
        Log.i("REST service root is '{}'", serviceRoot);
    }
}
