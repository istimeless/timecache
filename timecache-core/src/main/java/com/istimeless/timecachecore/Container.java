package com.istimeless.timecachecore;

import com.istimeless.timecachecommon.model.key.Key;
import com.istimeless.timecachecommon.model.value.Value;

import java.util.concurrent.ConcurrentHashMap;

public class Container {

    private static final ConcurrentHashMap<Key, Value> CONTAINER = new ConcurrentHashMap<>();
}
