package com.gms.xms.txndb.vo;

public class LanguageCodeVo extends BaseVo {
    private static final long serialVersionUID = 787617104685096856L;

    private String languageName;
    private String languageCode;

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @Override
    public String toString() {
        return "LanguageCodeVo [languageName=" + languageName + ", languageCode=" + languageCode + "]";
    }
}
