package com.gms.xms.common.json;

import com.gms.xms.common.constants.AppConstants;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Posted from JsonDouble2IntStringSerializer.java
 * <p>
 * Author dattrinh 10:04:38 AM
 */
public final class JsonDouble2IntStringSerializer extends JsonSerializer<Double> implements Serializable {

    private static final long serialVersionUID = -3155147080768082306L;

    @Override
    public void serialize(Double value, JsonGenerator g, SerializerProvider p) throws IOException, JsonProcessingException {
        NumberFormat formatter = new DecimalFormat(AppConstants.APP_SETTINGS.getDefaultIntNumberFormat());
        BigDecimal roundVal = new BigDecimal(value).setScale(0, RoundingMode.HALF_UP);
        g.writeString(formatter.format(roundVal));
    }
}
