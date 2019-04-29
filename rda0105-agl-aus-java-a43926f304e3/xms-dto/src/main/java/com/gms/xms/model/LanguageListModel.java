package com.gms.xms.model;

public class LanguageListModel extends BaseModel {
    private static final long serialVersionUID = 4828233599896118841L;
    private String languageCode;
    private String languageName;

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @Override
    public String toString() {
        return "LanguageListModel [languageCode=" + languageCode + ", languageName=" + languageName + "]";
    }
}