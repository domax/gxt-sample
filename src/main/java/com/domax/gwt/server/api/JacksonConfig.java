package com.domax.gwt.server.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/** @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a> */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonConfig implements ContextResolver<ObjectMapper> {

  private static final LocalDateSerializer LDS =
      new LocalDateSerializer(null) {
        @Override
        protected boolean useTimestamp(SerializerProvider provider) {
          return true;
        }
      };

  private static final LocalDateDeserializer LDD = new LocalDateDeserializer(null);

  private final ObjectMapper objectMapper;

  public JacksonConfig() {
    objectMapper =
        new ObjectMapper()
            .registerModule(
                new JavaTimeModule()
                    .addSerializer(LocalDate.class, LDS)
                    .addDeserializer(LocalDate.class, LDD))
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }

  @Override
  public ObjectMapper getContext(Class<?> objectType) {
    return objectMapper;
  }
}
