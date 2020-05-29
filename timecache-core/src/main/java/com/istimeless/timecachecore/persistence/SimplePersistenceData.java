package com.istimeless.timecachecore.persistence;

import com.istimeless.timecachecommon.constant.CommonConstant;
import com.istimeless.timecachecommon.model.value.Value;
import com.istimeless.timecachecommon.properties.PropertiesManager;
import com.istimeless.timecachecommon.properties.TimeCacheProperties;
import com.istimeless.timecachecommon.utils.ThreadPoolUtil;
import com.istimeless.timecachecore.container.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SimplePersistenceData implements PersistenceData {

    private static final Logger log = LoggerFactory.getLogger(SimplePersistenceData.class);

    private final TimeCacheProperties properties;

    public SimplePersistenceData () {
        properties = PropertiesManager.getProperties();
    }

    @Override
    public void persistenceForTime() {
        log.info("Persistence for time properties： {}", properties.isPersistenceTime());
        if (properties.isPersistenceTime()) {
            log.info("Persistence for time path properties： {}", properties.getPersistenceTimePath());
            log.info("Persistence for time interval properties： {} milliseconds", properties.getPersistenceTimeInterval());
            ThreadPoolUtil.getScheduledExecutorService()
                    .scheduleAtFixedRate(() -> {
                        log.info("Starting persistence for time");
                        long start = System.currentTimeMillis();
                        File persistence = new File(properties.getPersistenceTimePath());
                        File backup = new File(properties.getPersistenceTimePath() + CommonConstant.BACKUP);
                        if (persistence.exists()) {
                            while (!persistence.renameTo(backup));
                        }
                        try (FileOutputStream fos = new FileOutputStream(persistence);
                             ObjectOutputStream oos = new ObjectOutputStream(fos)){
                            oos.writeObject(Container.getContainer());
                            oos.flush();
                        } catch (IOException e) {
                            log.error("Persistence for time error: {}", e.getMessage(), e);
                        }
                        while (!backup.delete());
                        long end = System.currentTimeMillis();
                        log.info("Persistence for time in {} seconds", (end - start) / 1000.0);
                    }, 0L, properties.getPersistenceTimeInterval(), TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void persistenceForData(Map<String, Value> data) {
        //TODO
        log.info("Persistence for data properties： {}", properties.isPersistenceData());
        if (properties.isPersistenceTime()) {
            log.info("Persistence for data path properties： {}", properties.getPersistenceDataPath());
            File persistence = new File(properties.getPersistenceDataPath());
            try (FileOutputStream fos = new FileOutputStream(persistence);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)){
                long pos;
                if(persistence.exists()){
                    pos = fos.getChannel().position()-4;//追加的时候去掉头部aced 0005
                    fos.getChannel().truncate(pos);
                }
                oos.writeObject(data);
                oos.flush();
            } catch (IOException e) {
                log.error("Persistence for time error: {}", e.getMessage(), e);
            }
        }
    }
}
