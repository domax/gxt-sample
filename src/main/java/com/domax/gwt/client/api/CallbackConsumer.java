package com.domax.gwt.client.api;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.finam.slf4jgwt.logging.util.Log;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
public class CallbackConsumer<T> implements MethodCallback<T> {

    public static <T> CallbackConsumer<T> consume(Consumer<T> consumer) {
        return consume((v, e) -> consumer.accept(v));
    }

    public static <T> CallbackConsumer<T> consume(Consumer<T> consumer, Consumer<Throwable> error) {
        return consume((v, e) -> {
            if (e == null) consumer.accept(v);
            else error.accept(e);
        });
    }

    public static <T> CallbackConsumer<T> consume(BiConsumer<T, Throwable> consumer) {
        return new CallbackConsumer<>(consumer);
    }

    private final BiConsumer<T, Throwable> consumer;

    private CallbackConsumer(BiConsumer<T, Throwable> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onFailure(Method method, Throwable exception) {
        Log.w(method.getResponse().getText(), exception);
        consumer.accept(null, exception);
    }

    @Override
    public void onSuccess(Method method, T response) {
        consumer.accept(response, null);
    }
}
