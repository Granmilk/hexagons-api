package com.gtbr.hexapi.util;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QueryUtils {

    @SneakyThrows
    public static Map<String, Object> getMapFromFilled(Object object){
        Map<String, Object> map = new HashMap<>();

        for (Field declaredField : object.getClass().getDeclaredFields()) {
            declaredField.setAccessible(true);
            if (!declaredField.isSynthetic() && Objects.nonNull(declaredField.get(object)))
                map.put(declaredField.getName(), declaredField.get(object));

            declaredField.setAccessible(false);
        }

        return map;
    }
}
