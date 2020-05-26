package com.istimeless.timecachecommand.command;

import com.istimeless.timecachecommand.mapper.CommandMapper;
import com.istimeless.timecachecommon.constant.CommonConstant;
import com.istimeless.timecachecommon.exception.IllegalCommandException;
import com.istimeless.timecachecommon.model.value.StringValue;
import com.istimeless.timecachecommon.model.value.Value;
import com.istimeless.timecachecommon.utils.GsonUtil;
import com.istimeless.timecachecore.operate.HashOperate;
import com.istimeless.timecachecore.operate.Operate;
import com.istimeless.timecachecore.operate.StringOperate;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

public class CommandAnalysisImpl implements CommandAnalysis{

    @Override
    public Operate analysisOperate(String command) {
        Map<String, Operate> operateMap = CommandMapper.operateMap();
        String operateKey = getOperateKey(command);
        Operate operate = operateMap.get(operateKey);
        if (operate == null) {
            throw new IllegalCommandException();
        }
        return operate;
    }

    @Override
    public Method analysisMethod(String command) {
        Map<String, Method> methodMap = CommandMapper.methodMap();
        String methodKey = getMethodKey(command);
        Method method = methodMap.get(methodKey);
        if (method == null) {
            throw new IllegalCommandException();
        }
        return method;
    }

    @Override
    public Object[] analysisParameter(String command, Operate operate, Method method) {
        String[] parameterStrings = getParameterStrings(command);
        Parameter[] parameters = method.getParameters();
        if (parameterStrings.length == 0 && parameters.length == 0) {
            return null;
        }
        if (parameterStrings.length != parameters.length) {
            throw new IllegalCommandException();
        }
        Object[] objects = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            String parameterString = parameterStrings[i];
            Class<?> parameterType = parameter.getType();
            if (parameterType == Value.class) {
                if (operate instanceof StringOperate) {
                    parameterType = StringValue.class;
//                    objects[i] = new StringValue(parameterString);
                } else if (operate instanceof HashOperate) {

                }
            }
            Object object = GsonUtil.getGson().fromJson(parameterString, parameterType);
            objects[i] = object;
        }
        return objects;
    }

    private String getOperateKey(String command) {
        String[] split = command.split(CommonConstant.COMMAND_SPLIT);
        if (split.length < 3) {
            throw new IllegalCommandException();
        }
        return split[1];
    }

    private String getMethodKey(String command) {
        String[] split = command.split(CommonConstant.COMMAND_SPLIT);
        if (split.length < 3) {
            throw new IllegalCommandException();
        }
        return split[1] + split[2];
    }

    private String[] getParameterStrings(String command) {
        String[] split = command.split(CommonConstant.COMMAND_SPLIT);
        if (split.length < 3) {
            throw new IllegalCommandException();
        }
        if (split.length == 3) {
            return new String[0];
        }
        String[] parameter = new String[split.length - 3];
        System.arraycopy(split, 3, parameter, 0, split.length - 3);
        return parameter;
    }

//    private int getOperateIndex(String command) {
//        String[] split = command.split(CommonConstant.COMMAND_SPLIT);
//        if (split.length < 3) {
//            throw new IllegalCommandException();
//        }
//        int operate = command.indexOf(CommonConstant.COMMAND_SPLIT);
//        if (operate == -1) {
//            throw new IllegalCommandException();
//        }
//        return operate;
//    }
//
//    private int getMethodIndex(String command) {
//        int operate = getOperateIndex(command);
//        String methodCommand = command.substring(operate + 1);
//        int method = methodCommand.indexOf(CommonConstant.COMMAND_SPLIT);
//        if (method == -1) {
//            throw new IllegalCommandException();
//        }
//        return method;
//    }
}
