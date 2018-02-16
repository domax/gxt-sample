package com.domax.gwt.client.api;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.finam.slf4jgwt.logging.util.Log;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
public class CallbackConsumer<T> implements MethodCallback<T> {

    public static <T> CallbackConsumer<T> consume(Consumer<Optional<T>> consumer) {
        return new CallbackConsumer<>(consumer);
    }

    private final Consumer<Optional<T>> consumer;

    private CallbackConsumer(Consumer<Optional<T>> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onFailure(Method method, Throwable exception) {
        Log.w(method.getResponse().getText(), exception);
        consumer.accept(Optional.empty());
    }

    @Override
    public void onSuccess(Method method, T response) {
        consumer.accept(Optional.ofNullable(response));
    }
}
