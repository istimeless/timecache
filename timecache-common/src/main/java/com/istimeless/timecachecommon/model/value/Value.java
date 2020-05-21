package com.istimeless.timecachecommon.model.value;

import com.istimeless.timecachecommon.enums.ValueEnum;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public abstract class Value implements Serializable {

    private long timeout = -1L;

    /**
     * always {@link TimeUnit.MILLISECONDS}
     */
    private static final TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    public abstract ValueEnum getValueEnum();

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public static TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
