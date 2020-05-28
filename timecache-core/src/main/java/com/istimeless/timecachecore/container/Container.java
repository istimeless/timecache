package com.istimeless.timecachecore.container;

import com.istimeless.timecachecommon.model.value.Value;
import com.istimeless.timecachecommon.properties.PropertiesManager;
import com.istimeless.timecachecommon.properties.TimeCacheProperties;
import com.istimeless.timecachecommon.utils.ThreadPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class Container {

    private static final Logger log = LoggerFactory.getLogger(Container.class);

    private static final TimeCacheProperties properties;

    private static final ConcurrentHashMap<String, Value> KEY_VALUE_MAP = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, Value> getContainer() {
        return KEY_VALUE_MAP;
    }

    static {
        properties = PropertiesManager.getProperties();
    }

    public static void startCountDown() {
        ThreadPoolUtil.getScheduledExecutorService()
                .scheduleAtFixedRate(Container::countDown, 0L, 1L, TimeUnit.MILLISECONDS);
    }

    public static void startRecover() {
        log.info("Recover data properties： {}", properties.isRecover());
        if (properties.isRecover()) {
            log.info("Starting recover data");
            long start = System.currentTimeMillis();
            Container.recover();
            long end = System.currentTimeMillis();
            log.info("Recover data in {} seconds", (end - start) / 1000.0);
        }
    }

    public static void startPersistence() {
        log.info("Persistence data properties： {}", properties.isPersistence());
        if (properties.isPersistence()) {
            log.info("Persistence data path properties： {}", properties.getPersistencePath());
            log.info("Persistence data interval properties： {} milliseconds", properties.getPersistenceInterval());
            ThreadPoolUtil.getScheduledExecutorService()
                    .scheduleAtFixedRate(Container::persistence, 0L,
                            properties.getPersistenceInterval(), TimeUnit.MILLISECONDS);
        }
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

    @SuppressWarnings("unchecked")
    private static void recover() {
        File persistence = new File(properties.getPersistencePath());
        if (!persistence.exists()) {
            return;
        }
        try (FileInputStream fis = new FileInputStream(persistence);
             ObjectInputStream ois = new ObjectInputStream(fis)){
            ConcurrentHashMap<String, Value> recoverData = (ConcurrentHashMap<String, Value>)ois.readObject();
            KEY_VALUE_MAP.putAll(recoverData);
        } catch (IOException | ClassNotFoundException e) {
            log.error("Recover data error: {}", e.getMessage(), e);
        }
    }

    private static void persistence() {
        File persistence = new File(properties.getPersistencePath());
        try (FileOutputStream fos = new FileOutputStream(persistence);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(KEY_VALUE_MAP);
            oos.flush();
        } catch (IOException e) {
            log.error("Persistence data error: {}", e.getMessage(), e);
        }
    }
}
