package com.istimeless.timecachecommon.model.value;

import com.istimeless.timecachecommon.enums.ValueEnum;

import java.util.TreeSet;

public class SortSetValue implements Value {

    private static final ValueEnum valueEnum = ValueEnum.SORT_SET;

    private TreeSet<String> item;

    public TreeSet<String> getItem() {
        return item;
    }

    public void setItem(TreeSet<String> item) {
        this.item = item;
    }

    @Override
    public ValueEnum getValueEnum() {
        return valueEnum;
    }
}
