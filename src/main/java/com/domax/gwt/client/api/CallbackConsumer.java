package com.domax.gwt.client.api;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

/** @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a> */
@Slf4j
public class CallbackConsumer<T> implements MethodCallback<T> {

  public static <T> CallbackConsumer<T> consume(Consumer<Optional<T>> consumer) {
    return consume((v, e) -> consumer.accept(v));
  }

  public static <T> CallbackConsumer<T> consume(
      Consumer<Optional<T>> consumer, Consumer<Throwable> error) {
    return consume(
        (v, e) -> {
          if (e == null) consumer.accept(v);
          else error.accept(e);
        });
  }

  public static <T> CallbackConsumer<T> consume(BiConsumer<Optional<T>, Throwable> consumer) {
    return new CallbackConsumer<>(consumer);
  }

  private final BiConsumer<Optional<T>, Throwable> consumer;

  private CallbackConsumer(BiConsumer<Optional<T>, Throwable> consumer) {
    this.consumer = consumer;
  }

  @Override
  public void onFailure(Method method, Throwable exception) {
    log.error(method.getResponse().getText());
    consumer.accept(Optional.empty(), exception);
  }

  @Override
  public void onSuccess(Method method, T response) {
    log.debug("Consuming response: " + response);
    consumer.accept(Optional.ofNullable(response), null);
  }
}
