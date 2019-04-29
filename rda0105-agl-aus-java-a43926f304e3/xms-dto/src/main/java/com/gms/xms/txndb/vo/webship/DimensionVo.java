package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from DimensionVo
 * <p>
 * Author DatTV Date Apr 2, 2015
 */
public class DimensionVo extends BaseVo {

    private static final long serialVersionUID = -5052912581221322208L;

    private Integer id;

    private Integer webshipId;

    private String name;

    private Integer l;

    private Integer w;

    private Integer h;

    private String comment;

    @Override
    public String toString() {
        return "DimensionVo [id=" + id + ", webshipId=" + webshipId + ", name=" + name + ", l=" + l + ", w=" + w + ", h=" + h + ", comment=" + comment + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(Integer webshipId) {
        this.webshipId = webshipId;
    }
}