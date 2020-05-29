package com.istimeless.timecachecommon.properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;

public class PropertiesManager {

    private static final String CONFIG_PATH = "config.path";

    private static final PropertiesConfiguration properties = new PropertiesConfiguration();

    private static final TimeCacheProperties timeCacheProperties = new TimeCacheProperties();

    static {
        SystemConfiguration sysConfig = new SystemConfiguration();
        String configPath = sysConfig.getString(CONFIG_PATH);
        if (StringUtils.isBlank(configPath)) {
            URL resource = Thread.currentThread().getContextClassLoader().getResource("timecache.properties");
            if (resource == null) {
                throw new RuntimeException("can not find timecache.properties");
            }
            configPath = resource.getFile();
        }
        try {
            properties.load(configPath);
            initProperties();
        } catch (ConfigurationException e) {
            throw new RuntimeException("open timecache.properties failure");
        }
    }

    private static void initProperties() {
        int port = properties.getInt("port", timeCacheProperties.getPort());
        timeCacheProperties.setPort(port);
        int maxConnections = properties.getInt("maxConnections", timeCacheProperties.getMaxConnections());
        timeCacheProperties.setMaxConnections(maxConnections);
        boolean recoverTime = properties.getBoolean("recover.time", timeCacheProperties.isRecoverTime());
        timeCacheProperties.setRecoverTime(recoverTime);
        boolean persistenceTime = properties.getBoolean("persistence.time", timeCacheProperties.isPersistenceTime());
        timeCacheProperties.setPersistenceTime(persistenceTime);
        String persistenceTimePath = properties.getString("persistence.time.path", timeCacheProperties.getPersistenceTimePath());
        timeCacheProperties.setPersistenceDataPath(persistenceTimePath);
        long persistenceTimeInterval = properties.getLong("persistence.time.interval", timeCacheProperties.getPersistenceTimeInterval());
        timeCacheProperties.setPersistenceTimeInterval(persistenceTimeInterval);
        boolean recoverData = properties.getBoolean("recover.data", timeCacheProperties.isRecoverData());
        timeCacheProperties.setRecoverData(recoverData);
        boolean persistenceData = properties.getBoolean("persistence.data", timeCacheProperties.isPersistenceData());
        timeCacheProperties.setPersistenceData(persistenceData);
        String persistenceDataPath = properties.getString("persistence.data.path", timeCacheProperties.getPersistenceDataPath());
        timeCacheProperties.setPersistenceDataPath(persistenceDataPath);
    }

    public static TimeCacheProperties getProperties() {
        return timeCacheProperties;
    }
}
