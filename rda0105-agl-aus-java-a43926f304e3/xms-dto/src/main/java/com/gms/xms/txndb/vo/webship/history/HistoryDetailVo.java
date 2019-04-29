package com.gms.xms.txndb.vo.webship.history;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from HistoryDetailVo
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryDetailVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 8770002259057982182L;

    private HistoryDetailInfoVo historyDetailInfoVo;
    private HistoryDetailPieceVo historyDetailPieceVo;
    private HistoryDetailAccessorialVo historyDetailAccessorialVo;

    public HistoryDetailAccessorialVo getHistoryDetailAccessorialVo() {
        return historyDetailAccessorialVo;
    }

    public void setHistoryDetailAccessorialVo(HistoryDetailAccessorialVo historyDetailAccessorialVo) {
        this.historyDetailAccessorialVo = historyDetailAccessorialVo;
    }

    public HistoryDetailPieceVo getHistoryDetailPieceVo() {
        return historyDetailPieceVo;
    }

    public void setHistoryDetailPieceVo(HistoryDetailPieceVo historyDetailPieceVo) {
        this.historyDetailPieceVo = historyDetailPieceVo;
    }

    public HistoryDetailInfoVo getHistoryDetailInfoVo() {
        return historyDetailInfoVo;
    }

    public void setHistoryDetailInfoVo(HistoryDetailInfoVo historyDetailInfoVo) {
        this.historyDetailInfoVo = historyDetailInfoVo;
    }

    @Override
    public String toString() {
        return "HistoryDetailVo [historyDetailInfoVo=" + historyDetailInfoVo + ", historyDetailPieceVo=" + historyDetailPieceVo + ", historyDetailAccessorialVo=" + historyDetailAccessorialVo + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((historyDetailAccessorialVo == null) ? 0 : historyDetailAccessorialVo.hashCode());
        result = prime * result + ((historyDetailInfoVo == null) ? 0 : historyDetailInfoVo.hashCode());
        result = prime * result + ((historyDetailPieceVo == null) ? 0 : historyDetailPieceVo.hashCode());
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
        HistoryDetailVo other = (HistoryDetailVo) obj;
        if (historyDetailAccessorialVo == null) {
            if (other.historyDetailAccessorialVo != null)
                return false;
        } else if (!historyDetailAccessorialVo.equals(other.historyDetailAccessorialVo))
            return false;
        if (historyDetailInfoVo == null) {
            if (other.historyDetailInfoVo != null)
                return false;
        } else if (!historyDetailInfoVo.equals(other.historyDetailInfoVo))
            return false;
        if (historyDetailPieceVo == null) {
            if (other.historyDetailPieceVo != null)
                return false;
        } else if (!historyDetailPieceVo.equals(other.historyDetailPieceVo))
            return false;
        return true;
    }

}