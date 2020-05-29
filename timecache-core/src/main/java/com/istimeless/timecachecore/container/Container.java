package com.istimeless.timecachecore.container;

import com.istimeless.timecachecommon.model.value.Value;
import com.istimeless.timecachecommon.utils.ThreadPoolUtil;
import com.istimeless.timecachecore.persistence.PersistenceData;
import com.istimeless.timecachecore.persistence.SimplePersistenceData;
import com.istimeless.timecachecore.recover.RecoverData;
import com.istimeless.timecachecore.recover.SimpleRecoverData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class Container {

    private static final Logger log = LoggerFactory.getLogger(Container.class);

    private static final ConcurrentHashMap<String, Value> KEY_VALUE_MAP = new ConcurrentHashMap<>();

    public static Map<String, Value> getContainer() {
        return Collections.unmodifiableMap(KEY_VALUE_MAP);
    }

    static {
        ThreadPoolUtil.getScheduledExecutorService()
                .scheduleAtFixedRate(Container::countDown, 0L, 1L, TimeUnit.MILLISECONDS);
        RecoverData recoverData = new SimpleRecoverData();
        recoverData.recoverForTime();
        PersistenceData persistenceData = new SimplePersistenceData();
        persistenceData.persistenceForTime();
    }

    private Container () {}

    public static Value put (String key, Value value) {
        return KEY_VALUE_MAP.put(key, value);
    }

    public static void putAll (Map<String, Value> map) {
        KEY_VALUE_MAP.putAll(map);
    }

    public static Value get (String key) {
        return KEY_VALUE_MAP.get(key);
    }

    public static Set<String> keySet() {
        return KEY_VALUE_MAP.keySet();
    }

    public static boolean containsKey(String key) {
        return KEY_VALUE_MAP.containsKey(key);
    }

    public static Value remove(String key) {
        return KEY_VALUE_MAP.remove(key);
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

    public static void init () {
        log.info("Init container success");
    }
}
