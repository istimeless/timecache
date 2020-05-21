package com.istimeless.timecachecore.container;

import com.istimeless.timecachecommon.model.value.Value;
import com.istimeless.timecachecommon.utils.ThreadPoolUtil;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class Container {

    private static final ConcurrentHashMap<String, Value> KEY_VALUE_MAP = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, Value> getContainer() {
        return KEY_VALUE_MAP;
    }

    private Container() {
        ThreadPoolUtil.getScheduledExecutorService().schedule(this::countDown, 1L, TimeUnit.MILLISECONDS);
    }

    private void countDown() {
        KEY_VALUE_MAP.forEach((k, v) -> {
            long timeout = v.getTimeout();
            if (timeout != -1L) {
                timeout--;
                if (timeout == 0) {
                    KEY_VALUE_MAP.remove(k);
                }
            }
        });
    }
}
