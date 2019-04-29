package com.gms.xms.txndb.vo;

public class UserHomeVo extends BaseVo {
    private static final long serialVersionUID = 4878883738786714125L;

    private Long userId;
    private Integer div1;
    private Integer div2;
    private Integer div3;
    private Integer div4;
    private Integer div5;
    private Integer div6;
    private Float leftWidth;
    private Float topLeft;
    private Float topRight;
    private Float bottomLeft;
    private Float bottomRight;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDiv1() {
        return div1;
    }

    public void setDiv1(Integer div1) {
        this.div1 = div1;
    }

    public Integer getDiv2() {
        return div2;
    }

    public void setDiv2(Integer div2) {
        this.div2 = div2;
    }

    public Integer getDiv3() {
        return div3;
    }

    public void setDiv3(Integer div3) {
        this.div3 = div3;
    }

    public Integer getDiv4() {
        return div4;
    }

    public void setDiv4(Integer div4) {
        this.div4 = div4;
    }

    public Integer getDiv5() {
        return div5;
    }

    public void setDiv5(Integer div5) {
        this.div5 = div5;
    }

    public Integer getDiv6() {
        return div6;
    }

    public void setDiv6(Integer div6) {
        this.div6 = div6;
    }

    public Float getLeftWidth() {
        return leftWidth;
    }

    public void setLeftWidth(Float leftWidth) {
        this.leftWidth = leftWidth;
    }

    public Float getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Float topLeft) {
        this.topLeft = topLeft;
    }

    public Float getTopRight() {
        return topRight;
    }

    public void setTopRight(Float topRight) {
        this.topRight = topRight;
    }

    public Float getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(Float bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public Float getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Float bottomRight) {
        this.bottomRight = bottomRight;
    }

    @Override
    public String toString() {
        return "UserHomeVo [userId=" + userId + ", div1=" + div1 + ", div2=" + div2 + ", div3=" + div3 + ", div4=" + div4 + ", div5=" + div5 + ", div6=" + div6 + ", leftWidth=" + leftWidth + ", topLeft=" + topLeft + ", topRight=" + topRight + ", bottomLeft=" + bottomLeft + ", bottomRight=" + bottomRight + "]";
    }
}
