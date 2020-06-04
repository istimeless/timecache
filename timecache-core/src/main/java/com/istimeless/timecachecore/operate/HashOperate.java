package com.istimeless.timecachecore.operate;

import com.istimeless.timecachecommon.enums.ValueEnum;
import com.istimeless.timecachecommon.exception.IllegalParamException;
import com.istimeless.timecachecommon.model.value.HashValue;
import com.istimeless.timecachecommon.model.value.Value;
import org.apache.commons.lang3.StringUtils;

public class HashOperate extends Operate {

    public void put(String key, String hashKey, String value) {
        Value old = get(key);
        if (old != null) {
            checkValueEnum(old);
            HashValue hashValue = (HashValue) old;
            hashValue.getItem().put(hashKey, value);
        } else {
            HashValue hashValue = new HashValue();
            hashValue.getItem().put(hashKey, value);
            put(key, hashValue);
        }
    }

    public boolean putIfAbsent(String key, String hashKey, String value) {
        String result = get(key, hashKey);
        if (StringUtils.isNotBlank(result)) return false;
        put(key, hashKey, value);
        return true;
    }

    public String get(String key, String hashKey) {
        Value value = get(key);
        checkValueEnum(value);
        HashValue hashValue = (HashValue) value;
        return hashValue.getItem().get(hashKey);
    }

    public void delete(String key, String hashKey) {
        Value value = get(key);
        if (value != null) {
            checkValueEnum(value);
            HashValue hashValue = (HashValue) value;
            hashValue.getItem().remove(hashKey);
        }
    }

    private void checkValueEnum(Value value) {
        if (!value.getValueEnum().equals(ValueEnum.HASH)) {
            throw new IllegalParamException("the type of key is illegal");
        }
    }
}
