package com.gms.xms.model;

/**
 * Posted from LanguageValueModel
 * </p>
 *
 * @author hungnt - Nov 28, 2015
 */
public class LanguageValueModel extends BaseModel {
    private static final long serialVersionUID = 8691691568258558337L;

    private String id;
    private String langCode;
    private String original;
    private String destination;
    private String mode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "LanguageValueModel [id=" + id + ", langCode=" + langCode + ", original=" + original + ", destination=" + destination + ", mode=" + mode + "]";
    }
}
