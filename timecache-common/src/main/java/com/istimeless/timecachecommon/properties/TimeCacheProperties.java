package com.istimeless.timecachecommon.properties;

public class TimeCacheProperties {

    private int port = 6666;

    private int maxConnections = 128;

    private boolean recoverTime = true;

    private boolean persistenceTime = true;

    private String persistenceTimePath = "./timecache-time.data";

    private long persistenceTimeInterval = 1000 * 60 * 5L;

    private boolean recoverData = true;

    private boolean persistenceData = true;

    private String persistenceDataPath = "./timecache-data.data";

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public boolean isRecoverTime() {
        return recoverTime;
    }

    public void setRecoverTime(boolean recoverTime) {
        this.recoverTime = recoverTime;
    }

    public boolean isPersistenceTime() {
        return persistenceTime;
    }

    public void setPersistenceTime(boolean persistenceTime) {
        this.persistenceTime = persistenceTime;
    }

    public String getPersistenceTimePath() {
        return persistenceTimePath;
    }

    public void setPersistenceTimePath(String persistenceTimePath) {
        this.persistenceTimePath = persistenceTimePath;
    }

    public long getPersistenceTimeInterval() {
        return persistenceTimeInterval;
    }

    public void setPersistenceTimeInterval(long persistenceTimeInterval) {
        this.persistenceTimeInterval = persistenceTimeInterval;
    }

    public boolean isRecoverData() {
        return recoverData;
    }

    public void setRecoverData(boolean recoverData) {
        this.recoverData = recoverData;
    }

    public boolean isPersistenceData() {
        return persistenceData;
    }

    public void setPersistenceData(boolean persistenceData) {
        this.persistenceData = persistenceData;
    }

    public String getPersistenceDataPath() {
        return persistenceDataPath;
    }

    public void setPersistenceDataPath(String persistenceDataPath) {
        this.persistenceDataPath = persistenceDataPath;
    }
}
