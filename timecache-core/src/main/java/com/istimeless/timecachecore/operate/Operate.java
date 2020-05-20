package com.istimeless.timecachecore.operate;

import com.istimeless.timecachecommon.model.key.Key;
import com.istimeless.timecachecore.container.Container;

import java.util.Set;
import java.util.stream.Collectors;

public interface Operate {

    default Set<Key> keys(String regex){
        Set<Key> keys = Container.getContainer().keySet();
        if (regex == null || regex.length() == 0) {
            return keys;
        }
        return keys.stream().filter(key ->  key.getItem().matches(regex)).collect(Collectors.toSet());
    }

    default boolean hasKey(Key key) {
        return Container.getContainer().containsKey(key);
    }
}
