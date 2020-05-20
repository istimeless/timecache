package com.istimeless.timecachecore.container;

import com.istimeless.timecachecommon.model.key.Key;
import com.istimeless.timecachecommon.model.value.Value;

import java.util.concurrent.ConcurrentHashMap;

public class Container {

    private Container() {

    }

    private static final ConcurrentHashMap<Key, Value> KEY_VALUE_MAP = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<Key, Value> getContainer() {
        return KEY_VALUE_MAP;
    }
}
