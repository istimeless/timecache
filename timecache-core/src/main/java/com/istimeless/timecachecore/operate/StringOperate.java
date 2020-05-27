package com.istimeless.timecachecore.operate;

import com.istimeless.timecachecommon.model.value.StringValue;
import com.istimeless.timecachecommon.model.value.Value;

public class StringOperate extends Operate {

    public Value put(String key, String value) {
        StringValue stringValue = new StringValue();
        stringValue.setItem(value);
        return put(key, stringValue);
    }

    public boolean putIfAbsent(String key, String value) {
        StringValue stringValue = new StringValue();
        stringValue.setItem(value);
        return putIfAbsent(key, stringValue);
    }
}
