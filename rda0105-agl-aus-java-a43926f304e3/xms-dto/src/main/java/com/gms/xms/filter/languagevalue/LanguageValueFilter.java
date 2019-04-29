package com.gms.xms.filter.languagevalue;

import com.gms.xms.filter.BaseFilter;

public class LanguageValueFilter extends BaseFilter {
    private String langCode;
    private String original;

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
