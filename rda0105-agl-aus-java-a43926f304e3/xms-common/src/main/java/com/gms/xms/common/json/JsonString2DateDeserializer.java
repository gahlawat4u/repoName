package com.gms.xms.common.json;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.DateUtils;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * Posted from JsonDateVo2ModelSerializer.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:35:18 PM
 */
public final class JsonString2DateDeserializer extends JsonDeserializer<Date> implements Serializable {

    private static final long serialVersionUID = -1908184002727493629L;

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String strDate = jsonParser.getText();
        return DateUtils.convertStringToDate(strDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
    }


}
