package com.istimeless.timecachecommand.command;

import com.istimeless.timecachecommand.mapper.CommandMapper;
import com.istimeless.timecachecommand.mapper.Invoke;
import com.istimeless.timecachecommon.constant.CommonConstant;
import com.istimeless.timecachecommon.exception.IllegalCommandException;
import com.istimeless.timecachecommon.model.value.*;
import com.istimeless.timecachecommon.utils.GsonUtil;
import com.istimeless.timecachecore.operate.*;

import java.lang.reflect.Method;
import java.util.Map;

public class CommandAnalysisImpl implements CommandAnalysis{

    @Override
    public Invoke analysisCommand(String command) {
        Invoke invoke = new Invoke();
        Operate operate = analysisOperate(command);
        invoke.setOperate(operate);
        String methodKey = getMethodKey(command);
        String[] parameterStrings = getParameterStrings(command);
        Method[] methods = operate.getClass().getMethods();
        boolean findMethod = false;
        for (Method method : methods) {
            if (method.getName().equals(methodKey)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == parameterStrings.length) {
                    try {
                        Object[] objects = new Object[parameterTypes.length];
                        // try match parameter
                        for (int i = 0; i < parameterTypes.length; i++) {
                            String parameterString = parameterStrings[i];
                            Class<?> parameterType = parameterTypes[i];
                            parameterType = valueClass(operate, parameterType);
                            Object object = GsonUtil.getGson().fromJson(parameterString, parameterType);
                            objects[i] = object;
                        }
                        // parameter match success
                        findMethod = true;
                        invoke.setMethod(method);
                        invoke.setParameters(objects);
                        break;
                    } catch (Exception e) {
                        findMethod = false;
                    }
                }
            }
        }
        if (!findMethod) {
            throw new IllegalCommandException();
        }
        return invoke;
    }

    private Class<?> valueClass(Operate operate, Class<?> parameterType) {
        if (parameterType == Value.class) {
            if (operate instanceof StringOperate) {
                parameterType = StringValue.class;
            } else if (operate instanceof HashOperate) {
                parameterType = HashValue.class;
            } else if (operate instanceof ListOperate) {
                parameterType = ListValue.class;
            } else if (operate instanceof SetOperate) {
                parameterType = SetValue.class;
            } else if (operate instanceof SortSetOperate) {
                parameterType = SortSetValue.class;
            }
        }
        return parameterType;
    }

    private Operate analysisOperate(String command) {
        Map<String, Operate> operateMap = CommandMapper.operateMap();
        String operateKey = getOperateKey(command);
        Operate operate = operateMap.get(operateKey);
        if (operate == null) {
            throw new IllegalCommandException();
        }
        return operate;
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
        return split[2];
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
}
