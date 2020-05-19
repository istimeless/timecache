package com.istimeless.timecachecommon.model.value;

import com.istimeless.timecachecommon.enums.ValueEnum;

import java.util.HashMap;

public class HashValue implements Value{

    private static final ValueEnum valueEnum = ValueEnum.HASH;

    private HashMap<String, String> item;

    public HashMap<String, String> getItem() {
        return item;
    }

    public void setItem(HashMap<String, String> item) {
        this.item = item;
    }

    @Override
    public ValueEnum getValueEnum() {
        return valueEnum;
    }
}
