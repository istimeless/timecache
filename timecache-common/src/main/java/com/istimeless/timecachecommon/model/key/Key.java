package com.istimeless.timecachecommon.model.key;

import com.istimeless.timecachecommon.enums.ValueEnum;

import java.io.Serializable;

public class Key implements Serializable {

    private String item;

    private ValueEnum valueEnum;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public ValueEnum getValueEnum() {
        return valueEnum;
    }

    public void setValueEnum(ValueEnum valueEnum) {
        this.valueEnum = valueEnum;
    }
}
