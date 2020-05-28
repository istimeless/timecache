package com.istimeless.timecachecommon.properties;

public class TimeCacheProperties {

    private int port = 6666;

    private int maxConnections = 128;

    private boolean recover = true;

    private boolean persistence = true;

    private String persistencePath = "./timecache.bak";

    private long persistenceInterval = 1000 * 60 * 5L;

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

    public boolean isRecover() {
        return recover;
    }

    public void setRecover(boolean recover) {
        this.recover = recover;
    }

    public boolean isPersistence() {
        return persistence;
    }

    public void setPersistence(boolean persistence) {
        this.persistence = persistence;
    }

    public String getPersistencePath() {
        return persistencePath;
    }

    public void setPersistencePath(String persistencePath) {
        this.persistencePath = persistencePath;
    }

    public long getPersistenceInterval() {
        return persistenceInterval;
    }

    public void setPersistenceInterval(long persistenceInterval) {
        this.persistenceInterval = persistenceInterval;
    }
}
