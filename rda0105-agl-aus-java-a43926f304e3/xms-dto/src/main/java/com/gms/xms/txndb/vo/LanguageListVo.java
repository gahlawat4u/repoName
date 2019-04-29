package com.gms.xms.txndb.vo;

public class LanguageListVo extends BaseVo {
    private static final long serialVersionUID = 501072155271178344L;

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
        return "LanguageListVo [languageCode=" + languageCode + ", languageName=" + languageName + "]";
    }
}
