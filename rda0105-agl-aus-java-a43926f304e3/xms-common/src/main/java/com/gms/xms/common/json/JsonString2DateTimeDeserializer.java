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
 * Posted from JsonString2DateTimeDeserializer.java
 * <p>
 * Author TANDT
 */
public final class JsonString2DateTimeDeserializer extends JsonDeserializer<Date> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2213291052125143403L;

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String strDate = jsonParser.getText();
        return DateUtils.convertStringToDate(strDate, AppConstants.APP_SETTINGS.getDefaultDateTimeFormat(), null);
    }

}
