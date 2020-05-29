package com.istimeless.timecachecore.operate;

import com.istimeless.timecachecommon.exception.IllegalParamException;
import com.istimeless.timecachecommon.model.value.Value;
import com.istimeless.timecachecore.container.Container;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class Operate {

    public Value put(String key, Value value) {
        return Container.put(key, value);
    }

    public boolean putIfAbsent(String key, Value value) {
        if (Container.containsKey(key)) return false;
        Container.put(key, value);
        return true;
    }

    public Value get(String key) {
        return Container.get(key);
    }

    public Set<String> keys(String regex){
        Set<String> keys = Container.keySet();
        if (StringUtils.isBlank(regex)) {
            return keys;
        }
        return keys.stream().filter(key ->  key.matches(regex)).collect(Collectors.toSet());
    }

    public Set<String> keys(){
       return keys(null);
    }

    public boolean hasKey(String key) {
        return Container.containsKey(key);
    }

    public Value delete(String key) {
        return Container.remove(key);
    }

    public void expire(String key, long timeout) {
        Value value = Container.get(key);
        if (value == null) {
            throw new IllegalParamException("key not exists");
        }
        value.setTimeout(timeout);
    }
}
