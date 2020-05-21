package com.istimeless.timecachecommon.model.value;

import com.istimeless.timecachecommon.enums.ValueEnum;

import java.util.ArrayList;

public class ListValue extends Value {

    private static final ValueEnum valueEnum = ValueEnum.LIST;

    private ArrayList<String> item = new ArrayList<>();

    public ArrayList<String> getItem() {
        return item;
    }

    public void setItem(ArrayList<String> item) {
        this.item = item;
    }

    @Override
    public ValueEnum getValueEnum() {
        return valueEnum;
    }
}
