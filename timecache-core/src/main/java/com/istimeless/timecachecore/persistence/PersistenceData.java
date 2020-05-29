package com.istimeless.timecachecore.persistence;

import com.istimeless.timecachecommon.model.value.Value;

import java.util.Map;

public interface PersistenceData {

    void persistenceForTime();

    void persistenceForData(Map<String, Value> data);
}
