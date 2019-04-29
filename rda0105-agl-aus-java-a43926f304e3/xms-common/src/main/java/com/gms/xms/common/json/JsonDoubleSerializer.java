package com.gms.xms.common.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Posted from JsonDoubleSerializer
 * <p>
 * Author DatTV Date Jul 1, 2015 5:13:43 PM
 */
public class JsonDoubleSerializer extends JsonSerializer<Double> {

    @Override
    public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    }
}
