package com.gms.delivery.ups.service.rest.label;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Translate {
	
	@SerializedName("LanguageCode")
	@Expose
private String LanguageCode;
public String getLanguageCode() {
	return LanguageCode;
}
public void setLanguageCode(String languageCode) {
	LanguageCode = languageCode;
}
public String getDialectCode() {
	return DialectCode;
}
public void setDialectCode(String dialectCode) {
	DialectCode = dialectCode;
}
public String getCode() {
	return Code;
}
public void setCode(String code) {
	Code = code;
}
@SerializedName("DialectCode")
@Expose
private String DialectCode;

@SerializedName("Code")
@Expose
private String Code;

}
