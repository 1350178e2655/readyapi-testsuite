package me.chrisanabo.db.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;




public class JsonUtils {

    private static Logger log = LoggerFactory.getLogger(JsonUtils.class.getSimpleName() );

    private static ObjectMapper objectMapper = new ObjectMapper();
    
    static {
    	objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
	}

    public static Map<String, Object> deserializePayload(String payload) {
        if (StringUtils.isEmpty(payload)) {
            return Collections.emptyMap();
        } else {
            return deserialize(payload, Map.class);
        }
    }

    public static <T> T deserialize(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (Exception exception) {
            log.error("Error deserializing json to {}", tClass.getName(), exception);
        }
        return null;
    }

    public static <T> T convert(Object obj, Class<T> tClass) {
        try {
            return objectMapper.convertValue(obj, tClass);
        } catch (Exception exception) {
            log.error("Error deserializing json to {}", tClass.getName(), exception);
        }
        return null;
    }

    public static String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception exception) {
            log.error("Error serializing object");
            return null;
        }
    }

}
