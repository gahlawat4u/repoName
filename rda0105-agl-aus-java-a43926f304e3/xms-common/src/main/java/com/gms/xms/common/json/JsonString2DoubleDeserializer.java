package com.gms.xms.common.json;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;
import java.io.Serializable;

/**
 * Posted from JsonString2DoubleDeserializer
 * <p>
 * Author dattrinh Mar 23, 2016 10:38:26 AM
 */
public final class JsonString2DoubleDeserializer extends JsonDeserializer<Double> implements Serializable {

    private static final long serialVersionUID = -1908184002727493629L;

    @Override
    public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String valueStr = jsonParser.getText();
        valueStr = valueStr.replace(",", "");
        return StringUtils.isBlank(valueStr) ? null : Double.valueOf(valueStr);
    }
}
