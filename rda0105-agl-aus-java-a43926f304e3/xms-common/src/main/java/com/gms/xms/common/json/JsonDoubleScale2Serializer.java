package com.gms.xms.common.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Posted from JsonDoubleScale2Serializer
 * </p>
 *
 * @author hungnt - Nov 25, 2015
 */
public class JsonDoubleScale2Serializer extends JsonSerializer<Double> {

    @Override
    public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(new BigDecimal(value).setScale(2, BigDecimal.ROUND_FLOOR).toString());
    }
}
