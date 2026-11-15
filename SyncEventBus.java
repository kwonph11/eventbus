package com.tko.eventbus;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SyncEventBus {
    private final Map<Class<?>, Consumer<?>> subscribers;

    public SyncEventBus() {
        subscribers = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public <T> void subscribe(Class<T> clazz, Consumer<T> listener) {
        if (listener == null || clazz == null) return;
        subscribers.merge(clazz, listener, (oldListener, newListener) -> {
            Consumer<T> oldCast = (Consumer<T>) oldListener;
            return oldCast.andThen((Consumer<? super T>) newListener);
        });
    }

    public void publish(Object event) {
        if (event == null) return;

        Consumer<?> listenerChain = subscribers.get(event.getClass());
        if (listenerChain != null) {
            safelyInvoke(listenerChain, event);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> void safelyInvoke(Consumer<T> consumer, Object event) {
        consumer.accept((T) event);
    }
}
