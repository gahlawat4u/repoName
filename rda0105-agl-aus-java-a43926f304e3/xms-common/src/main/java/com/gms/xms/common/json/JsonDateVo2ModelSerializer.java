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
 * Posted from JsonDateVo2ModelSerializer.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:35:18 PM
 */
public final class JsonDateVo2ModelSerializer extends JsonSerializer<Date> implements Serializable {

    private static final long serialVersionUID = -1908184002727493629L;

    /*
     * (non-Javadoc)
     * 
     * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
     */
    @Override
    public void serialize(Date value, JsonGenerator g, SerializerProvider p) throws IOException, JsonProcessingException {
        g.writeString(DateUtils.convertDateToString(value, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
    }
}
