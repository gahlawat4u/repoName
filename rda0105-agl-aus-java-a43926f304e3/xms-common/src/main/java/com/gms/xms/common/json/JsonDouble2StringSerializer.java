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
 * Posted from JsonDouble2StringSerializer
 * <p>
 * Author DatTV Nov 6, 2015
 */
public final class JsonDouble2StringSerializer extends JsonSerializer<Double> implements Serializable {

    private static final long serialVersionUID = -3155147080768082306L;

    @Override
    public void serialize(Double value, JsonGenerator g, SerializerProvider p) throws IOException, JsonProcessingException {
        NumberFormat formatter = new DecimalFormat(AppConstants.APP_SETTINGS.getDefaultNumberFormat());
        g.writeString(formatter.format(value));
    }
}
