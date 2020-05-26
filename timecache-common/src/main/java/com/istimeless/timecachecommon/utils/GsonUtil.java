package com.istimeless.timecachecommon.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

    private static final Gson GSON;

    static {
        GSON = new GsonBuilder().create();
    }

    public static Gson getGson() {
        return GSON;
    }
}
