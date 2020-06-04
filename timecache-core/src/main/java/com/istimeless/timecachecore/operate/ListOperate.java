package com.istimeless.timecachecore.operate;

import com.istimeless.timecachecommon.enums.ValueEnum;
import com.istimeless.timecachecommon.exception.IllegalParamException;
import com.istimeless.timecachecommon.model.value.ListValue;
import com.istimeless.timecachecommon.model.value.Value;
import org.apache.commons.lang3.StringUtils;

public class ListOperate extends Operate {

    public void put(String key, int index, String value) {
        Value old = get(key);
        if (old != null) {
            checkValueEnum(old);
            ListValue listValue = (ListValue) old;
            listValue.getItem().set(index, value);
        } else {
            ListValue listValue = new ListValue();
            listValue.getItem().set(index, value);
            put(key, listValue);
        }
    }

    public boolean putIfAbsent(String key, int index, String value) {
        String result = get(key, index);
        if (StringUtils.isNotBlank(result)) return false;
        put(key, index, value);
        return true;
    }

    public String get(String key, int index) {
        Value value = get(key);
        checkValueEnum(value);
        ListValue listValue = (ListValue) value;
        return listValue.getItem().get(index);
    }

    public void delete(String key, int index) {
        Value value = get(key);
        if (value != null) {
            checkValueEnum(value);
            ListValue listValue = (ListValue) value;
            listValue.getItem().remove(index);
        }
    }

    private void checkValueEnum(Value value) {
        if (!value.getValueEnum().equals(ValueEnum.LIST)) {
            throw new IllegalParamException("the type of key is illegal");
        }
    }
}
