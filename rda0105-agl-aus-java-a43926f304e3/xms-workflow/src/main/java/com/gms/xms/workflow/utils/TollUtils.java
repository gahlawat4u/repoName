package com.gms.xms.workflow.utils;

import com.gms.xms.txndb.vo.PieceVo;

import java.util.List;

/**
 * Posted from TollPriorityUtils
 * <p>
 * Author HungNT Date Aug 25, 2015
 */
public class TollUtils {
    public static Integer getTotalPiecesQuantity(List<PieceVo> pieceVos) {
        Integer total = 1;
        for (PieceVo pieceVo : pieceVos) {
            total += pieceVo.getQuantity();
        }
        return total;
    }
}
