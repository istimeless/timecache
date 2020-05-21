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
            try {
                properties.load(configPath);
            } catch (ConfigurationException e) {
                throw new RuntimeException("open timecache.properties failure");
            }
        }
        int port = properties.getInt("port");
        timeCacheProperties.setPort(port);
    }

    public static TimeCacheProperties getProperties() {
        return timeCacheProperties;
    }
}
