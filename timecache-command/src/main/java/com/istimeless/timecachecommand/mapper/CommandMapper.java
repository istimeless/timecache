package com.istimeless.timecachecommand.mapper;

import com.istimeless.timecachecore.operate.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandMapper {

    private static Map<String, Operate> operateMap;

    static  {
        operateMap = new HashMap<>();
        operateMap.put("string", new StringOperate());
        operateMap.put("hash", new HashOperate());
        operateMap.put("list", new ListOperate());
        operateMap.put("set", new SetOperate());
        operateMap.put("sortSet", new SortSetOperate());
        operateMap = Collections.unmodifiableMap(operateMap);
    }

    public static Map<String, Operate> operateMap() {
        return operateMap;
    }

    private CommandMapper () {}
}
