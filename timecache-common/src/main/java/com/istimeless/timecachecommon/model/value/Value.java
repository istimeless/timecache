package com.istimeless.timecachecommon.model.value;

import com.istimeless.timecachecommon.enums.ValueEnum;

import java.io.Serializable;

public interface Value extends Serializable {

    ValueEnum getValueEnum();
}
