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
 * Posted from JsonBigDecimalVo2ModelSerializer.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:35:10 PM
 */
public final class JsonNumber2DecimalStringSerializer extends JsonSerializer<Number> implements Serializable {

    private static final long serialVersionUID = -1908184002727493629L;

    /*
     * (non-Javadoc)
     * 
     * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
     */
    @Override
    public void serialize(Number value, JsonGenerator g, SerializerProvider p) throws IOException, JsonProcessingException {
        NumberFormat formatter = new DecimalFormat(AppConstants.APP_SETTINGS.getDefaultNumberFormat());
        g.writeString(formatter.format(value));
    }
}
