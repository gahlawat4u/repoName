package com.gms.xms.model;

public class UserHomeModel extends BaseModel {
    private static final long serialVersionUID = -3827361127059563356L;
    private String userId;
    private String div1;
    private String div2;
    private String div3;
    private String div4;
    private String div5;
    private String div6;
    private String leftWidth;
    private String topLeft;
    private String topRight;
    private String bottomLeft;
    private String bottomRight;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDiv1() {
        return div1;
    }

    public void setDiv1(String div1) {
        this.div1 = div1;
    }

    public String getDiv2() {
        return div2;
    }

    public void setDiv2(String div2) {
        this.div2 = div2;
    }

    public String getDiv3() {
        return div3;
    }

    public void setDiv3(String div3) {
        this.div3 = div3;
    }

    public String getDiv4() {
        return div4;
    }

    public void setDiv4(String div4) {
        this.div4 = div4;
    }

    public String getDiv5() {
        return div5;
    }

    public void setDiv5(String div5) {
        this.div5 = div5;
    }

    public String getDiv6() {
        return div6;
    }

    public void setDiv6(String div6) {
        this.div6 = div6;
    }

    public String getLeftWidth() {
        return leftWidth;
    }

    public void setLeftWidth(String leftWidth) {
        this.leftWidth = leftWidth;
    }

    public String getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(String topLeft) {
        this.topLeft = topLeft;
    }

    public String getTopRight() {
        return topRight;
    }

    public void setTopRight(String topRight) {
        this.topRight = topRight;
    }

    public String getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(String bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public String getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(String bottomRight) {
        this.bottomRight = bottomRight;
    }

    @Override
    public String toString() {
        return "UserHomeModel [userId=" + userId + ", div1=" + div1 + ", div2=" + div2 + ", div3=" + div3 + ", div4=" + div4 + ", div5=" + div5 + ", div6=" + div6 + ", leftWidth=" + leftWidth + ", topLeft=" + topLeft + ", topRight=" + topRight + ", bottomLeft=" + bottomLeft + ", bottomRight=" + bottomRight + "]";
    }
}
