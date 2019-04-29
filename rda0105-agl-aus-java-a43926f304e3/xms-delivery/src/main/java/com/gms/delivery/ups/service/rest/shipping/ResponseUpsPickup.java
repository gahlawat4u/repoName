package com.gms.delivery.ups.service.rest.shipping;

public class ResponseUpsPickup {

	private String desc;

    private String confirmationNumber;

    private String errorCode;

    private String type;

    public String getDesc ()
    {
        return desc;
    }

    public void setDesc (String desc)
    {
        this.desc = desc;
    }

    public String getConfirmationNumber ()
    {
        return confirmationNumber;
    }

    public void setConfirmationNumber (String confirmationNumber)
    {
        this.confirmationNumber = confirmationNumber;
    }

    public String getErrorCode ()
    {
        return errorCode;
    }

    public void setErrorCode (String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ResponseUpsPickup [desc = "+desc+", confirmationNumber = "+confirmationNumber+", errorCode = "+errorCode+", type = "+type+"]";
    }
}
