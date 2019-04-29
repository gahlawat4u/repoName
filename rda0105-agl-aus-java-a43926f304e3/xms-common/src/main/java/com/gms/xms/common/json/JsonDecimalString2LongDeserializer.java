package com.gms.xms.common.json;

import com.gms.xms.common.utils.AppUtils;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;
import java.io.Serializable;

/**
 * Posted from JsonDateVo2ModelSerializer.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:35:18 PM
 */
public final class JsonDecimalString2LongDeserializer extends JsonDeserializer<Long> implements Serializable {
    private static final long serialVersionUID = -1908184002727493629L;

    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        try {
            Number result = AppUtils.parseNumber(jsonParser.getText());
            return result == null ? null : result.longValue();
        } catch (Exception e) {
            throw new IOException(e);
        }

    }
}
