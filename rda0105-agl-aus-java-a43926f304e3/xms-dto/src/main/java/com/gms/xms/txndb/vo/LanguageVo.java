package com.gms.xms.txndb.vo;

public class LanguageVo extends BaseVo {
    private static final long serialVersionUID = -5108236405742578227L;

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
        return "LanguageVo [languageId=" + languageId + ", languageName=" + languageName + ", encodeName=" + encodeName + "]";
    }
}
