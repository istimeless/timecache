package com.istimeless.timecachecommon.model.key;

import com.istimeless.timecachecommon.enums.ValueEnum;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public final class Key implements Serializable {

    private final String item;

    private ValueEnum valueEnum = ValueEnum.STRING;

    private long timeout = -1L;

    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    public Key(String item) {
        this.item = item;
    }

    public Key(String item, ValueEnum valueEnum) {
        this.item = item;
        this.valueEnum = valueEnum;
    }

    public Key(String item, ValueEnum valueEnum, long timeout) {
        this.item = item;
        this.valueEnum = valueEnum;
        this.timeout = timeout;
    }

    public Key(String item, ValueEnum valueEnum, long timeout, TimeUnit timeUnit) {
        this.item = item;
        this.valueEnum = valueEnum;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    public String getItem() {
        return item;
    }

    public ValueEnum getValueEnum() {
        return valueEnum;
    }

    public void setValueEnum(ValueEnum valueEnum) {
        this.valueEnum = valueEnum;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    @Override
    public int hashCode() {
        return this.item.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Key) {
            Key key = (Key) obj;
            return this.item.equals(key.item);
        }
        return false;
    }
}
