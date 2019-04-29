package com.gms.xms.common.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.io.Serializable;

/**
 * Posted from JsonTime2StringSerializer
 * <p>
 * Author dattrinh Jan 26, 2016 2:37:04 PM
 */
public final class JsonTime2StringSerializer extends JsonSerializer<String> implements Serializable {

    private static final long serialVersionUID = -3155147080768082306L;

    @Override
    public void serialize(String value, JsonGenerator g, SerializerProvider p) throws IOException, JsonProcessingException {
        String result = value.substring(0, value.length() - 3);
        g.writeString(result);
    }
}
