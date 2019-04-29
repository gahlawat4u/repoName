package com.gms.xms.model.webship.history;

import com.gms.xms.model.BaseModel;

/**
 * Posted from HistoryDetailPieceVo
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryDetailPieceModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = 8770002259057982182L;

    private String pieces;
    private String deadWeight;
    private String dimension;
    private String cubicWeight;

    public String getPieces() {
        return pieces;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }

    public String getDeadWeight() {
        return deadWeight;
    }

    public void setDeadWeight(String deadWeight) {
        this.deadWeight = deadWeight;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getCubicWeight() {
        return cubicWeight;
    }

    public void setCubicWeight(String cubicWeight) {
        this.cubicWeight = cubicWeight;
    }

    @Override
    public String toString() {
        return "HistoryDetailPieceModel [pieces=" + pieces + ", deadWeight=" + deadWeight + ", dimension=" + dimension + ", cubicWeight=" + cubicWeight + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cubicWeight == null) ? 0 : cubicWeight.hashCode());
        result = prime * result + ((deadWeight == null) ? 0 : deadWeight.hashCode());
        result = prime * result + ((dimension == null) ? 0 : dimension.hashCode());
        result = prime * result + ((pieces == null) ? 0 : pieces.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HistoryDetailPieceModel other = (HistoryDetailPieceModel) obj;
        if (cubicWeight == null) {
            if (other.cubicWeight != null)
                return false;
        } else if (!cubicWeight.equals(other.cubicWeight))
            return false;
        if (deadWeight == null) {
            if (other.deadWeight != null)
                return false;
        } else if (!deadWeight.equals(other.deadWeight))
            return false;
        if (dimension == null) {
            if (other.dimension != null)
                return false;
        } else if (!dimension.equals(other.dimension))
            return false;
        if (pieces == null) {
            if (other.pieces != null)
                return false;
        } else if (!pieces.equals(other.pieces))
            return false;
        return true;
    }

}