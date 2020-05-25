package com.istimeless.timecachecommon.properties;

public class TimeCacheProperties {

    private int port = 6666;

    private int maxConnections = 128;

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
}
