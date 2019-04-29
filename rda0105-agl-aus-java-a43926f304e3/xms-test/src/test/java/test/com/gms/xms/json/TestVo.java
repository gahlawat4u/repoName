package test.com.gms.xms.json;

import com.gms.xms.common.json.*;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class TestVo extends BaseVo {
    private static final long serialVersionUID = 1L;
    private Double doubleValue;
    private Float floatValue;
    private Byte byteValue;
    private Integer intValue;
    private Long logValue;

    @JsonSerialize(using = JsonNumber2DecimalStringSerializer.class)
    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    @JsonSerialize(using = JsonNumber2DecimalStringSerializer.class)
    public Float getFloatValue() {
        return floatValue;
    }

    @JsonDeserialize(using = JsonDecimalString2FloatDeserializer.class)
    public void setFloatValue(Float floatValue) {
        this.floatValue = floatValue;
    }

    @JsonSerialize(using = JsonNumber2DecimalStringSerializer.class)
    public Byte getByteValue() {
        return byteValue;
    }

    @JsonDeserialize(using = JsonDecimalString2ByteDeserializer.class)
    public void setByteValue(Byte byteValue) {
        this.byteValue = byteValue;
    }

    @JsonSerialize(using = JsonNumber2DecimalStringSerializer.class)
    public Integer getIntValue() {
        return intValue;
    }

    @JsonDeserialize(using = JsonDecimalString2IntegerDeserializer.class)
    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    @JsonSerialize(using = JsonNumber2DecimalStringSerializer.class)
    public Long getLogValue() {
        return logValue;
    }

    @JsonDeserialize(using = JsonDecimalString2LongDeserializer.class)
    public void setLogValue(Long logValue) {
        this.logValue = logValue;
    }

    @Override
    public String toString() {
        return "TestVo [doubleValue=" + doubleValue + ", floatValue=" + floatValue + ", byteValue=" + byteValue + ", intValue=" + intValue + ", logValue=" + logValue + "]";
    }

}
