package com.istimeless.timecachecommand.command;

import com.istimeless.timecachecommand.mapper.Invoke;
import com.istimeless.timecachecommon.enums.CodeEnum;
import com.istimeless.timecachecommon.model.result.Result;
import com.istimeless.timecachecommon.utils.GsonUtil;
import com.istimeless.timecachecore.operate.Operate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CommandHandler {

    private static final CommandAnalysis commandAnalysis = new CommandAnalysisImpl();

    private CommandHandler () {}

    @SuppressWarnings("all")
    public static String invokeCommand(String command) {
        Result result = new Result(CodeEnum.FAILURE.getCode());
        try {
            Invoke invoke = commandAnalysis.analysisCommand(command);
            Operate operate = invoke.getOperate();
            Method method = invoke.getMethod();
            Object[] objects = invoke.getParameters();
            method.setAccessible(true);
            Object object = method.invoke(operate, objects);
            result.setCode(CodeEnum.SUCCESS.getCode());
            result.setData(object);
            result.setId(invoke.getId());
        } catch (InvocationTargetException e) {
            //method exception
            result.setMsg(e.getTargetException().getMessage());
        } catch (Exception e) {
            result.setMsg(e.getMessage());
        }
        return GsonUtil.getGson().toJson(result);
    }
}
