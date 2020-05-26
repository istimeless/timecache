package com.istimeless.timecachecommand.command;

import com.istimeless.timecachecore.operate.Operate;

import java.lang.reflect.Method;

public interface CommandAnalysis {

    Operate analysisOperate(String command);

    Method analysisMethod(String command);

    Object[] analysisParameter(String command, Operate operate, Method method);
}
