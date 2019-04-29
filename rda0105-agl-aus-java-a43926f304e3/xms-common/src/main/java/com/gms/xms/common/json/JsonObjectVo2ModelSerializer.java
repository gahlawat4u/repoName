package com.gms.xms.common.json;

import com.gms.xms.common.utils.AppUtils;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.io.Serializable;

/**
 * Posted from JsonObjectVo2ModelSerializer.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:35:25 PM
 */
public final class JsonObjectVo2ModelSerializer extends JsonSerializer<Object> implements Serializable {

    private static final long serialVersionUID = -1908184002727493629L;

    /*
     * (non-Javadoc)
     * 
     * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
     */
    @Override
    public void serialize(Object value, JsonGenerator g, SerializerProvider p) throws IOException, JsonProcessingException {
        if (value != null) {
            g.writeString(AppUtils.reconcileUTF8String(value.toString()));
        }
    }

}
