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
 * Posted from JsonObjectVo2ModelSerializer.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:35:25 PM
 */
public final class JsonDate2StringSerializer extends JsonSerializer<Date> implements Serializable {

    private static final long serialVersionUID = -1908184002727493629L;

    /*
     * (non-Javadoc)
     * 
     * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
     */
    @Override
    public void serialize(Date value, JsonGenerator g, SerializerProvider p) throws IOException, JsonProcessingException {
        if (value != null) {
            //Date date = DateUtils.convertStringToDate(AppUtils.reconcileUTF8String(value.toString()),AppConstants.APP_SETTINGS.getDefaultDateFormat(),null);
            g.writeString(DateUtils.convertDateToString(value, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        }
    }
}
