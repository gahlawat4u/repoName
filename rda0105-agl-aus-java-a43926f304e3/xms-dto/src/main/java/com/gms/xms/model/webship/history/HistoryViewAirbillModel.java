package com.gms.xms.model.webship.history;


import com.gms.xms.model.BaseModel;

/**
 * Posted from HistoryViewAirbillModel
 * <p>
 * Author TanDT Date Jul 11, 2015
 */

/**
 * @author tkvcl
 *
 */
public class HistoryViewAirbillModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = 8770002259057982182L;

    private String pieceBarcode;
    private String pieceBarcode2;
    private String pieceBarcode3;
    private String itemCode;
    private String picesCount;
    private String imageBarcode;
    private String imageBarcode2;
    private String imageBarcode3;
    private String weight;
    private String dimWeight;
    private String articleId;
    private String sortBin;
    private String dimession;
    private String tntLogo;
    private String destinationPortDescription;
    private String exPort;
    private String strDanger;
    private String reasonForExport;


    public HistoryViewAirbillModel(String pieceBarcode, String pieceBarcode2, String pieceBarcode3, String itemCode, String picesCount, String imageBarcode, String imageBarcode2, String imageBarcode3, String weight, String dimWeight, String articleId, String sortBin, String dimession, String tntLogo, String destinationPortDescription, String exPort, String strDanger, String reasonForExport) {
        super();
        this.pieceBarcode = pieceBarcode;
        this.pieceBarcode2 = pieceBarcode2;
        this.pieceBarcode3 = pieceBarcode3;
        this.itemCode = itemCode;
        this.picesCount = picesCount;
        this.imageBarcode = imageBarcode;
        this.imageBarcode2 = imageBarcode2;
        this.imageBarcode3 = imageBarcode3;
        this.weight = weight;
        this.dimWeight = dimWeight;
        this.articleId = articleId;
        this.sortBin = sortBin;
        this.dimession = dimession;
        this.tntLogo = tntLogo;
        this.destinationPortDescription = destinationPortDescription;
        this.exPort = exPort;
        this.strDanger = strDanger;
        this.reasonForExport = reasonForExport;
    }

    public String getPieceBarcode() {
        return pieceBarcode;
    }

    public void setPieceBarcode(String pieceBarcode) {
        this.pieceBarcode = pieceBarcode;
    }

    public String getPieceBarcode2() {
        return pieceBarcode2;
    }

    public void setPieceBarcode2(String pieceBarcode2) {
        this.pieceBarcode2 = pieceBarcode2;
    }

    public String getPieceBarcode3() {
        return pieceBarcode3;
    }

    public void setPieceBarcode3(String pieceBarcode3) {
        this.pieceBarcode3 = pieceBarcode3;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getPicesCount() {
        return picesCount;
    }

    public void setPicesCount(String picesCount) {
        this.picesCount = picesCount;
    }

    public String getImageBarcode() {
        return imageBarcode;
    }

    public void setImageBarcode(String imageBarcode) {
        this.imageBarcode = imageBarcode;
    }

    public String getImageBarcode2() {
        return imageBarcode2;
    }

    public void setImageBarcode2(String imageBarcode2) {
        this.imageBarcode2 = imageBarcode2;
    }

    public String getImageBarcode3() {
        return imageBarcode3;
    }

    public void setImageBarcode3(String imageBarcode3) {
        this.imageBarcode3 = imageBarcode3;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDimWeight() {
        return dimWeight;
    }

    public void setDimWeight(String dimWeight) {
        this.dimWeight = dimWeight;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getSortBin() {
        return sortBin;
    }

    public void setSortBin(String sortBin) {
        this.sortBin = sortBin;
    }

    public String getDimession() {
        return dimession;
    }

    public void setDimession(String dimession) {
        this.dimession = dimession;
    }

    public String getTntLogo() {
        return tntLogo;
    }

    public void setTntLogo(String tntLogo) {
        this.tntLogo = tntLogo;
    }

    public String getDestinationPortDescription() {
        return destinationPortDescription;
    }

    public void setDestinationPortDescription(String destinationPortDescription) {
        this.destinationPortDescription = destinationPortDescription;
    }

    public String getExPort() {
        return exPort;
    }

    public void setExPort(String exPort) {
        this.exPort = exPort;
    }

    public String getStrDanger() {
        return strDanger;
    }

    public void setStrDanger(String strDanger) {
        this.strDanger = strDanger;
    }

    public String getReasonForExport() {
        return reasonForExport;
    }

    public void setReasonForExport(String reasonForExport) {
        this.reasonForExport = reasonForExport;
    }


    @Override
    public String toString() {
        return "HistoryViewAirbillModel [pieceBarcode=" + pieceBarcode + ", pieceBarcode2=" + pieceBarcode2 + ", pieceBarcode3=" + pieceBarcode3 + ", itemCode=" + itemCode + ", picesCount=" + picesCount + ", imageBarcode=" + imageBarcode + ", imageBarcode2=" + imageBarcode2 + ", imageBarcode3=" + imageBarcode3 + ", weight=" + weight + ", dimWeight=" + dimWeight + ", articleId=" + articleId + ", sortBin=" + sortBin + ", dimession=" + dimession + ", tntLogo=" + tntLogo
                + ", destinationPortDescription=" + destinationPortDescription + ", exPort=" + exPort + ", strDanger=" + strDanger + ", reasonForExport=" + reasonForExport + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((articleId == null) ? 0 : articleId.hashCode());
        result = prime * result + ((destinationPortDescription == null) ? 0 : destinationPortDescription.hashCode());
        result = prime * result + ((dimWeight == null) ? 0 : dimWeight.hashCode());
        result = prime * result + ((dimession == null) ? 0 : dimession.hashCode());
        result = prime * result + ((exPort == null) ? 0 : exPort.hashCode());
        result = prime * result + ((imageBarcode == null) ? 0 : imageBarcode.hashCode());
        result = prime * result + ((imageBarcode2 == null) ? 0 : imageBarcode2.hashCode());
        result = prime * result + ((imageBarcode3 == null) ? 0 : imageBarcode3.hashCode());
        result = prime * result + ((itemCode == null) ? 0 : itemCode.hashCode());
        result = prime * result + ((picesCount == null) ? 0 : picesCount.hashCode());
        result = prime * result + ((pieceBarcode == null) ? 0 : pieceBarcode.hashCode());
        result = prime * result + ((pieceBarcode2 == null) ? 0 : pieceBarcode2.hashCode());
        result = prime * result + ((pieceBarcode3 == null) ? 0 : pieceBarcode3.hashCode());
        result = prime * result + ((reasonForExport == null) ? 0 : reasonForExport.hashCode());
        result = prime * result + ((sortBin == null) ? 0 : sortBin.hashCode());
        result = prime * result + ((strDanger == null) ? 0 : strDanger.hashCode());
        result = prime * result + ((tntLogo == null) ? 0 : tntLogo.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
        HistoryViewAirbillModel other = (HistoryViewAirbillModel) obj;
        if (articleId == null) {
            if (other.articleId != null)
                return false;
        } else if (!articleId.equals(other.articleId))
            return false;
        if (destinationPortDescription == null) {
            if (other.destinationPortDescription != null)
                return false;
        } else if (!destinationPortDescription.equals(other.destinationPortDescription))
            return false;
        if (dimWeight == null) {
            if (other.dimWeight != null)
                return false;
        } else if (!dimWeight.equals(other.dimWeight))
            return false;
        if (dimession == null) {
            if (other.dimession != null)
                return false;
        } else if (!dimession.equals(other.dimession))
            return false;
        if (exPort == null) {
            if (other.exPort != null)
                return false;
        } else if (!exPort.equals(other.exPort))
            return false;
        if (imageBarcode == null) {
            if (other.imageBarcode != null)
                return false;
        } else if (!imageBarcode.equals(other.imageBarcode))
            return false;
        if (imageBarcode2 == null) {
            if (other.imageBarcode2 != null)
                return false;
        } else if (!imageBarcode2.equals(other.imageBarcode2))
            return false;
        if (imageBarcode3 == null) {
            if (other.imageBarcode3 != null)
                return false;
        } else if (!imageBarcode3.equals(other.imageBarcode3))
            return false;
        if (itemCode == null) {
            if (other.itemCode != null)
                return false;
        } else if (!itemCode.equals(other.itemCode))
            return false;
        if (picesCount == null) {
            if (other.picesCount != null)
                return false;
        } else if (!picesCount.equals(other.picesCount))
            return false;
        if (pieceBarcode == null) {
            if (other.pieceBarcode != null)
                return false;
        } else if (!pieceBarcode.equals(other.pieceBarcode))
            return false;
        if (pieceBarcode2 == null) {
            if (other.pieceBarcode2 != null)
                return false;
        } else if (!pieceBarcode2.equals(other.pieceBarcode2))
            return false;
        if (pieceBarcode3 == null) {
            if (other.pieceBarcode3 != null)
                return false;
        } else if (!pieceBarcode3.equals(other.pieceBarcode3))
            return false;
        if (reasonForExport == null) {
            if (other.reasonForExport != null)
                return false;
        } else if (!reasonForExport.equals(other.reasonForExport))
            return false;
        if (sortBin == null) {
            if (other.sortBin != null)
                return false;
        } else if (!sortBin.equals(other.sortBin))
            return false;
        if (strDanger == null) {
            if (other.strDanger != null)
                return false;
        } else if (!strDanger.equals(other.strDanger))
            return false;
        if (tntLogo == null) {
            if (other.tntLogo != null)
                return false;
        } else if (!tntLogo.equals(other.tntLogo))
            return false;
        if (weight == null) {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        return true;
    }


}