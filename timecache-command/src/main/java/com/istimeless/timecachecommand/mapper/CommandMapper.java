package com.istimeless.timecachecommand.mapper;

import com.istimeless.timecachecore.operate.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandMapper {

    private static Map<String, Operate> operateMap;

    private static Map<String, Method> methodMap;

    static  {
        operateMap = new HashMap<>();
        methodMap = new HashMap<>();
        operateMap.put("string", new StringOperate());
        operateMap.put("hash", new HashOperate());
        operateMap.put("list", new ListOperate());
        operateMap.put("set", new SetOperate());
        operateMap.put("sortSet", new SortSetOperate());

        operateMap.forEach((k, v) -> {
            Method[] methods = v.getClass().getMethods();
            Arrays.stream(methods).forEach(method -> {
                String methodName = method.getName();
                methodMap.put(k + methodName, method);
            });
        });
        operateMap = Collections.unmodifiableMap(operateMap);
        methodMap = Collections.unmodifiableMap(methodMap);
    }

    public static Map<String, Operate> operateMap() {
        return operateMap;
    }

    public static Map<String, Method> methodMap() {
        return methodMap;
    }

    private CommandMapper () {}
}
