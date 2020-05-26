package com.istimeless.timecachecommand.command;

import com.istimeless.timecachecommon.enums.CodeEnum;
import com.istimeless.timecachecommon.model.result.Result;
import com.istimeless.timecachecommon.model.value.Value;
import com.istimeless.timecachecommon.utils.GsonUtil;
import com.istimeless.timecachecore.operate.Operate;

import java.lang.reflect.Method;

public class CommandHandler {

    private static final CommandAnalysis commandAnalysis = new CommandAnalysisImpl();

    public static String invokeCommand(String command) {
        Result result = new Result(CodeEnum.FAILURE.getCode());
        try {
            Operate operate = commandAnalysis.analysisOperate(command);
            Method method = commandAnalysis.analysisMethod(command);
            Object[] objects = commandAnalysis.analysisParameter(command, operate, method);
            method.setAccessible(true);
            Object invoke = method.invoke(operate, objects);
            if (invoke == null) {
                result.setCode(CodeEnum.SUCCESS.getCode());
            } else if (invoke instanceof Boolean) {
                Boolean res = (Boolean) invoke;
                if (Boolean.TRUE.equals(res)) {
                    result.setCode(CodeEnum.SUCCESS.getCode());
                }
            } else if (invoke instanceof Value) {
                Value res = (Value) invoke;
                result.setCode(CodeEnum.SUCCESS.getCode());
                result.setData(res);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
        }
        return GsonUtil.getGson().toJson(result);
    }
}
