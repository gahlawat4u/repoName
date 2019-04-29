package com.gms.delivery.ups.service.rest.shipment.pojo.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PrimaryErrorCode
{
    private String description;

    private String code;

   

   

    public String getDescription() {
		return description;
	}





	public void setDescription(String description) {
		this.description = description;
	}





	public String getCode() {
		return code;
	}





	public void setCode(String code) {
		this.code = code;
	}





	@Override
    public String toString()
    {
        return "ClassPojo [Description = "+description+", Code = "+code+"]";
    }
}
			
			
