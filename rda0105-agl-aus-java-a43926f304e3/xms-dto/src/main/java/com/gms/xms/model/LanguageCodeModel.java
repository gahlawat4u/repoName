package com.gms.xms.model;

public class LanguageCodeModel extends BaseModel {
    private static final long serialVersionUID = -6907694774471587434L;

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
        return "LanguageCodeModel [languageName=" + languageName + ", languageCode=" + languageCode + "]";
    }
}
