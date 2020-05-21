package com.istimeless.timecachecore.operate;

import com.istimeless.timecachecommon.model.value.Value;
import com.istimeless.timecachecore.container.Container;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Operate {

    static final ConcurrentHashMap<String, Value> container = Container.getContainer();

    public Value put(String key, Value value) {
        return container.put(key, value);
    }

    public boolean putIfAbsent(String key, Value value) {
        if (container.containsKey(key)) return false;
        container.put(key, value);
        return true;
    }

    public Value get(String key) {
        return container.get(key);
    }

    public Set<String> keys(String regex){
        Set<String> keys = container.keySet();
        if (StringUtils.isBlank(regex)) {
            return keys;
        }
        return keys.stream().filter(key ->  key.matches(regex)).collect(Collectors.toSet());
    }

    public boolean hasKey(String key) {
        return container.containsKey(key);
    }

    public Value delete(String key) {
        return container.remove(key);
    }

    public void expire(String key, long timeout) {
        container.get(key).setTimeout(timeout);
    }
}
