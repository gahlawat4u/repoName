package test.com.gms.xms.json;

import com.gms.xms.model.BaseModel;

public class TestModel extends BaseModel {
    private static final long serialVersionUID = 1L;
    private String doubleValue;
    private String floatValue;
    private String byteValue;
    private String intValue;
    private String logValue;

    public String getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(String doubleValue) {
        this.doubleValue = doubleValue;
    }

    public String getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(String floatValue) {
        this.floatValue = floatValue;
    }

    public String getByteValue() {
        return byteValue;
    }

    public void setByteValue(String byteValue) {
        this.byteValue = byteValue;
    }

    public String getIntValue() {
        return intValue;
    }

    public void setIntValue(String intValue) {
        this.intValue = intValue;
    }

    public String getLogValue() {
        return logValue;
    }

    public void setLogValue(String logValue) {
        this.logValue = logValue;
    }

    @Override
    public String toString() {
        return "TestModel [doubleValue=" + doubleValue + ", floatValue=" + floatValue + ", byteValue=" + byteValue + ", intValue=" + intValue + ", logValue=" + logValue + "]";
    }

}
