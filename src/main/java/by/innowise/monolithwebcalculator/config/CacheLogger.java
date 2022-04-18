package by.innowise.monolithwebcalculator.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.springframework.stereotype.Component;

@Component
public class CacheLogger implements CacheEventListener<Object, Object> {
    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        System.out.println(cacheEvent.getType() + " : " + cacheEvent.getNewValue());
    }
}
