package com.gms.xms.model;

/**
 * Posted from DimensionModel
 * <p>
 * Author DatTV Date Apr 2, 2015
 */
public class DimensionModel extends BaseModel {

    private static final long serialVersionUID = 3578330934769148870L;

    private String id;

    private String webshipId;

    private String name;

    private String l;

    private String w;

    private String h;

    private String comment;

    @Override
    public String toString() {
        return "DimensionModel [id=" + id + ", webshipId=" + webshipId + ", name=" + name + ", l=" + l + ", w=" + w + ", h=" + h + ", comment=" + comment + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(String webshipId) {
        this.webshipId = webshipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}