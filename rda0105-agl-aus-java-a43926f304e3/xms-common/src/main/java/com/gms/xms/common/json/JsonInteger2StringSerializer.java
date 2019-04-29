package com.gms.xms.common.json;

import com.gms.xms.common.constants.AppConstants;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Posted from JsonInteger2StringSerializer.java
 * <p>
 * Author dattrinh 4:00:35 PM
 */
public final class JsonInteger2StringSerializer extends JsonSerializer<Integer> implements Serializable {

    private static final long serialVersionUID = -3155147080768082306L;

    @Override
    public void serialize(Integer value, JsonGenerator g, SerializerProvider p) throws IOException, JsonProcessingException {
        NumberFormat formatter = new DecimalFormat(AppConstants.APP_SETTINGS.getDefaultIntNumberFormat());
        g.writeString(formatter.format(value));
    }
}
