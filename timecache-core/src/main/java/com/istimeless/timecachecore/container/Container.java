package com.istimeless.timecachecore.container;

import com.istimeless.timecachecommon.model.value.Value;
import com.istimeless.timecachecommon.utils.ThreadPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class Container {

    private static final Logger log = LoggerFactory.getLogger(Container.class);

    private static final ConcurrentHashMap<String, Value> KEY_VALUE_MAP = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, Value> getContainer() {
        return KEY_VALUE_MAP;
    }

    public static void startCountDown() {
        ThreadPoolUtil.getScheduledExecutorService()
                .scheduleAtFixedRate(Container::countDown, 0L, 1L, TimeUnit.MILLISECONDS);
    }

    private static void countDown() {
        KEY_VALUE_MAP.forEach((k, v) -> {
            long timeout = v.getTimeout();
            if (timeout != -1L) {
                timeout--;
                v.setTimeout(timeout);
                if (timeout == 0) {
                    KEY_VALUE_MAP.remove(k);
                }
            }
        });
    }
}
