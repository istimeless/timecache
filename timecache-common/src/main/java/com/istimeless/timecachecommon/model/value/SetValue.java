package com.istimeless.timecachecommon.model.value;

import com.istimeless.timecachecommon.enums.ValueEnum;

import java.util.HashSet;

public class SetValue extends Value {

    private static final ValueEnum valueEnum = ValueEnum.SET;

    private HashSet<String> item = new HashSet<>();

    public HashSet<String> getItem() {
        return item;
    }

    public void setItem(HashSet<String> item) {
        this.item = item;
    }

    @Override
    public ValueEnum getValueEnum() {
        return valueEnum;
    }
}
