package com.gms.xms.model;

public class LanguageModel extends BaseModel {
    private static final long serialVersionUID = 8627591198695202308L;

    private Integer languageId;
    private String languageName;
    private String encodeName;

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getEncodeName() {
        return encodeName;
    }

    public void setEncodeName(String encodeName) {
        this.encodeName = encodeName;
    }

    @Override
    public String toString() {
        return "LanguageModel [languageId=" + languageId + ", languageName=" + languageName + ", encodeName=" + encodeName + "]";
    }
}
