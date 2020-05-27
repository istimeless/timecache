package com.istimeless.timecachecommand.command;

import com.istimeless.timecachecommand.mapper.Invoke;

public interface CommandAnalysis {

    Invoke analysisCommand(String command);
}
