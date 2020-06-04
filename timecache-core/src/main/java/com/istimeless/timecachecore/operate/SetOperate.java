package com.istimeless.timecachecore.operate;

import com.istimeless.timecachecommon.enums.ValueEnum;
import com.istimeless.timecachecommon.exception.IllegalParamException;
import com.istimeless.timecachecommon.model.value.SetValue;
import com.istimeless.timecachecommon.model.value.Value;

public class SetOperate extends Operate {

    public void put(String key, String value) {
        Value old = get(key);
        if (old != null) {
            checkValueEnum(old);
            SetValue setValue = (SetValue) old;
            setValue.getItem().add(value);
        } else {
            SetValue setValue = new SetValue();
            setValue.getItem().add(value);
            put(key, setValue);
        }
    }

    public void delete(String key, String value) {
        Value old = get(key);
        if (old != null) {
            checkValueEnum(old);
            SetValue setValue = (SetValue) old;
            setValue.getItem().remove(value);
        }
    }

    private void checkValueEnum(Value value) {
        if (!value.getValueEnum().equals(ValueEnum.SET)) {
            throw new IllegalParamException("the type of key is illegal");
        }
    }
}
