package com.gms.xms.common.json;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Admin on 4/26/2017.
 */
public class JsonString2BooleanDeserializer extends JsonDeserializer<Boolean> implements Serializable {

    private static final long serialVersionUID = -6885923302337904016L;

    @Override
    public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return Boolean.valueOf(jsonParser.getText());
    }
}
