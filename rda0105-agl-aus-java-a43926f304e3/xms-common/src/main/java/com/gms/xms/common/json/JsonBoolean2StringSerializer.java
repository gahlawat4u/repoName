package com.gms.xms.common.json;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.DateUtils;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Admin on 4/26/2017.
 */
public class JsonBoolean2StringSerializer extends JsonSerializer<Boolean> implements Serializable {

    private static final long serialVersionUID = -4324962811064104322L;

    @Override
    public void serialize(Boolean aBoolean, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        aBoolean = aBoolean != null ? aBoolean : false;
        jsonGenerator.writeString(aBoolean.toString());
    }
}
