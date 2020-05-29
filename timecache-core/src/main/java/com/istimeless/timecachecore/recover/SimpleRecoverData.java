package com.istimeless.timecachecore.recover;

import com.istimeless.timecachecommon.model.value.Value;
import com.istimeless.timecachecommon.properties.PropertiesManager;
import com.istimeless.timecachecommon.properties.TimeCacheProperties;
import com.istimeless.timecachecore.container.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public class SimpleRecoverData implements RecoverData {

    private static final Logger log = LoggerFactory.getLogger(SimpleRecoverData.class);

    private final TimeCacheProperties properties;

    public SimpleRecoverData () {
        properties = PropertiesManager.getProperties();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void recoverForTime() {
        log.info("Recover data properties： {}", properties.isRecoverTime());
        if (properties.isRecoverTime()) {
            log.info("Starting recover data");
            long start = System.currentTimeMillis();
            File persistence = new File(properties.getPersistenceTimePath());
            if (!persistence.exists()) {
                return;
            }
            try (FileInputStream fis = new FileInputStream(persistence);
                 ObjectInputStream ois = new ObjectInputStream(fis)){
                Object object = ois.readObject();
                if (object != null) {
                    Map<String, Value> recoverData = (Map<String, Value>) object;
                    Container.putAll(recoverData);
                }
            } catch (IOException | ClassNotFoundException e) {
                log.error("Recover data error: {}", e.getMessage(), e);
            }
            long end = System.currentTimeMillis();
            log.info("Recover data in {} seconds", (end - start) / 1000.0);
        }
    }

    @Override
    public void recoverForData() {
        //TODO
    }
}
